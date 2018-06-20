package com.liupeng.learning.xml.dom4j;

import java.math.BigDecimal;
import java.util.List;

public class HaoQHotel {

    private Integer star;
    private String introduction;
    private Integer hotelID;
    private String latitude;
    private String longitude;
    private Integer cityID;
    private String nameChn;
    private String nameEng;
    private String address;
    private String addressEng;
    private HaoQHotelImageList imageList;
    private HaoQHotelFacilities facilities;
    private BigDecimal commentScore;
    private String remarks;

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

    public HaoQHotelImageList getImageList() {
        return imageList;
    }

    public void setImageList(HaoQHotelImageList imageList) {
        this.imageList = imageList;
    }

    public HaoQHotelFacilities getFacilities() {
        return facilities;
    }

    public void setFacilities(HaoQHotelFacilities facilities) {
        this.facilities = facilities;
    }

}

class HaoQHotelFacilities {
    private List<HaoQFacilitiesFacility> facility;

    public List<HaoQFacilitiesFacility> getFacility() {
        return facility;
    }

    public void setFacility(List<HaoQFacilitiesFacility> facility) {
        this.facility = facility;
    }
}

class HaoQFacilitiesFacility {
    private String nameChn;
    private String nameEng;
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

class HaoQHotelImageList {
    private List<HaoQHotelImageListImage> image;

    public List<HaoQHotelImageListImage> getImage() {
        return image;
    }

    public void setImage(List<HaoQHotelImageListImage> image) {
        this.image = image;
    }

}

class HaoQHotelImageListImage {
    private String imgURL;

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
