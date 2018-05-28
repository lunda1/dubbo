package com.liupeng.learning.xml.dom4j;

public enum HaoQCountryFieldEnum {
    NAMECHN(0,"NameChn"),
    NAMEENG(1,"NameEng"),
    COUNTRYID(2,"CountryID");

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

    HaoQCountryFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
