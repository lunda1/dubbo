package com.liupeng.dubbo.api.callback;

public interface CallbackService {
    String addListener(String key, CallbackListener listener);
}
