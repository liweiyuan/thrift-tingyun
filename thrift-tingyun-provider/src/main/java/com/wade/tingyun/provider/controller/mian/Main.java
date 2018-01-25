package com.wade.tingyun.provider.controller.mian;

import com.wade.tingyun.provider.service.HelloImpl;
import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.protocol.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by tingyun on 2017/12/19.
 */
public class Main implements Runnable {

    private int port;

    public Main(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        startServer();
    }

    private void startServer() {
        if (port == 7911) {
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
            System.err.println("开启thrift服务器，监听端口：7911");
            server.serve();
        } else if (port == 7912) {
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
            System.err.println("开启thrift服务器，监听端口：7912");
            server.serve();
        } else if (port == 7913) {
            //设置端口
            TServerSocket serverTransport = null;
            try {
                serverTransport = new TServerSocket(7913);
            } catch (TTransportException e) {
                e.printStackTrace();
            }
            //设置协议工厂TBinaryProtocol.Factory
            TJSONProtocol.Factory factory = new TJSONProtocol.Factory();
            //关联处理器与hello服务器
            Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            //使用单线程标准阻塞I/O模型
            TServer.Args simpleArgs = new TServer.Args(serverTransport)
                    .processor(processor)
                    .protocolFactory(factory);
            TServer server = new TSimpleServer(simpleArgs);
            System.err.println("开启thrift服务器，监听端口:7913");
            server.serve();
        } else if (port == 7914) {
            //设置端口
            TServerSocket serverTransport = null;
            try {
                serverTransport = new TServerSocket(7914);
            } catch (TTransportException e) {
                e.printStackTrace();
            }
            //设置协议工厂TBinaryProtocol.Factory
            TTupleProtocol.Factory factory = new TTupleProtocol.Factory();
            //关联处理器与hello服务器
            Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            //使用单线程标准阻塞I/O模型
            TServer.Args simpleArgs = new TServer.Args(serverTransport)
                    .processor(processor)
                    .protocolFactory(factory);
            TServer server = new TSimpleServer(simpleArgs);
            System.err.println("开启thrift服务器，监听端口:7914");
            server.serve();
        } else if (port == 7915) {
//设置端口
            TServerSocket serverTransport = null;
            try {
                serverTransport = new TServerSocket(7915);
            } catch (TTransportException e) {
                e.printStackTrace();
            }
            //设置协议工厂TBinaryProtocol.Factory
            TSimpleJSONProtocol.Factory factory = new TSimpleJSONProtocol.Factory();
            //关联处理器与hello服务器
            Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            //使用单线程标准阻塞I/O模型
            TServer.Args simpleArgs = new TServer.Args(serverTransport)
                    .processor(processor)
                    .protocolFactory(factory);
            TServer server = new TSimpleServer(simpleArgs);
            System.err.println("开启thrift服务器，监听端口:7915");
            server.serve();
        }
    }
}
