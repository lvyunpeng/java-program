package com.lyp.designSeheme.strategy;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 15:18
 **/
public class Client {

    public static void main(String[] args){
        AdvancedMemberStrategy advancedMemberStrategy = new AdvancedMemberStrategy();
        Price price = new Price(advancedMemberStrategy);
        double bookPrice = price.quote(300);
        System.out.println(bookPrice);
    }
}
