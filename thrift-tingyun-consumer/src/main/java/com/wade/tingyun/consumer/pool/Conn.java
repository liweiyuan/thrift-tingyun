package com.wade.tingyun.consumer.pool;

import org.slf4j.LoggerFactory;

/**
 * Created by tingyun on 2018/1/25.
 * common-pool2使用
 * 假设这是一个建立TCP连接的对象，该对象的初始化时间平均为500ms，
 * 为了避免在程序中频繁创建Conn对象，我们需要借助对象池管理Conn对象实例
 */
public class Conn {
    //记录对象的创建时间
    private long createTime;
    /**
     * 初始化Conn对象，模拟创建Conn对象平均消耗500ms
     * @throws InterruptedException
     */
    public Conn() throws InterruptedException {
        createTime = System.currentTimeMillis();
        LoggerFactory.getLogger(getClass()).debug(" init conn suc... " + createTime);
        System.err.println("init");
    }
    /**
     * 报告Conn对象信息
     */
    public void report() {
        LoggerFactory.getLogger(getClass()).info("this is a available conn " + createTime);
        System.err.println("init  available");
    }
}
