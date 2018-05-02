package com.liupeng.learning.lamdar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LamdarTest {
    public static void main(String[] args) {
        testGroupingBy();
    }

    public static void testGroupingBy(){
        List<Computer> list = genList();
        Map<String,List<Computer>> map = list.stream().collect(Collectors.groupingBy(Computer::getBrand));

    }

    public static List<Computer> genList() {
        List<Computer> list = new ArrayList<>();
        Computer c1 = new Computer();
        c1.setBrand("huawei");
        c1.setPrice(1000.0);
        Computer c2 = new Computer();
        c2.setBrand("lenovo");
        c2.setPrice(1000.0);
        Computer c3 = new Computer();
        c3.setBrand("lenovo");
        c3.setPrice(2000.0);

        list.add(c1);
        list.add(c2);
        list.add(c3);

        return list;
    }
}




class Computer {
    String brand;

    Double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
