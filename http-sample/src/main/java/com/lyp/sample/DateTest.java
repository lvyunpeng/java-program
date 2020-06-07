package com.lyp.sample;

import com.google.gson.reflect.TypeToken;
import redis.clients.jedis.Jedis;

import java.util.*;

public class DateTest {

    public static void main(String[] args){
        String list = "[{\"manorId\":null,\"tempId\":\"0\",\"meta\":\"0\",\"charm\":0,\"price\":0,\"positionX\":31,\"positionY\":3,\"positionZ\":53,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"5\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":1,\"positionZ\":58,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"5\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":2,\"positionZ\":58,\"id\":null},{\"manorId\":null,\"tempId\":\"5\",\"meta\":\"1\",\"charm\":1,\"price\":80,\"positionX\":31,\"positionY\":2,\"positionZ\":50,\"id\":null},{\"manorId\":null,\"tempId\":\"0\",\"meta\":\"0\",\"charm\":0,\"price\":0,\"positionX\":31,\"positionY\":1,\"positionZ\":53,\"id\":null},{\"manorId\":null,\"tempId\":\"0\",\"meta\":\"0\",\"charm\":0,\"price\":0,\"positionX\":31,\"positionY\":1,\"positionZ\":50,\"id\":null},{\"manorId\":null,\"tempId\":\"0\",\"meta\":\"0\",\"charm\":0,\"price\":0,\"positionX\":31,\"positionY\":2,\"positionZ\":53,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":35,\"positionY\":3,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":32,\"positionY\":3,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"0\",\"meta\":\"0\",\"charm\":0,\"price\":0,\"positionX\":30,\"positionY\":2,\"positionZ\":53,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"5\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":3,\"positionZ\":58,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":36,\"positionY\":3,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":36,\"positionY\":1,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":32,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":3,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":33,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":32,\"positionY\":2,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":35,\"positionY\":2,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"5\",\"meta\":\"2\",\"charm\":1,\"price\":80,\"positionX\":33,\"positionY\":2,\"positionZ\":50,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":34,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"138\",\"meta\":\"0\",\"charm\":1,\"price\":1000,\"positionX\":33,\"positionY\":1,\"positionZ\":56,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":36,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":2,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"0\",\"charm\":1,\"price\":50,\"positionX\":35,\"positionY\":2,\"positionZ\":48,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":31,\"positionY\":1,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":32,\"positionY\":1,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"5\",\"meta\":\"2\",\"charm\":1,\"price\":80,\"positionX\":24,\"positionY\":1,\"positionZ\":51,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":35,\"positionY\":1,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"65\",\"meta\":\"3\",\"charm\":1,\"price\":50,\"positionX\":36,\"positionY\":2,\"positionZ\":57,\"id\":null},{\"manorId\":null,\"tempId\":\"138\",\"meta\":\"0\",\"charm\":1,\"price\":1000,\"positionX\":37,\"positionY\":1,\"positionZ\":57,\"id\":null}]";
        List<Block> blocks = (List<Block>) JsonUtils.fromJson(list, new TypeToken<List<Block>>(){}.getType());
        List<Block> blockList = new ArrayList<>();
        for(int i=0; i<200; i++){
            blockList.addAll(blocks);
        }
        System.out.println(blockList.size());
        System.out.println(JsonUtils.toJson(blockList).length());
        System.out.println(JsonUtils.toJson(blockList));

//        String result = "12_12";
//        System.out.println(result.lastIndexOf("\\_"));
//        String userId = result.substring(result.lastIndexOf("\\_")+1);
//        System.out.println(userId);
//        Calendar ca = Calendar.getInstance();
//        int MaxDay=ca.getMaximum(Calendar.DAY_OF_MONTH);
//        ca.set( ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), MaxDay, 23, 59, 59);
//        System.out.println(ca.getTime());
//        ca.set( ca.get(Calendar.YEAR), ca.get(Calendar.MONTH), 1, 0, 0, 0);
//        System.out.println(ca.getTime());
    }
}
