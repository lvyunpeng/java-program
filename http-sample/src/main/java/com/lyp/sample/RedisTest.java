package com.lyp.sample;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1"); // 默认端口
        Random random = new Random();
        List<String> gameIdList = new ArrayList<String>();
        gameIdList.add("g1008");
        gameIdList.add("g1009");
        gameIdList.add("g1010");
        gameIdList.add("g1032");
        gameIdList.add("g1045");
        gameIdList.add("g2008");
        gameIdList.add("g2009");
        gameIdList.add("g2010");
        gameIdList.add("g2032");
        gameIdList.add("g2045");

        for(int i=0; i<100; i++){
            int userId = random.nextInt(1000000000) + 1000000000;
            String cacheKey = String.format("cache_user_game_record_%d", userId);
            for(int j=0; j<10; j++){
                jedis.zadd(cacheKey, System.currentTimeMillis(), gameIdList.get(j));
            }
        }
    }
}
