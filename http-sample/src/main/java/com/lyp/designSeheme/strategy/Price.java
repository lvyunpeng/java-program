package com.lyp.designSeheme.strategy;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 15:16
 **/
public class Price {

    private MemberStrategy memberStrategy;

    public Price(MemberStrategy memberStrategy){
        this.memberStrategy = memberStrategy;
    }

    public double quote(double bookPrice){
        return this.memberStrategy.getPrice(bookPrice);
    }
}
