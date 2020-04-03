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
        executor = new ThreadPoolExecutor(500, 500, 200,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100000), new MyTaskThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args) {
        String url = "http://dev.mods.sandboxol.cn/game/api/v1/game/auth?typeId=%s";
        Map<String, String> headMap = new HashMap<>();
        headMap.put("userId", "128");
        headMap.put("language", "zh_CN");
        headMap.put("Access-Token", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjgiLCJpYXQiOjE1ODU2NTI3NTgsInN1YiI6IjIwMjAtMDMtMzEgMTk6MDU6NTgiLCJpc3MiOiJTYW5kYm94LVNlY3VyaXR5LUJhc2ljIiwiZXhwIjoxNTg2NTQxNzkwfQ.Ntr77zs_qXPltZp9u15H8HGDXRN4u7l37FAbfu4Jlwk");
        headMap.put("CloudFront-Viewer-Country", "zh_CN");
        headMap.put("packageName", "android");

        List<String> gameIds = new ArrayList<>();
        gameIds.add("g1008");
        gameIds.add("g1046");
        gameIds.add("g1058");
        gameIds.add("g1002");
        gameIds.add("g1018");
        gameIds.add("g1055");
        Random random = new Random();


        int count = 1000000;
        for (int i = 0; i < count; i++) {
            String gameUrl = String.format(url, gameIds.get(random.nextInt(4)));
            MyTask myTask = new MyTask(i, gameUrl, headMap, "");
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
            String result = HttpSample.doGetWithHeader(url, headMap, param);
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


