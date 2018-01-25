package com.wade.tingyun.consumer.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by tingyun on 2018/1/25.
 * common-pool2 使用方式
 * <p/>
 * {@link org.apache.commons.pool2.impl.GenericObjectPool}支持个性化配置，
 * 我们可以配置对象池中总共的对象数，最大、最小空闲对象数等等
 * 这边继承{@link GenericObjectPoolConfig}是为了ConnPool也可以进行个性化的配置
 */
public class ConnPoolConfig extends GenericObjectPoolConfig {

    public ConnPoolConfig() {
        // defaults to make your life with connection pool easier :)
        setMinIdle(5);
        setTestOnBorrow(true);
    }
}
