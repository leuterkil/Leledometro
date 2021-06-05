package com.left4dev.leledometrostratou.functions;

public class ServiceDatas {

    private String Type,Time,Date;

    public ServiceDatas(String type, String time, String date) {
        Type = type;
        Time = time;
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
