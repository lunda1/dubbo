package com.liupeng.dubbo.example.callback;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static java.util.concurrent.TimeUnit.SECONDS;

public class ConsumerCallback {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"example/callback/dubbo-config-consumer-callback.xml"});
        context.start();
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        callbackService.addListener("testkey", new CallbackListener() {
            @Override public void changed(String msg) {
                System.out.println("callback1: " + msg);
                /*try {
                    SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        });

        callbackService.addListener2("testkey2", new CallbackListener() {
            @Override public void changed(String msg) {
                System.out.println("callback1: " + msg);
            }
        });

        callbackService.buildName(10);

        Thread.currentThread().join();

    }
}
