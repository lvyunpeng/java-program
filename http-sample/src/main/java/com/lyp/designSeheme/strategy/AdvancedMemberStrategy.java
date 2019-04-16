package com.lyp.designSeheme.strategy;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 15:14
 **/
public class AdvancedMemberStrategy implements MemberStrategy {
    @Override
    public double getPrice(double booksPrice) {
        return booksPrice * 0.7;
    }
}
