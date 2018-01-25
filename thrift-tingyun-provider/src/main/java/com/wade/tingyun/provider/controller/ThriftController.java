package com.wade.tingyun.provider.controller;

import com.wade.tingyun.provider.controller.bean.ThriftBean;
import com.wade.tingyun.provider.controller.mian.Main;
import com.wade.tingyun.provider.controller.mian.NioMain;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tingyun on 2017/12/19.
 */
@RestController
public class ThriftController {

    @Autowired
    private ThriftBean thriftBean;


    //基于二进制传输协议TBinaryProtocol
    @RequestMapping("/binary")
    @ResponseBody
    public String startServer() throws TTransportException {
        return thriftBean.getResult();
    }


    //TCompactProtocol —— 高效率的、密集的二进制编码格式进行数据传输
    /*@RequestMapping("/compact")
    @ResponseBody
    public String startTCompact(){
        return thriftBean.startTCompact();
    }*/

    //TJSONProtocol —— 使用 JSON 的数据编码协议进行数据传输
    /*@RequestMapping("/json")
    @ResponseBody
    public String startJson(){
        return thriftBean.startJson();
    }*/

    //阻塞模式进行调用
    @RequestMapping("/server")
    @ResponseBody
    public String runningServer() throws InterruptedException {
        Main main = new Main(7912);
        Main main1 = new Main(7911);
        Main main2 = new Main(7913);
        Main main3 = new Main(7914);
        Main main4 = new Main(7915);

        Thread thread = new Thread(main);
        Thread thread2 = new Thread(main1);
        Thread thread1 = new Thread(main2);
        Thread thread3 = new Thread(main3);
        Thread thread4 = new Thread(main4);

        thread.start();
        thread2.start();
        thread1.start();
        thread3.start();
        thread4.start();

        Thread.sleep(1000);
        return "server is running.";
    }

    //非阻碍模式调用
    @RequestMapping("/nioserver")
    @ResponseBody
    public String startNioServer(){
        NioMain main=new NioMain(10005);
        Thread thread=new Thread(main);
        thread.start();
        return "the nioServer is running: 10005";
    }

    //THsHaServer模式
    @RequestMapping("/niohsha")
    @ResponseBody
    public String startNioHshaServer(){
        NioMain main=new NioMain(10006);
        Thread thread=new Thread(main);
        thread.start();
        return "the nioHsHaServer is running: 10006";
    }

    //TThreadPoolServer模式
    @RequestMapping("/niothread")
    @ResponseBody
    public String startNioThread(){
        NioMain main=new NioMain(10007);
        Thread thread=new Thread(main);
        thread.start();
        return "the nioThreadServer is running: 10007";
    }

    //TThreadedSelectorServer
    @RequestMapping("/nioselector")
    @ResponseBody
    public String startNioSelector(){
        NioMain main=new NioMain(10008);
        Thread thread=new Thread(main);
        thread.start();
        return "the nioSelectorServer is running: 10008";
    }


}
