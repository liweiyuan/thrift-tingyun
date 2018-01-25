package com.wade.tingyun.consumer.callback;

import org.apache.thrift.async.AsyncMethodCallback;

import java.util.concurrent.CountDownLatch;



public class MethodCallback implements AsyncMethodCallback {

    private CountDownLatch latch;

    public MethodCallback(CountDownLatch latch) {
        this.latch = latch;
    }

    Object response=null;

    @Override
    public void onComplete(Object o) {
        this.response=o;
    }

    @Override
    public void onError(Exception e) {
        System.err.println(e.getMessage());
    }

    //自定义方法获取结果
    public Object getResult(){
        return  response;
    }
}
