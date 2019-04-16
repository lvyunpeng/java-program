package com.lyp.sample;

import com.google.gson.reflect.TypeToken;

import java.util.*;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-10 18:05
 **/
public class TestJson {

    private static Set<String> gameIdSet = new HashSet<>();
    static {
        gameIdSet.add("g1042");
        gameIdSet.add("g1043");
        gameIdSet.add("g1044");
        gameIdSet.add("g1045");
    }

    public static void main(String[] args){
        testJson();
//        String gameId = "g1041";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
//        gameId = "g1042";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
//        gameId = "g1043";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
//        gameId = "g1044";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
//        gameId = "g1045";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
//        gameId = "g1046";
//        gameId = filterGame(gameId);
//        System.out.println(gameId);
    }

    public static  String filterGame(String gameId){
        if(gameIdSet.contains(gameId)){
            return "g1042";
        }

        return gameId;
    }


    public static void testJson(){
        Random random = new Random();
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<1000000; i++){
            Integer a = random.nextInt(200);
            if(map.containsKey(a)){
                map.put(a, map.get(a) + 1);
            }else{
                map.put(a, 1);
            }
        }
        for(int i=0; i<=200; i++){
            System.out.println(i + ", " + map.get(i));
        }


//        User user = new User(10, "hello");
//        String jsonString = JsonUtils.toJson(user);
//        Student student = (Student)JsonUtils.fromJson(jsonString, new TypeToken<Student>(){}.getType());
//        System.out.println(student.getGrade());
    }

}
