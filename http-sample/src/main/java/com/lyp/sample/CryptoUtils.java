package com.lyp.sample;

import redis.clients.jedis.Jedis;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by arthur on 16/3/4.
 */
public class CryptoUtils {


    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        Random random = new Random();
        for(int i=0; i<10000000; i++){
            long userId = random.nextInt(100000000);
            double scores = random.nextInt(100000);
            String member = Long.toString(userId + 100000000);
            jedis.zadd("g1014.police.new", scores, member);
            if(i % 10000 == 0){
                System.out.println(i + " finished");
            }
        }
    }

    public static String sha1(String conent) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(conent.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
