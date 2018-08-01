package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;

@Adaptive
public class MyListener extends ExporterListenerAdapter {
    public void exported(Exporter<?> exporter) throws RpcException {
        Class<?> clz = exporter.getInvoker().getInterface();
        System.out.println("------------"+clz.getName());
    }
}
