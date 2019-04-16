package com.lyp.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-25 18:09
 **/
public class TestFor {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<10; i++){
            list.add(i);
        }
        int start = 5;
        int end = 8;
        for(Integer i : list){
            if(start > i){
                System.out.println("start: " + i);
            }else if(end > i){
                System.out.println("end: " + i);
            }else{
                break;
            }
            System.out.println("iiiiiiii: " + i);
        }

    }
}
