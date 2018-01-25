package com.wade.tingyun.consumer.controller.bean;

import com.wade.tingyun.consumer.callback.MethodCallback;
import com.wade.tingyun.consumer.controller.bean.pool.TsoketPool;
import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.*;
import org.apache.thrift.transport.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by tingyun on 2017/12/19.
 */
@Service
public class ThriftBean {

    //采用对象池管理
    TsoketPool tsoketPool=new TsoketPool();


    public String getResult() {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("10.128.0.12", 7911);
        //使用二进制协议
        TProtocol protocol = new TBinaryProtocol(transport);
        //使用的接口
        Hello.Client client = new Hello.Client(protocol);
        //打开socket
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = client.helloString("wade");
        } catch (TException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        transport.close();
        return result;
    }

    public String getCompact1() {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("10.128.0.12", 7912);
        //使用二进制协议
        TProtocol protocol = new TCompactProtocol(transport);
        //使用的接口
        Hello.Client client = new Hello.Client(protocol);
        //打开socket
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = client.helloString("wade");
        } catch (TException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        transport.close();
        return result;
    }

    public Boolean getJson() {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("10.128.0.12", 7913);
        //使用二进制协议
        TProtocol protocol = new TJSONProtocol(transport);
        //使用的接口
        Hello.Client client = new Hello.Client(protocol);
        //打开socket
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        Boolean result = null;
        try {
            result = client.helloBoolean(true);
        } catch (TException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        transport.close();
        return result;
    }

    public int getSimple() {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("10.128.0.12", 7914);
        //使用二进制协议
        TProtocol protocol = new TTupleProtocol(transport);
        //使用的接口
        Hello.Client client = new Hello.Client(protocol);
        //打开socket
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = client.helloString("32");
        } catch (TException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        transport.close();
        return Integer.valueOf(result);
    }

    public String getTuple() {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("10.128.0.12", 7915);
        //使用二进制协议
        TProtocol protocol = new TSimpleJSONProtocol(transport);
        //使用的接口
        Hello.Client client = new Hello.Client(protocol);
        //打开socket
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = client.helloString("wade");
        } catch (TException e) {
            e.printStackTrace();
        }
        System.err.println(result);
        transport.close();
        return result;
    }

    //异步方式调用
    public void getNioBinary() {
        try {
            TProtocolFactory factory = new TBinaryProtocol.Factory();
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket("10.128.0.12", 10005);
            Hello.AsyncClient asyncClient = new Hello.AsyncClient(factory, clientManager, transport);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            asyncClient.helloString("wade", new MethodCallback(countDownLatch));

            //
            boolean wait;
            try {
                wait = countDownLatch.await(1, TimeUnit.SECONDS);
                System.err.println("latch.await =:" + wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            transport.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public void getNioHsHa() {
        try {
            TProtocolFactory factory = new TBinaryProtocol.Factory();
            TAsyncClientManager clientManager = new TAsyncClientManager();
            TNonblockingTransport transport = new TNonblockingSocket("10.128.0.12", 10006);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Hello.AsyncClient asyncClient = new Hello.AsyncClient(factory, clientManager, transport);

            asyncClient.helloString("wade", new MethodCallback(countDownLatch));
            //
            boolean wait;
            try {
                wait = countDownLatch.await(1, TimeUnit.SECONDS);
                System.err.println("latch.await =:" + wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public String getNioThread() throws Exception {


        //
        TSocket transport=null;
        try {
            transport = tsoketPool.borrowObject();
            TProtocol protocol = new TBinaryProtocol(transport);
            //CountDownLatch countDownLatch=new CountDownLatch(1);
            Hello.Client client = new Hello.Client(protocol);
            //transport.open();
            String result = client.helloString("wade");
            return result;
        } catch (TException e) {
            e.printStackTrace();
        }finally{
            tsoketPool.returnObject(transport);
            //transport.close();
        }
        return "";
    }

    public void getSelector() throws IOException, TException {
        TProtocolFactory factory = new TBinaryProtocol.Factory();
        TAsyncClientManager clientManager = new TAsyncClientManager();
        TNonblockingTransport transport = new TNonblockingSocket("10.128.0.12", 10008);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Hello.AsyncClient asyncClient = new Hello.AsyncClient(factory, clientManager, transport);
        asyncClient.helloString("wade", new MethodCallback(countDownLatch));
        //asyncClient.helloString("wade",new MethodCallback());
        boolean wait;
        try {
            wait = countDownLatch.await(1, TimeUnit.SECONDS);
            System.out.println("latch.await =:" + wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        transport.close();
    }
}
