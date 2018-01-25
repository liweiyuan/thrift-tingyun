package com.wade.tingyun.provider.controller.bean;

import com.wade.tingyun.provider.service.HelloImpl;
import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.stereotype.Service;

/**
 * Created by tingyun on 2017/12/19.
 */
@Service
public class ThriftBean {


    public String getResult() throws TTransportException {
        Thread  thread =new Thread(new Runnable() {
            @Override
            public void run() {
                //设置端口
                TServerSocket serverTransport = null;
                try {
                    serverTransport = new TServerSocket(7911);
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
                //设置协议工厂TBinaryProtocol.Factory
                TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
                //关联处理器与hello服务器
                Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
                //使用单线程标准阻塞I/O模型
                TServer.Args simpleArgs = new TServer.Args(serverTransport)
                        .processor(processor)
                        .protocolFactory(factory);
                TServer server = new TSimpleServer(simpleArgs);
                System.out.println("开启thrift服务器，监听端口：7911");
                server.serve();
            }
        });
        thread.start();
        return "TBinaryProtocol sport：7911";
    }

    public String startTCompact() {
        Thread  thread =new Thread(new Runnable() {
            @Override
            public void run() {
                //设置端口
                TServerSocket serverTransport = null;
                try {
                    serverTransport = new TServerSocket(7912);
                } catch (TTransportException e) {
                    e.printStackTrace();
                }
                //设置协议工厂TBinaryProtocol.Factory
                TCompactProtocol.Factory factory = new TCompactProtocol.Factory();
                //关联处理器与hello服务器
                Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
                //使用单线程标准阻塞I/O模型
                TServer.Args simpleArgs = new TServer.Args(serverTransport)
                        .processor(processor)
                        .protocolFactory(factory);
                TServer server = new TSimpleServer(simpleArgs);
                System.out.println("开启thrift服务器，监听端口：7912");
                server.serve();
            }
        });
        thread.start();
        return "TCompactProtocol port：7912";
    }

    /*public String startJson() {
    }*/
}
