package com.lyp.designSeheme.adaptor;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 18:16
 **/
public class Adapter extends Source implements Targetable{
    @Override
    public void method2() {
        System.out.println("this is new methods");
    }
}
