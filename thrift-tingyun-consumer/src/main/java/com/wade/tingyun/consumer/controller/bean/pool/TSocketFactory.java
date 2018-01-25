package com.wade.tingyun.consumer.controller.bean.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.transport.TSocket;

/**
 * Created by tingyun on 2018/1/25.
 */
public class TSocketFactory extends BasePooledObjectFactory<TSocket> {
    @Override
    public TSocket create() throws Exception {
        TSocket t= new TSocket("10.128.0.12", 10007);
        t.open();
        return t;
    }

    @Override
    public PooledObject<TSocket> wrap(TSocket tSocket) {
        return new DefaultPooledObject<TSocket>(tSocket);
    }

    @Override
    public void destroyObject(PooledObject<TSocket> p) throws Exception {
        //|super.destroyObject(p);
        p.getObject().close();
    }

    @Override
    public boolean validateObject(PooledObject<TSocket> p) {
        return true;
    }
}
