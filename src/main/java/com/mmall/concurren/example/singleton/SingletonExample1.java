package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.ThreadSafe;

/*
饿汉模式
对象的实例在类装载的时候就被创建
 */
@ThreadSafe
public class SingletonExample1 {
    //私有构造函数
    private SingletonExample1(){
    }
    //单例对象
    private static SingletonExample1 singletonExample = new SingletonExample1();
    //静态的工厂方法
    public static SingletonExample1 getInstance(){
        return singletonExample;
    }
}
