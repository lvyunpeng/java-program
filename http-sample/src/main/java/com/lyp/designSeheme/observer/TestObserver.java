package com.lyp.designSeheme.observer;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-21 20:06
 **/
public class TestObserver {

    public static void main(String[] args){
        //主题一
        GameRecordSubject gameRecordSubject = new GameRecordSubject();

        GameTimeObserver gameTimeObserver = new GameTimeObserver();
        ClanObserver clanObserver = new ClanObserver();
        gameRecordSubject.registerObserver(gameTimeObserver);
        gameRecordSubject.registerObserver(clanObserver);

        gameRecordSubject.notifyObservers("game record 1");
        gameRecordSubject.notifyObservers("game record 2");

    }
}
