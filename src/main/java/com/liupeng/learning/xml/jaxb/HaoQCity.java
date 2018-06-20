package com.liupeng.learning.xml.jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "City")
public class HaoQCity {
    @XmlElement(name = "CountryID")
    private Integer countryID;
    @XmlElement(name = "StateID")
    private Integer stateID;
    @XmlElement(name = "CityID")
    private Integer cityID;
    @XmlElement(name = "NameChn")
    private String nameChn;
    @XmlElement(name = "NameEng")
    private String nameEng;
    @XmlElement(name = "StateNameChn")
    private String stateNameChn;
    @XmlElement(name = "StateNameEng")
    private String stateNameEng;


    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }
    public String getNameChn() {
        return nameChn;
    }

    public void setNameChn(String nameChn) {
        this.nameChn = nameChn;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getStateNameChn() {
        return stateNameChn;
    }

    public void setStateNameChn(String stateNameChn) {
        this.stateNameChn = stateNameChn;
    }

    public String getStateNameEng() {
        return stateNameEng;
    }

    public void setStateNameEng(String stateNameEng) {
        this.stateNameEng = stateNameEng;
    }

}
