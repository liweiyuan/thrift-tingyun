package com.wade.tingyun.consumer.controller.bean.pool;

import com.wade.tingyun.consumer.pool.ConnPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.thrift.transport.TSocket;

/**
 * Created by tingyun on 2018/1/25.
 */
public class TsoketPool extends GenericObjectPool<TSocket> {
    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public TsoketPool() {
        super(new TSocketFactory(), new ConnPoolConfig());
    }

    /**
     * 调用{@link GenericObjectPool}的构造方法，构造ConnPool
     */
    public TsoketPool(ConnPoolConfig connPoolConfig) {
        super(new TSocketFactory(), connPoolConfig);
    }
}
