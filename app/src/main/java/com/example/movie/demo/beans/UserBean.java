package com.example.movie.demo.beans;


import org.litepal.crud.DataSupport;

import java.io.Serializable;


public class UserBean extends DataSupport implements Serializable {

    private int id;
    private String user_id;
    private int user_type;
    private String user_name;
    private int user_photo;
    private int isSignIn;
    private String student_id;
    private String sex;
    private String age;
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(int user_photo) {
        this.user_photo = user_photo;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getIsSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(int isSignIn) {
        this.isSignIn = isSignIn;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
