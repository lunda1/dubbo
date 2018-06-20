package com.liupeng.dubbo.loadbalance;

import com.alibaba.fastjson.JSON;
import com.liupeng.dubbo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TestLoadBalanceClient {
    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext localContext
            = new ClassPathXmlApplicationContext("dubbo-config-consumer.xml");
        localContext.start();
        DemoService service = (DemoService) localContext.getBean("demoService");

        List<Long> elapseList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            long current = System.currentTimeMillis();
            String hiMessage = service.sayHello("baeldung");
            elapseList.add(System.currentTimeMillis() - current);
            SECONDS.sleep(1);
        }

        System.out.println(JSON.toJSONString(elapseList));
    }
}
