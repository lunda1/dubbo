package com.liupeng.learning.fastjson;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class TestFastjson {
    public static void main(String[] args) {
        String d = "[{\"id\":1,\"name\":\"name1\"},{\"id\":2,\"name\":\"name2\"}]";
        System.out.println(JSON.parseArray(d,InfoDTO.class));

        List<InfoDTO> list = new ArrayList<>();
        InfoDTO d1 = new InfoDTO();
        d1.setId(1);
        d1.setName("name1");

        InfoDTO d2 = new InfoDTO();
        d2.setId(2);
        d2.setName("name2");

        list.add(d1);
        list.add(d2);

        String ss = JSON.toJSONString(list);

        System.out.println(ss);

        List<InfoDTO> ll = JSON.parseArray(ss, InfoDTO.class);
        System.out.println(ll.size());

    }
}

class InfoDTO{
    int id;
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
