package com.liupeng.learning.haoqjob;

public enum HaoQBasicDataFileNameEnum {
    CITY(0,"cityList"),
    COUNTRY(1,"countryList"),
    HOTEL(2,"hotellist_cityId");

    private Integer code;

    private String fileName;

    HaoQBasicDataFileNameEnum(Integer code, String fileName){
        this.code = code;
        this.fileName = fileName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
