package com.example.ecohomepro;
public class MyRequest {
    public String getNote() {
        return note;
    }

    public String getInfo() {
        return info;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getMobile() {
        return mobile;
    }

    public String getService() {
        return service;
    }

    public String getAddress() {
        return address;
    }

    public String getServed() {
        return served;
    }

    public void setServed(String served) {
        this.served = served;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MyRequest(long id, String note, String info, String date, String time, String mobile, String service, String address, String served) {
        this.note = note;
        this.info = info;
        this.date = date;
        this.time = time;
        this.id=id;
        this.mobile = mobile;
        this.service = service;
        this.served=served;
        this.address = address;
    }

    String note, info,date,time,mobile,service,address;
    String served;
    long id;
}
