package com.wade.tingyun.provider.service;

import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.TException;

/**
 * Created by tingyun on 2017/12/19.
 */
public class HelloImpl implements Hello.Iface {
    @Override
    public boolean helloBoolean(boolean para) throws TException {
        System.err.println("this is bool:"+para);
        return para;
    }

    @Override
    public void helloWorld() throws TException {
        System.err.println("this is helloWorld.");
    }

    @Override
    public int helloInt(int para) throws TException {
        System.err.println("this is a int ");
        return para;
    }

    @Override
    public String helloNull() throws TException {
        return "helloNull";
    }

    @Override
    public String helloString(String para) throws TException {
        System.err.println("This is return :"+para);
        return para;
    }
}
