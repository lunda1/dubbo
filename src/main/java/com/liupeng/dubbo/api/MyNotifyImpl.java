package com.liupeng.dubbo.api;

public class MyNotifyImpl implements MyNotify{
    @Override
    public void onreturn(String returnMsg, String param) {
        System.out.println("[MyNotify---onreturn]");
    }

    @Override
    public void onthrow(Throwable ex, String param) {
        System.out.println("[MyNotify---onreturn]");
    }
}
