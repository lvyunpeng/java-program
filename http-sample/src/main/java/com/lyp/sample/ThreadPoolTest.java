package com.lyp.sample;

import java.util.*;
import java.util.concurrent.*;

import static com.lyp.sample.HttpSample.doPost;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-01 11:28
 **/
public class ThreadPoolTest {

    private static ThreadPoolExecutor executor = null;

    static {
        executor = new ThreadPoolExecutor(10, 10, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(500), new MyTaskThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args){
        Random random = new Random();
        Map<String, Object> map = new HashMap();
        map.put("userId", 32);
        map.put("currency", 1);
        map.put("gameId", "g1001");
        map.put("propsId", "1");
        map.put("quantity", 10);
        String url = "http://120.92.158.119/pay/api/v1/pay/users/purchase/game/props?timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df&time=" + random.nextInt(10000);

        int count = 100;
        for(int i=0;i<count;i++){
            MyTask myTask = new MyTask(i, JsonUtils.toJson(map), url);
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

    private String param;

    private String url;

    public MyTask(int num, String param, String url) {
        this.taskNum = num;
        this.param = param;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.taskNum + ": " + HttpSample.doPost(url, param));
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


