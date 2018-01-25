package com.wade.tingyun.provider.service;

import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;



public class HelloNioImpl implements Hello.AsyncIface {
    @Override
    public String helloString(String para) throws TException {
        return para;
    }

    @Override
    public int helloInt(int para) throws TException {
        return 0;
    }

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        return false;
    }

    @Override
    public void helloWorld() throws TException {

    }

    @Override
    public String helloNull() throws TException {
        return null;
    }

    @Override
    public void helloString(String para, AsyncMethodCallback resultHandler) throws TException {
        System.err.println(para);
    }

    @Override
    public void helloInt(int para, AsyncMethodCallback resultHandler) throws TException {
        System.err.println(para);
    }

    @Override
    public void helloBoolean(boolean para, AsyncMethodCallback resultHandler) throws TException {
        System.err.println(para);
    }

    @Override
    public void helloWorld(AsyncMethodCallback resultHandler) throws TException {
        System.err.println(" ...");
    }

    @Override
    public void helloNull(AsyncMethodCallback resultHandler) throws TException {
        System.err.println("...");
    }
}
