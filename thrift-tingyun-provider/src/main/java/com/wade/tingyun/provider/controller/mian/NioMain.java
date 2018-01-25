package com.wade.tingyun.provider.controller.mian;

import com.wade.tingyun.provider.service.HelloImpl;
import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;


public class NioMain implements Runnable {

    private int port;

    public NioMain(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        reStartNioServer();
    }

    private void reStartNioServer() {
        if (port == 10005) {
            TNonblockingServerTransport serverTransport = null;
            try {
                serverTransport = new TNonblockingServerSocket(port);
                TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
                Hello.Processor<Hello.Iface> processor =
                        new Hello.Processor<Hello.Iface>(new HelloImpl());
                TNonblockingServer.Args args = new TNonblockingServer.Args(serverTransport)
                        .processor(processor)
                        .protocolFactory(factory);
                TServer server = new TNonblockingServer(args);
                System.err.println("开启thrift异步服务器，监听端口：10005");
                server.serve();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        } else if (port == 10006) {
            try {
                TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);
                TTransportFactory tTransportFactory = new TFramedTransport.Factory();
                TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
                Hello.Processor<Hello.Iface> processor =
                        new Hello.Processor<Hello.Iface>(new HelloImpl());
                THsHaServer.Args args = new THsHaServer.Args(serverTransport)
                        .transportFactory(tTransportFactory)
                        .protocolFactory(factory)
                        .processor(processor);
                TServer server = new THsHaServer(args);
                System.err.println("开启thrift服务器，监听端口：10006");
                server.serve();
            } catch (TTransportException e) {
                e.printStackTrace();
            }

        } else if (port == 10007) {
            TServerSocket serverTransport=null;
            try {
                serverTransport = new TServerSocket(port);
                TBinaryProtocol.Factory factory = new TBinaryProtocol.Factory();
                Hello.Processor<Hello.Iface> processor =
                        new Hello.Processor<Hello.Iface>(new HelloImpl());
                TThreadPoolServer.Args args = new TThreadPoolServer.Args(serverTransport)
                        .protocolFactory(factory)
                        .processor(processor)
                        .minWorkerThreads(10)
                        .maxWorkerThreads(1000);
                TServer server = new TThreadPoolServer(args);
                System.err.println("开启thrift服务器，监听端口：10007");
                server.serve();
            } catch (TTransportException e) {
                e.printStackTrace();
            }finally{
                serverTransport.close();
            }
        } else if (port == 10008) {
            try {
                // 设置服务器端口
                TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(10008);
                // 设置二进制协议工厂
                TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
                // 处理器关联业务实现
                Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
                TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport);
                args.processor(processor);
                args.protocolFactory(protocolFactory);
                TServer server = new TThreadedSelectorServer(args);
                System.err.println("开启thrift服务器，监听端口：10008");
                server.serve();
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
    }
}
