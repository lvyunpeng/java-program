package com.lyp.designSeheme.singleton;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-21 21:02
 **/
public class LanHanSingleton {

    private static LanHanSingleton instance = null;

    private LanHanSingleton(){}

    public LanHanSingleton getInstance(){
        if(null == instance){
            synchronized (this){
                if(null == instance){
                    instance = new LanHanSingleton();
                }
            }
        }

        return instance;
    }
}
