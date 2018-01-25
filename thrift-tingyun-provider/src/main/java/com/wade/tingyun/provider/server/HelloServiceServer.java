package com.wade.tingyun.provider.server;

import com.wade.tingyun.provider.service.HelloImpl;
import com.wade.tingyun.service.demo.Hello;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by tingyun on 2017/12/19.
 */
public class HelloServiceServer {
    /**
     * 启动thrift服务器
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //设置端口
            TServerSocket serverTransport = new TServerSocket(7911);
            //设置协议工厂TBinaryProtocol.Factory
            Factory factory = new TBinaryProtocol.Factory();
            //关联处理器与hello服务器
            Hello.Processor<Hello.Iface> processor = new Hello.Processor<Hello.Iface>(new HelloImpl());
            //使用单线程标准阻塞I/O模型
            TServer.Args simpleArgs = new TServer.Args(serverTransport)
                    .processor(processor)
                    .protocolFactory(factory);
            TServer server = new TSimpleServer(simpleArgs);
            System.out.println("开启thrift服务器，监听端口：7911");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
