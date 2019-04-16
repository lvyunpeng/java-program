package com.lyp.designSeheme.observer;

/**
 * @Description
 * 所有观察者都要事先该接口
 * @Author lv.yp
 * @Date 2019-03-21 19:46
 **/
public interface Observer {

    public void update(String msg);
}
