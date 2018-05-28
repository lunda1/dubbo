package com.liupeng.learning.xml.dom4j;

public enum HaoQHotelImageListImageFieldEnum {
    IMGURL(0,"ImgURL"),
    IMGTITLE(0,"ImgTitle");;

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

    HaoQHotelImageListImageFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
