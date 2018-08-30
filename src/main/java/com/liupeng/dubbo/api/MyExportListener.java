package com.liupeng.dubbo.api;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.listener.ExporterListenerAdapter;

@Activate
public class MyExportListener extends ExporterListenerAdapter {

    @Override
    public void exported(Exporter<?> exporter) throws RpcException {
        Class<?> clz = exporter.getInvoker().getInterface();
        System.out.println("export------------"+clz.getName()+"----------"+exporter.getInvoker().getUrl());
    }
}
