package com.liupeng.learning.xml.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HotelResponse")
public class HaoQCityResponse {

    @XmlElementWrapper(name = "CityList")
    @XmlElement(required = true, name = "City")
    List<HaoQCity> cityList;

    public List<HaoQCity> getCityList() {
        return cityList;
    }

    public void setCityList(List<HaoQCity> cityList) {
        this.cityList = cityList;
    }
}
