package com.liupeng.dubbo.api.callback;

public class NotifyImpl implements Notify {
    @Override public void onreturn(String msg, String id) {
        System.out.println("onreturn(param: "+id+", result:"+msg+")");
    }

    @Override public void onthrow(Throwable ex, String id) {
        System.out.println("onthrow(param"+id+", throwable:"+ex);
    }
}
