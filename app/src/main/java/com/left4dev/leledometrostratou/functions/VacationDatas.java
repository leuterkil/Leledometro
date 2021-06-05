package com.left4dev.leledometrostratou.functions;

public class VacationDatas {

    private String Type,startDate,endDate;

    public VacationDatas(String type, String startDate, String endDate) {
        this.Type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
