package com.lyp.sample;

/**
 * @Description
 * @Author lv.yp
 * @Date 2019-01-10 18:08
 **/
public class Student {

    private int age;

    private String nickName;

    private Integer grade;

    public Student(){}

    public Student(int age, String nickName, int grade){
        this.age = age;
        this.nickName = nickName;
        this.grade = grade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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
