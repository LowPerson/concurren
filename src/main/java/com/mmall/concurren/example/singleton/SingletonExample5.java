package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.ThreadSafe;

import javax.management.InstanceAlreadyExistsException;

/*
饿汉模式
对象的实例在类装载的时候就被创建
 */
@ThreadSafe
public class SingletonExample5 {
    //私有构造函数
    private SingletonExample5(){
    }
    //单例对象
    private static SingletonExample5 singletonExample = null;
    static {
        singletonExample = new SingletonExample5();
    }

    //静态的工厂方法
    public static SingletonExample5 getInstance(){
        return singletonExample;
    }
}
