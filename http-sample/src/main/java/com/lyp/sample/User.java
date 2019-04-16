package com.lyp.sample;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-10 18:07
 **/
public class User {

    private int age;

    private String nickName;

    public User(){};

    public User(int age, String nickName){
        this.age = age;
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
