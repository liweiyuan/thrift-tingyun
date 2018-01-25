package com.wade.tingyun.consumer.controller.bean.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by tingyun on 2018/1/25.
 */
public class TSocketPoolConfig extends GenericObjectPoolConfig {
    public TSocketPoolConfig() {
        // defaults to make your life with connection pool easier :)
        setMinIdle(5);
        setTestOnBorrow(true);
        setMaxTotal(1000);
    }
}
