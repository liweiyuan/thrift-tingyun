package com.wade.tingyun.consumer.controller;

import com.wade.tingyun.consumer.controller.bean.ThriftBean;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by tingyun on 2017/12/19.
 */
@RestController
public class ThriftController {

    @Autowired
    private ThriftBean thriftBean;


    //二进制协议传输数据TBinaryProtocol
    @RequestMapping("/binary")
    @ResponseBody
    public String getResult() {
        String result = thriftBean.getResult();
        return result;
    }


    //TCompactProtocol —— 高效率的、密集的二进制编码格式进行数据传输
    @RequestMapping("/compact")
    @ResponseBody
    public String getCompact() {
        String result = thriftBean.getCompact1();
        return result;
    }

    //TJSONProtocol   -使用 JSON 的数据编码协议进行数据传输
    @RequestMapping("/json")
    @ResponseBody
    public String getJson() {
        Boolean bool = thriftBean.getJson();
        return bool.toString();
    }

    //TTupleProtocol   -使用 JSON 的数据编码协议进行数据传输
    @RequestMapping("/tuple")
    @ResponseBody
    public String getSimple() {
        Integer number = thriftBean.getSimple();
        return number.toString();
    }

    //TSimpleJSONProtocol
    /*@RequestMapping("/simple")
    @ResponseBody
    public String getTuple() {
        String result = thriftBean.getTuple();
        return result;
    }*/


    //调用异步的服务端

    /**
     * TNonblockingServer模式在业务处理上还是采用单线程顺序来完成，在业务处理比较复杂、耗时的时候，例如某些接口函数需要读取数据库执行时间较长，
     * 此时该模式效率也不高，因为多个调用请求任务依然是顺序一个接一个执行。
     */
    @RequestMapping("/niobinary")
    @ResponseBody
    public void getNioBinary() {
        System.err.println("this is a sysnc consumer");
        thriftBean.getNioBinary();
    }


    //调用异步的服务端

    /**
     * 与TNonblockingServer模式相比，THsHaServer在完成数据读取之后，
     * 将业务处理过程交由一个线程池来完成，主线程直接返回进行下一次循环操作，效率大大提升；
     */
    @RequestMapping("/niohsha")
    @ResponseBody
    public void getNioHsHa() {
        System.err.println("this is a niohsha sysnc consumer");
        thriftBean.getNioHsHa();
    }

    /**
     * 线程池模式中，数据读取和业务处理都交由线程池完成，主线程只负责监听新连接，
     * 因此在并发量较大时新连接也能够被及时接受。线程池模式比较适合服务器端能预知最多有多少个客户端并发的情况，
     * 这时每个请求都能被业务线程池及时处理，性能也非常高。
     */
    @RequestMapping("/niothread")
    @ResponseBody
    public String getNioThread() throws Exception {
        System.err.println("this is a Thread sysnc consumer");
        String result = thriftBean.getNioThread();

        return result;
    }

    /**
     * TThreadedSelectorServer模式是目前Thrift提供的最高级的模式，它内部有如果几个部分构成：
     1. 一个AcceptThread线程对象，专门用于处理监听socket上的新连接；
     2. 若干个SelectorThread对象专门用于处理业务socket的网络I/O操作，所有网络数据的读写均是有这些线程来完成；
     3. 一个负载均衡器SelectorThreadLoadBalancer对象，主要用于AcceptThread线程接收到一个新socket连接请求时，决定将这个新连接请求分配给哪个SelectorThread线程。
     4. 一个ExecutorService类型的工作线程池，在SelectorThread线程中，监听到有业务socket中有调用请求过来，则将请求读取之后，交个ExecutorService线程池中的线程完成此次调用的具体执行；
     */
    //TThreadedSelectorServer
    @RequestMapping("/selector")
    @ResponseBody
    public void getSelector() throws IOException, TException {
        System.err.println("this is a Thread sysnc consumer");
        thriftBean.getSelector();
    }

}
