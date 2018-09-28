package com.liupeng.dubbo.api.callback;

import java.io.Serializable;

public interface CallbackListener extends Serializable{
    void changed(String msg);
}
