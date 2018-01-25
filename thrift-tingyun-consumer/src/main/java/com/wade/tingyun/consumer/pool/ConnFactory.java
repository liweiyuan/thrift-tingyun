package com.wade.tingyun.consumer.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by tingyun on 2018/1/25.
 * common-pool2 使用方式
 * <p/>
 * 为了使用common-pool2对象池管理，我们必须实现{@link org.apache.commons.pool2.PooledObjectFactory}或者其子类
 * 这是一个工厂模式，告诉对象池怎样去创建要管理的对象
 * <p/>
 * BasePooledObjectFactory 是对{@link org.apache.commons.pool2.PooledObjectFactory}的一个基本实现，我们可以继承该类，
 * 减少一些方法的实现
 * <p/>
 * 在实现{@link org.apache.commons.pool2.PooledObjectFactory}接口时，我们一定要实现的接口方法是
 * {@link PooledObjectFactory#makeObject()}方法。
 */
public class ConnFactory extends BasePooledObjectFactory<Conn> {
    //间接实现{@link PooledObjectFactory#makeObject()}方法，表明怎样创建需要管理对象
    @Override
    public Conn create() throws Exception {
        return new Conn();
    }

    /**
     * 在common-pool2中为了统计管理的对象的一些信息，比如调用次数，空闲时间，上次使用时间等，
     * 需要对管理的对象进行包装，然后在放入到对象池中
     *
     * @param obj 对象池要管理的对象
     * @return 返回包装后的PooledObject对象
     */
    @Override
    public PooledObject<Conn> wrap(Conn conn) {
        return new DefaultPooledObject<Conn>(conn);
    }
}
