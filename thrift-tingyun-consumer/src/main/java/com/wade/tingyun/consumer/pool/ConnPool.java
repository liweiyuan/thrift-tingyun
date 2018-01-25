package com.wade.tingyun.consumer.pool;

import org.apache.commons.pool2.impl.GenericObjectPool;

/**
 * Created by tingyun on 2018/1/25.
 * common-pool2 使用方式
 * <p/>
 * Conn对象管理池，这里利用GenericObjectPool作为对象池
 */
public class ConnPool extends GenericObjectPool<Conn> {
    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnPool() {
        super(new ConnFactory(), new ConnPoolConfig());
    }

    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public ConnPool(ConnPoolConfig connPoolConfig) {
        super(new ConnFactory(), connPoolConfig);
    }
}
