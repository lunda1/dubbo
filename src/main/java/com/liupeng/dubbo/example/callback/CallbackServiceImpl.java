package com.liupeng.dubbo.example.callback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CallbackServiceImpl implements CallbackService {

    private final Map<String, CallbackListener> listeners = new ConcurrentHashMap<>();

    public CallbackServiceImpl() {
        Thread t = new Thread(new Runnable() {
            @Override public void run() {
                while (true) {
                    try{
                        for (Map.Entry<String,CallbackListener> entry : listeners.entrySet()) {
                            try {
                                entry.getValue().changed(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                listeners.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void addListener(String key, CallbackListener listener) {
        listeners.put(key,listener);
        listener.changed(getChanged(key));
    }

    @Override
    public void addListener2(String key, CallbackListener listener) {
        listener.changed(getChanged(key));
    }

    @Override public String buildName(Integer id) {
        return "name_"+id;
    }

    //在调用回调函数前调用getChanged方法，说明参数经过了服务器的处理
    private String getChanged(String key) {
        return "Changed: "+key+" "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
