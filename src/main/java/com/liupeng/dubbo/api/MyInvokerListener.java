package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;
import com.alibaba.dubbo.rpc.listener.InvokerListenerAdapter;

@Activate
public class MyInvokerListener extends InvokerListenerAdapter {

    @Override
    public void referred(Invoker<?> invoker) throws RpcException {
        Class<?> clz = invoker.getInterface();
        System.out.println("invoker------------"+clz.getName()+"------------"+invoker.getUrl().toFullString());
    }

}
