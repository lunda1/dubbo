package com.liupeng.dubbo.api;

public interface MyNotify {
    public void onreturn(String returnMsg, String param);
    public void onthrow(Throwable ex, String param);
}
