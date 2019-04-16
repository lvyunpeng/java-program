package com.lyp.designSeheme.observer;

/**
 * @Description
 * 所有主题必须实现该接口
 * @Author lv.yp
 * @Date 2019-03-21 19:45
 **/
public interface Subject {

    //注册观察者
    public void registerObserver(Observer observer);

    //删除观察者
    public void removeObserver(Observer observer);

    //通知所有观察者
    public void notifyObservers(String msg);
}
