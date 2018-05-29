package com.liupeng.learning.xml.jaxb;

import com.alibaba.fastjson.JSON;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class TestJaxb {
    public static void main(String[] args) throws IOException {
        //1.转换城市
        //testCityXmlToBean();
        //2.转换国家
        //testCountryXmlToBean();
        //3.转换酒店
        testHotelXmlToBean();
    }

    public static void testCityXmlToBean() throws IOException {
        File file = new File("src/main/resources/io/tmp/HAOQIAO_test_static_xml/cityList/cityList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        HaoQCityResponse haoQCityHotelResponse = converyToJavaBean(sb.toString(),HaoQCityResponse.class);
        System.out.println(JSON.toJSONString(haoQCityHotelResponse));
    }

    public static void testCountryXmlToBean() throws IOException {
        File file = new File("src/main/resources/io/tmp/HAOQIAO_test_static_xml/countryList/countryList.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        HaoQCountryResponse haoQCityHotelResponse = converyToJavaBean(sb.toString(),HaoQCountryResponse.class);
        System.out.println(JSON.toJSONString(haoQCityHotelResponse));
    }

    public static void testHotelXmlToBean() throws IOException {
        File file = new File("src/main/resources/io/tmp/HAOQIAO_test_static_xml/hotelList/hotellist_cityId1.xml");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        br.close();
        HaoQHotelResponse haoQCityHotelResponse = converyToJavaBean(sb.toString(),HaoQHotelResponse.class);
        System.out.println(JSON.toJSONString(haoQCityHotelResponse));
    }

    public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
