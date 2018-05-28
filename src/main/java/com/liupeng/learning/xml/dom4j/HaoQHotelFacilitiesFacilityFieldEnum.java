package com.liupeng.learning.xml.dom4j;

public enum HaoQHotelFacilitiesFacilityFieldEnum {
    NAMECHN(0,"NameChn"),
    NAMEENG(0,"NameEng"),
    FACDESC(0,"FacDesc");

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

    HaoQHotelFacilitiesFacilityFieldEnum(int index, String xmlTagName){
        this.index = index;
        this.xmlTagName = xmlTagName;
    }
}
