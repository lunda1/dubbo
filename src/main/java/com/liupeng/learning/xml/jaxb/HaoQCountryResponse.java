package com.liupeng.learning.xml.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HotelResponse")
public class HaoQCountryResponse {

    @XmlElementWrapper(name = "CountryList")
    @XmlElement(required = true, name = "Country")
    List<HaoQCountry> countryList;

    public List<HaoQCountry> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<HaoQCountry> countryList) {
        this.countryList = countryList;
    }

}
