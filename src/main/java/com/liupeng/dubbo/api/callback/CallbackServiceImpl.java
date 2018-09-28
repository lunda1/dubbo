package com.liupeng.dubbo.api.callback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CallbackServiceImpl implements CallbackService {

    @Override
    public String addListener(String key, CallbackListener listener) {
        listener.changed(getChanged(key)); // send change notification
        return getChanged(key);
    }

    private String getChanged(String key) {
        return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
