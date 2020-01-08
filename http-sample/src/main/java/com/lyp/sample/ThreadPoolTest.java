package com.lyp.sample;

import org.apache.commons.lang3.StringUtils;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

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
        executor = new ThreadPoolExecutor(10, 10, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000), new MyTaskThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args) {
        String url = "http://52.82.39.67:8910/api/v1/game/rank?isNew=0&timestamp=1506316800723&nonce=792703&signature=115630d4c852eda21bcc0fbe7b3ab626ae19a7df";
//        String url = "http://mods.sandboxol.com/activity/api/v1/lucky/turntable?activityId=halloween_turntable&type=diamond&isMulti=1";
        Map<String, String> headMap = new HashMap<>();
//        headMap.put("userId", "128");
//        headMap.put("language", "zh_CN");
//        headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjgiLCJpYXQiOjE1NzUzNDUzNzcsInN1YiI6IjIwMTktMTItMDMgMTE6NTY6MTciLCJpc3MiOiJTYW5kYm94LVNlY3VyaXR5LUJhc2ljIiwiZXhwIjoxNTc2MjM0NDEwfQ.wt1J_ZcLoBrhkrqzqGmEScRnvtBRWJ18hwPP8RFI6Qk");

        Map<String, Object> map = new HashMap<>();
        map.put("add", Boolean.TRUE);
        map.put("count", 2);
        map.put("key", "g1014.criminal");
        Random random = new Random();

        int count = 1000;
        for (int i = 0; i < count; i++) {
            List<Map<String, Object>> list = new ArrayList<>();
            map.put("member", 16 * (random.nextInt(2) + 1) );
            list.add(map);
            MyTask myTask = new MyTask(i, url, headMap, JsonUtils.toJson(list));
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
//            String result = HttpSample.doGetWithHeader(url, headMap, param);
            String result = HttpSample.doPostWithHeader(url, headMap, param);
            System.out.println(taskNum +  ": " + result);
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


