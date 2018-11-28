package com.liupeng.dubbo.api.callback;

public interface Notify {
    public void onreturn(String msg, String id);
    public void onthrow(Throwable ex, String id);
}
