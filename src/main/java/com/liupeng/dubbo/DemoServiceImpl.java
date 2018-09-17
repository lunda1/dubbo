package com.liupeng.dubbo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) throws InterruptedException {
        System.out.println("-1-");
        SECONDS.sleep(2);
        return "Hello " + name;
    }

    @Override public List<Integer> listInt(List<Integer> args) throws InterruptedException {
        System.out.println(JSON.toJSONString(args));
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);
        return list;
    }
}
