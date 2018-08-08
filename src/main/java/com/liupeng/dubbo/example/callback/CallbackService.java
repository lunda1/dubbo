package com.liupeng.dubbo.example.callback;

public interface CallbackService {
    void addListener(String key, CallbackListener listener);

    void addListener2(String key, CallbackListener listener);

    String buildName(Integer id);
}
