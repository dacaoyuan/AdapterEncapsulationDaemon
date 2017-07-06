package com.example.adapterencapsulation.bean;

/**
 * Created by zhyantai on 2017/7/4.
 */

public class StateBean {
    public boolean isDisplay;

    public boolean position;

    public String test;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public boolean isDisplay() {
        return isDisplay;
    }

    public void setDisplay(boolean display) {
        isDisplay = display;
    }

    public void setPosition(boolean position) {
        this.position = position;
    }

    public boolean isPosition() {
        return position;
    }

}
