package com.lyp.designSeheme.observer;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-21 19:57
 **/
public class GameTimeObserver implements Observer {

    @Override
    public void update(String msg) {
        System.out.println("game time statistic" + msg);
    }
}
