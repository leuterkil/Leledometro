package com.left4dev.leledometrostratou.functions;

public class PenaltyDatas {

    private String Type,Days;

    public PenaltyDatas(String Type,String Days)
    {
        this.Days = Days;
        this.Type = Type;
    }


    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setDays(String days) {
        Days = days;
    }

    public String getDays() {
        return Days;
    }
}
