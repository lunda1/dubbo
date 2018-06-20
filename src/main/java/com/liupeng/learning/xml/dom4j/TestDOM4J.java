package com.liupeng.learning.xml.dom4j;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestDOM4J {
    public static void main(String[] args) {
        //1.城市列表
        //testCityList();
        //2.国家列表
        //testCountryList();
        //3.酒店列表
        testHotelList();

    }

    public static void testCityList(){
        File f = new File("src/main/resources/xml/cityList.xml");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(f);
            Element root = document.getRootElement();

            for (Iterator<Element> iter = root.elementIterator();iter.hasNext();) {
                Element cityListTag = iter.next();
                System.out.println(cityListTag.getName());
                Iterator<Element> iter1 = cityListTag.elementIterator();
                while (iter1.hasNext()) {
                    Element cityTag = iter1.next();
                    System.out.println(" "+cityTag.getName());
                    Iterator<Element> iter2 = cityTag.elementIterator();
                    HaoQCity city = new HaoQCity();
                    while (iter2.hasNext()) {
                        Element fieldTag = iter2.next();
                        if (HaoQCityFieldEnum.COUNTRYID.getXmlTagName().equals(fieldTag.getName())){
                            city.setCountryId(!Strings.isNullOrEmpty(fieldTag.getStringValue()) ? Integer.parseInt(fieldTag.getStringValue()) : 0);
                        } else if (HaoQCityFieldEnum.STATEID.getXmlTagName().equals(fieldTag.getName())) {
                            city.setStateId(!Strings.isNullOrEmpty(fieldTag.getStringValue()) ? Integer.parseInt(fieldTag.getStringValue()) : 0);
                        } else if (HaoQCityFieldEnum.CITYID.getXmlTagName().equals(fieldTag.getName())) {
                            city.setCityId(!Strings.isNullOrEmpty(fieldTag.getStringValue()) ? Integer.parseInt(fieldTag.getStringValue()) : 0);
                        } else if (HaoQCityFieldEnum.NAMECHN.getXmlTagName().equals(fieldTag.getName())) {
                            city.setNameChn(fieldTag.getStringValue());
                        } else if (HaoQCityFieldEnum.NAMEENG.getXmlTagName().equals(fieldTag.getName())) {
                            city.setNameEng(fieldTag.getStringValue());
                        } else if (HaoQCityFieldEnum.STATENAMECHN.getXmlTagName().equals(fieldTag.getName())) {
                            city.setStateNameChn(fieldTag.getStringValue());
                        } else if (HaoQCityFieldEnum.STATENAMEENG.getXmlTagName().equals(fieldTag.getName())) {
                            city.setStateNameEng(fieldTag.getStringValue());
                        }

                    }
                    System.out.println(JSON.toJSONString(city));
                }
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void testCountryList(){
        File f = new File("src/main/resources/xml/countryList.xml");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(f);
            Element root = document.getRootElement();

            for (Iterator<Element> iter = root.elementIterator();iter.hasNext();) {
                Element countryListTag = iter.next();
                System.out.println(countryListTag.getName());
                Iterator<Element> iter1 = countryListTag.elementIterator();
                while (iter1.hasNext()) {
                    Element countryTag = iter1.next();
                    System.out.println(" "+countryTag.getName());
                    Iterator<Element> iter2 = countryTag.elementIterator();
                    HaoQCountry country = new HaoQCountry();
                    while (iter2.hasNext()) {
                        Element fieldTag = iter2.next();
                        if (HaoQCountryFieldEnum.COUNTRYID.getXmlTagName().equals(fieldTag.getName())){
                            country.setCountryId(Strings.isNullOrEmpty(fieldTag.getStringValue()) ? Integer.parseInt(fieldTag.getStringValue()) : 0);
                        } else if (HaoQCountryFieldEnum.NAMECHN.getXmlTagName().equals(fieldTag.getName())) {
                            country.setNameChn(fieldTag.getStringValue());
                        } else if (HaoQCountryFieldEnum.NAMEENG.getXmlTagName().equals(fieldTag.getName())) {
                            country.setNameEng(fieldTag.getStringValue());
                        }

                    }
                    System.out.println(JSON.toJSONString(country));
                }
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static void testHotelList(){
        File f = new File("src/main/resources/xml/hotellist_cityId1.xml");
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(f);
            Element root = document.getRootElement();

            for (Iterator<Element> iter = root.elementIterator();iter.hasNext();) {
                Element hotelListTag = iter.next();
                System.out.println(hotelListTag.getName());
                Iterator<Element> iter1 = hotelListTag.elementIterator();
                // TODO: 2018/5/28 test code;
                int count = 0;

                while (iter1.hasNext()) {
                    // TODO: 2018/5/28 test code;
                    if (count > 0) {
                        return;
                    }
                    Element hotelTag = iter1.next();
                    System.out.println(" "+hotelTag.getName());
                    Iterator<Element> iter2 = hotelTag.elementIterator();
                    HaoQHotel haoQHotel = new HaoQHotel();
                    while (iter2.hasNext()) {
                        Element hotelFieldTag = iter2.next();
                        if (HaoQHotelFieldEnum.STAR.getXmlTagName().equals(hotelFieldTag.getName())){
                            haoQHotel.setStar(!Strings.isNullOrEmpty(hotelFieldTag.getStringValue())? Integer.parseInt(hotelFieldTag.getStringValue()) : 0);
                        } else if (HaoQHotelFieldEnum.INTRODUCTION.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setIntroduction(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.HOTELID.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setHotelID(!Strings.isNullOrEmpty(hotelFieldTag.getStringValue())? Integer.parseInt(hotelFieldTag.getStringValue()) : 0);
                        } else if (HaoQHotelFieldEnum.LATITUDE.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setLatitude(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.LONGITUDE.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setLongitude(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.CITYID.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setCityID(!Strings.isNullOrEmpty(hotelFieldTag.getStringValue())? Integer.parseInt(hotelFieldTag.getStringValue()) : 0);
                        } else if (HaoQHotelFieldEnum.NAMECHN.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setNameChn(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.NAMEENG.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setNameEng(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.ADDRESS.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setAddress(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.ADDRESSENG.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setAddressEng(hotelFieldTag.getStringValue());
                        } else if (HaoQHotelFieldEnum.IMAGELIST.getXmlTagName().equals(hotelFieldTag.getName())) {
                            HaoQHotelImageList haoQHotelImageListObj = new HaoQHotelImageList();
                            haoQHotel.setImageList(haoQHotelImageListObj);
                            List<HaoQHotelImageListImage> haoQHotelImageListImageObj = new ArrayList<>();
                            haoQHotelImageListObj.setImage(haoQHotelImageListImageObj);

                            Iterator<Element> iter3 = hotelFieldTag.elementIterator();
                            while (iter3.hasNext()) {
                                Element imageListFieldTag = iter3.next();
                                if (HaoQHotelImageListFieldEnum.IMAGE.getXmlTagName().equals(imageListFieldTag.getName())) {
                                    Iterator<Element> iter4 = imageListFieldTag.elementIterator();
                                    HaoQHotelImageListImage imgDTO = new HaoQHotelImageListImage();
                                    while (iter4.hasNext()) {
                                        Element imageListImageFieldTag = iter4.next();
                                        if (HaoQHotelImageListImageFieldEnum.IMGURL.getXmlTagName().equals(imageListImageFieldTag.getName())) {
                                            imgDTO.setImgURL(imageListImageFieldTag.getStringValue());
                                        } else if (HaoQHotelImageListImageFieldEnum.IMGTITLE.getXmlTagName().equals(imageListImageFieldTag.getName())) {
                                            imgDTO.setImgTitle(imageListImageFieldTag.getStringValue());
                                        }
                                    }
                                    haoQHotelImageListImageObj.add(imgDTO);
                                }
                            }//while

                        } else if (HaoQHotelFieldEnum.FACILITIES.getXmlTagName().equals(hotelFieldTag.getName())) {
                            HaoQHotelFacilities haoQHotelFacilities = new HaoQHotelFacilities();
                            haoQHotel.setFacilities(haoQHotelFacilities);
                            List<HaoQFacilitiesFacility> facilityList = new ArrayList<>();
                            haoQHotelFacilities.setFacility(facilityList);

                            Iterator<Element> iter3 = hotelFieldTag.elementIterator();
                            while (iter3.hasNext()) {
                                Element facilitiesFieldTag = iter3.next();
                                if (HaoQHotelFacilitiesFieldEnum.FACILITY.getXmlTagName().equals(facilitiesFieldTag.getName())) {
                                    HaoQFacilitiesFacility facility = new HaoQFacilitiesFacility();
                                    Iterator<Element> iter4 = facilitiesFieldTag.elementIterator();
                                    while (iter4.hasNext()) {
                                        Element facilityTag = iter4.next();
                                        if (HaoQHotelFacilitiesFacilityFieldEnum.NAMECHN.getXmlTagName().equals(facilityTag.getName())) {
                                            facility.setNameChn(facilityTag.getStringValue());
                                        } else if (HaoQHotelFacilitiesFacilityFieldEnum.NAMEENG.getXmlTagName().equals(facilityTag.getName())) {
                                            facility.setNameEng(facilityTag.getStringValue());
                                        } else if (HaoQHotelFacilitiesFacilityFieldEnum.FACDESC.getXmlTagName().equals(facilityTag.getName())) {
                                            facility.setFacDesc(facilityTag.getStringValue());
                                        }
                                    }
                                    facilityList.add(facility);
                                }
                            }//while

                        } else if (HaoQHotelFieldEnum.COMMENTSCORE.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setCommentScore(!Strings.isNullOrEmpty(hotelFieldTag.getStringValue()) ? new BigDecimal(hotelFieldTag.getStringValue()).setScale(2) : BigDecimal.ZERO);
                        } else if (HaoQHotelFieldEnum.REMARKS.getXmlTagName().equals(hotelFieldTag.getName())) {
                            haoQHotel.setRemarks(hotelFieldTag.getStringValue());
                        }

                    }//while
                    System.out.println(JSON.toJSONString(haoQHotel));
                    // TODO: 2018/5/28 test code
                    count++;
                }//while
            }



        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}



