package com.liupeng.learning.xml.jaxb;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HotelResponse")
public class HaoQHotelResponse {

    @XmlElementWrapper(name = "HotelList")
    @XmlElement(required = true, name = "Hotel")
    List<HaoQHotel> hotelList;

    public List<HaoQHotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<HaoQHotel> hotelList) {
        this.hotelList = hotelList;
    }

}
