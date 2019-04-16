package com.lyp.designSeheme.observer;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-21 20:03
 **/
public class ClanObserver implements Observer {

    @Override
    public void update(String msg) {
        System.out.println("clan observer get msg: " + msg);
    }
}
