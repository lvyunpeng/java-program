package com.lyp.designSeheme.singleton;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-03-22 14:39
 **/
public class InnerClassSingleton {

    private InnerClassSingleton(){}

    private static class SingletonHolder{
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public static InnerClassSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
