package com.liupeng.dubbo.api;


import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate(group = {Constants.CONSUMER, Constants.PROVIDER})
public class MyFilter implements Filter {
    @Override public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("filter-------------"+invoker.getUrl());
        return invoker.invoke(invocation);
    }
}
