package com.liupeng.learning.xml.dom4j;

public enum HaoQHotelImageListFieldEnum {
    IMAGE(0,"Image");

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

    HaoQHotelImageListFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
