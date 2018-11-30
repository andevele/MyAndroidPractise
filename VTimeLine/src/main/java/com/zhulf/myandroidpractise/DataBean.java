package com.zhulf.myandroidpractise;


public class DataBean {
    private String time;
    private String address;

    public DataBean(String time, String address) {
        this.time = time;
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }
}
