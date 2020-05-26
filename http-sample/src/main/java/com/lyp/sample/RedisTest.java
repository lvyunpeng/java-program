package com.lyp.sample;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

import java.util.Random;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1"); // 默认端口
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        for(int i=0; i<2000000; i++){
            Integer userId = random.nextInt(100000000) + 100000000;
            jedis.setbit("user_info_1", userId, Boolean.TRUE);
            if(i % 100000 == 0){
                System.out.println(i);
            }
        }
        long addTime = System.currentTimeMillis();
        for(int i=0; i<2000000; i++){
            Integer userId = random.nextInt(100000000) + 100000000;
            jedis.setbit("user_info_2", userId, Boolean.TRUE);
            if(i % 100000 == 0){
                System.out.println(i);
            }
        }
        long addTime2 = System.currentTimeMillis();
        Long size = jedis.bitop(BitOP.AND,"tmp_redis", "user_info_1", "user_info_2");
        Long sizeNew = jedis.bitcount("tmp_redis");
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("%s, %s, %s, %s, %s", addTime-startTime, addTime2 - addTime, time3-addTime2, size, sizeNew));
    }


//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("127.0.0.1"); // 默认端口
//        Random random = new Random();
//        long startTime = System.currentTimeMillis();
//        for(int i=0; i<2000000; i++){
//            Integer userId = random.nextInt(1000000000) + 1000000000;
//            jedis.sadd("user_info_1", userId.toString());
//            if(i % 100000 == 0){
//                System.out.println(i);
//            }
//        }
//        long addTime = System.currentTimeMillis();
//        for(int i=0; i<2000000; i++){
//            Integer userId = random.nextInt(1000000000) + 1000000000;
//            jedis.sadd("user_info_2", userId.toString());
//            if(i % 100000 == 0){
//                System.out.println(i);
//            }
//        }
//        long addTime2 = System.currentTimeMillis();
//        Long size = jedis.sunionstore("tmp_redis", "user_info_1", "user_info_2");
//        Long sizeNew = jedis.scard("tmp_redis");
//        long time3 = System.currentTimeMillis();
//        System.out.println(String.format("%s, %s, %s, %s, %s", addTime-startTime, addTime2 - addTime, time3-addTime2, size, sizeNew));
//    }
}
