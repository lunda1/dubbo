package com.liupeng.learning.xml.dom4j;

public enum HaoQHotelFieldEnum {
    STAR(0,"Star"),
    INTRODUCTION(1,"Introduction"),
    HOTELID(2,"HotelID"),
    LATITUDE(3,"Latitude"),
    LONGITUDE(4,"Longitude"),
    CITYID(5,"CityID"),
    NAMECHN(6,"NameChn"),
    NAMEENG(7,"NameEng"),
    ADDRESS(8,"Address"),
    IMAGELIST(9,"ImageList"),
    FACILITIES(10,"Facilities"),
    ADDRESSENG(11,"AddressEng"),
    COMMENTSCORE(12,"CommentScore"),
    REMARKS(13,"Remarks");

    private int index;
    private String xmlTagName;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getXmlTagName() {
        return xmlTagName;
    }

    public void setXmlTagName(String xmlTagName) {
        this.xmlTagName = xmlTagName;
    }

    HaoQHotelFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
