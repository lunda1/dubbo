package com.liupeng.learning.xml.dom4j;

public enum HaoQCityFieldEnum {
    COUNTRYID(0,"CountryID"),
    STATEID(1,"StateID"),
    CITYID(2,"CityID"),
    NAMECHN(3,"NameChn"),
    NAMEENG(4,"NameEng"),
    STATENAMECHN(5,"StateNameChn"),
    STATENAMEENG(6,"StateNameEng");

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

    HaoQCityFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
