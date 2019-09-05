package com.lyp.sample;

import org.apache.commons.lang3.StringUtils;

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
        executor = new ThreadPoolExecutor(100, 100, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000), new MyTaskThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args) {
        Random random = new Random();
        Map<String, Object> map = new HashMap();
        String url = "http://52.82.45.18:8815/api/v1/inner/users/games/reporting?userId=%d&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
//        String url = "http://mods.sandboxol.com/activity/api/v1/lucky/turntable?activityId=lucky_turntable&type=gold&isMulti=0";
//        String url2 = "http://mods.sandboxol.com/activity/api/v1/lucky/turntable?activityId=lucky_turntable&type=gold&isMulti=1";
//        String url3 = "http://mods.sandboxol.com/activity/api/v1/lucky/turntable?activityId=lucky_turntable&type=gold&isMulti=0";
//        String url4 = "http://mods.sandboxol.com/activity/api/v1/lucky/turntable?activityId=lucky_turntable&type=diamond&isMulti=0";

//        String integralAddUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/integral?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
//        String getIntegralUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/info?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df&userId=";
//        String addIntegralUrl = "http://120.92.158.119/gameaide/api/v1/inner/user/segment/info?gameId=g1042&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df&userId=";
//        String createTableUrl = "http://120.92.158.119/gameaide/api/v1/inner/data/sheet?gameId=g1042&type=USER_SEGMENT&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
//        Map<Integer, String> urlMap = new HashMap<>();
//        urlMap.put(0, integralAddUrl);
//        urlMap.put(1, getIntegralUrl);
//        urlMap.put(2, addIntegralUrl);
//        urlMap.put(3, createTableUrl);


        int count = 1000000;
        for (int i = 0; i < count; i++) {
            Map<String, String> headMap = new HashMap<>();
            int a = random.nextInt(1000000);
            MyTask myTask = null;
            String urlNew = String.format(url, Integer.valueOf(a * 16));


//            if(a == 0){
//                headMap.put("userId", "756244848");
//                headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3NTYyNDQ4NDgiLCJpYXQiOjE1NjE2NTY2NTIsInN1YiI6IjIwMTktMDYtMjcgMTc6MzA6NTIiLCJpc3MiOiJTYW5kYm94LVNlY3VyaXR5LUJhc2ljIiwiZXhwIjoxNTYyNTQ1Njg0fQ.9P71fapTJM6a_pF8depCS7EU4bXEGZaPJ0ShAqL8l6Y");
//                headMap.put("language", "zh_CN");
//                myTask = new MyTask(i, url, headMap, "");
//            }else if(a == 1){
//                headMap.put("userId", "415190464");
//                headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0MTUxOTA0NjQiLCJpYXQiOjE1NjE2NDU4OTQsInN1YiI6IjIwMTktMDYtMjcgMTQ6MzE6MzQiLCJpc3MiOiJTYW5kYm94LVNlY3VyaXR5LUJhc2ljIiwiZXhwIjoxNTYyNTM0OTI3fQ.N5fp72nECVfMQTt2dkuPYfF9IQ1CKRff9IPkiYeK7d8");
//                headMap.put("language", "zh_CN");
//                myTask = new MyTask(i, url2, headMap, "");
//            }else if(a == 2){
//                headMap.put("userId", "48");
//                headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0OCIsImlhdCI6MTU2MTY0NTYzMiwic3ViIjoiMjAxOS0wNi0yNyAxNDoyNzoxMiIsImlzcyI6IlNhbmRib3gtU2VjdXJpdHktQmFzaWMiLCJleHAiOjE1NjI1MzQ2NjV9.I8N0LCTnKYnraoyI_sw7tkBg5S8SCGw2sC833zDJS48");
//                headMap.put("language", "zh_CN");
//                myTask = new MyTask(i, url3, headMap, "");
//            }else if(a == 3){
//                headMap.put("userId", "64");
//                headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NCIsImlhdCI6MTU2MTQ0ODc1Miwic3ViIjoiMjAxOS0wNi0yNSAwNzo0NTo1MiIsImlzcyI6IlNhbmRib3gtU2VjdXJpdHktQmFzaWMiLCJleHAiOjE1NjIzMzc3ODR9.MzKSW42xE62S1rQrQ3Zerswu52TMgjfg8akKvCZbGmQ");
//                headMap.put("language", "zh_CN");
//                myTask = new MyTask(i, url4, headMap, "");
//            }


            executor.execute(myTask);
        }

        while (executor.getCompletedTaskCount() != count) {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        }
        System.out.println("thread pool finished");
        executor.shutdown();
    }
}

class MyTask implements Runnable {
    private int taskNum;
    private String url;
    private Map<String, String> headMap;
    private String param;


    public MyTask(int num, String url, Map<String, String> headMap, String param) {
        this.taskNum = num;
        this.url = url;
        this.headMap = headMap;
        this.param = param;
    }

    @Override
    public void run() {
        try {
            String result = HttpSample.doPostWithHeader(url, headMap, param);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class MyTaskThreadFactory implements ThreadFactory {
    private static String FACTORYNAME = "MYTASKFACTORY";
    private static List<? super Thread> threadList = new ArrayList<>();

    public void setFactoryName(String name) {
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


