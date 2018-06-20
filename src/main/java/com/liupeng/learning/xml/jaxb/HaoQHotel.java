package com.liupeng.learning.xml.jaxb;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Hotel")
public class HaoQHotel {
    @XmlElement(name = "Star")
    private Integer star;
    @XmlElement(name = "Introduction")
    private String introduction;
    @XmlElement(name = "HotelID")
    private Integer hotelID;
    @XmlElement(name = "Latitude")
    private String latitude;
    @XmlElement(name = "Longitude")
    private String longitude;
    @XmlElement(name = "CityID")
    private Integer cityID;
    @XmlElement(name = "NameChn")
    private String nameChn;
    @XmlElement(name = "NameEng")
    private String nameEng;
    @XmlElement(name = "Address")
    private String address;
    @XmlElement(name = "AddressEng")
    private String addressEng;
    @XmlElement(name = "CommentScore")
    private BigDecimal commentScore;
    @XmlElement(name = "Remarks")
    private String remarks;

    @XmlElementWrapper(name = "ImageList")
    @XmlElement(required = true, name = "Image")
    private List<HaoQHotelImage> imageList;

    @XmlElementWrapper(name = "Facilities")
    @XmlElement(required = true, name = "Facility")
    private List<HaoQFacility> facilities;

    public List<HaoQHotelImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<HaoQHotelImage> imageList) {
        this.imageList = imageList;
    }

    public List<HaoQFacility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<HaoQFacility> facilities) {
        this.facilities = facilities;
    }

    public BigDecimal getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(BigDecimal commentScore) {
        this.commentScore = commentScore;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressEng() {
        return addressEng;
    }

    public void setAddressEng(String addressEng) {
        this.addressEng = addressEng;
    }

}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Facility")
class HaoQFacility {
    @XmlElement(name = "NameChn")
    private String nameChn;
    @XmlElement(name = "NameEng")
    private String nameEng;
    @XmlElement(name = "FacDesc")
    private String facDesc;

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

    public String getFacDesc() {
        return facDesc;
    }

    public void setFacDesc(String facDesc) {
        this.facDesc = facDesc;
    }

}

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Image")
class HaoQHotelImage {
    @XmlElement(name = "ImgURL")
    private String imgURL;
    @XmlElement(name = "ImgTitle")
    private String imgTitle;

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
