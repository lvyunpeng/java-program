package com.lyp.sample;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-01 11:28
 **/
public class ThreadPoolTest {

    private static ThreadPoolExecutor executor = null;

    static {
        executor = new ThreadPoolExecutor(500, 500, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000), new MyTaskThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args){
        Random random = new Random();
        Map<String, Object> map = new HashMap();
        String integralAddUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/integral?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
        String getIntegralUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/info?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df&userId=";
        String addIntegralUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/info?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df&userId=";
        String createTableUrl = "http://120.92.158.119/gameaide/api/v1/inner/data/sheet?gameId=g1042&type=USER_SEGMENT&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
        Map<Integer, String> urlMap = new HashMap<>();
        urlMap.put(0, integralAddUrl);
        urlMap.put(1, getIntegralUrl);
        urlMap.put(2, addIntegralUrl);
        urlMap.put(3, createTableUrl);

//        String url = "http://120.92.145.229:8834/api/v1/treasure/chest?type=gold&activityId=new_year";
        List<Long> userIds = new ArrayList<>();
//        userIds.add(3536l);
//        userIds.add(224l);
        userIds.add(84624l);
//        userIds.add(128l);
//        userIds.add(144l);

        int count = 50000;
        Random random1 = new Random();
        for(int i=0;i<count;i++){
            Map<String, String> headMap = new HashMap<>();
//            headMap.put("userId", userIds.get(random.nextInt(userIds.size())).toString());
//            headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4NDYyNCIsImlhdCI6MTU1MzE1NTEyOSwic3ViIjoiMjAxOS0wMy0yMSAwNzo1ODo0OSIsImlzcyI6IlNhbmRib3gtU2VjdXJpdHktQmFzaWMiLCJleHAiOjE1NTQwNDQxNjF9.86dY7MuNN1eKGMtG5RItRWLo1HElSj6SiZVrZWF8P-g");
//            headMap.put("language", "zh_CN");
            int number = random1.nextInt(3);
            String url = urlMap.get(number);

            MyTask myTask = new MyTask(i, number, url);
            executor.execute(myTask);
        }

        while(executor.getCompletedTaskCount() != count){
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }
        }
        System.out.println("thread pool finished");
        executor.shutdown();
    }
}

class MyTask implements Runnable {
    private int taskNum;

    private int number;

    private String url;


    public MyTask(int num,int number, String url) {
        this.taskNum = num;
        this.number = number;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            long userId = new Random().nextInt(2000000) + 1000000;
            if(number == 0){
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> map = new HashMap<>();
                map.put("userId", Long.valueOf(userId));
                map.put("gameId", "g1042");
                map.put("integral", 10);
                map.put("level", 1);
                list.add(map);
                System.out.println(number + ":" + url + ": "  + JsonUtils.toJson(list) + ":" + HttpSample.doPost(url, JsonUtils.toJson(list)));
            }else if(number == 1){
                System.out.println(number + ":" + url+userId + ": " + HttpSample.doGet(url+userId));
            }else if(number == 2){
                userId = 5000000 + userId;
                System.out.println(number + ":" + url+userId + ": " + HttpSample.doGet(url+userId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class MyTaskThreadFactory implements ThreadFactory{
    private static String FACTORYNAME = "MYTASKFACTORY";
    private static List<? super Thread> threadList = new ArrayList<>();

    public void setFactoryName(String name){
        FACTORYNAME = name;
    }
    @Override
    public Thread newThread(Runnable r) {
        synchronized (r) {
            //这里可以自定义个Thread，用了处理在创建线程前后预处理
            Thread t = new Thread(r, FACTORYNAME + threadList.size());
            threadList.add(t);
            return t;
        }
    }
}


