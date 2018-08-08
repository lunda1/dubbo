package com.liupeng.dubbo.example.callback;

public interface Notify {
    public void onreturn(String msg, Integer id);
    public void onthrow(Throwable ex, Integer id);
}
