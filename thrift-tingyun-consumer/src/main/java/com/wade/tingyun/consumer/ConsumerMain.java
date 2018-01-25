package com.wade.tingyun.consumer;

import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by tingyun on 2017/12/19.
 */
public class ConsumerMain {
    public static void main(String[] args) throws TException {
        // 设置调用的服务地址-端口
        TTransport transport = new TSocket("localhost", 7911);
        //使用二进制协议
        TProtocol protocol=new TBinaryProtocol(transport);
        //使用的接口
        Hello.Client client=new Hello.Client(protocol);
        //打开socket
        transport.open();
        String result=client.helloString("wade");
        //client.helloBoolean(true);
        //client.helloInt(25);
        //client.helloWorld();
        //client.helloNull();
        System.err.println(result);
        transport.close();

    }
}
