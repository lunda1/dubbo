package com.liupeng.dubbo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoServiceImpl2 implements DemoService {
    @Override
    public String sayHello(String name) {
        try {
            System.out.println("-2-");
            SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello " + name;
    }

    @Override public List<Integer> listInt(List<Integer> args) throws InterruptedException {
        System.out.println(JSON.toJSONString(args));
        List<Integer> list = new ArrayList<>();
        list.add(21);
        list.add(22);
        list.add(23);
        return list;
    }
}
