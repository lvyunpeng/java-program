package com.lyp.sample;

import redis.clients.jedis.Jedis;

import java.util.*;

public class DateTest {

    public static void main(String[] args){
        String result = "12_12";
        System.out.println(result.lastIndexOf("\\_"));
        String userId = result.substring(result.lastIndexOf("\\_")+1);
        System.out.println(userId);
//        Calendar ca = Calendar.getInstance();
//        int MaxDay=ca.getMaximum(Calendar.DAY_OF_MONTH);
//        ca.set( ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), MaxDay, 23, 59, 59);
//        System.out.println(ca.getTime());
//        ca.set( ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), 1, 0, 0, 0);
//        System.out.println(ca.getTime());
    }
}
