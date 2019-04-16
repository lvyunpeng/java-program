package com.lyp.designSeheme.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-21 19:53
 **/
public class GameRecordSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        if(observerList.contains(observer)){
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObservers(String msg) {
        for(Observer observer : observerList){
            observer.update(msg);
        }
    }

}
