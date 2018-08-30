package com.liupeng.dubbo.api;


import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

@Activate
public class MyFilter implements Filter {
    @Override public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("invoker-------------"+invoker.getUrl());
        return invoker.invoke(invocation);
    }
}
