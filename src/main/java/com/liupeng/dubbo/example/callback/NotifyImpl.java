package com.liupeng.dubbo.example.callback;

public class NotifyImpl implements Notify {
    @Override public void onreturn(String msg, Integer id) {
        System.out.println("onreturn(param: "+id+", result:"+msg+")");
    }

    @Override public void onthrow(Throwable ex, Integer id) {
        System.out.println("onthrow(throwable:"+ex+", param"+id+")");
    }
}
