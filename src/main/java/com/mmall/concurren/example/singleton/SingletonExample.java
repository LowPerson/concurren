package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.NotThreadSafe;

/*
懒汉模式
对象的实例在第一次调用时才创建
 */
@NotThreadSafe
public class SingletonExample {
    //私有构造函数
    private SingletonExample(){
    }
    //单例对象
    private static SingletonExample  singletonExample = null;
    //静态的工厂方法  通过添加synchronized可以保证线程安全
    public static SingletonExample getInstance(){
        if (singletonExample == null){
            singletonExample = new SingletonExample();
        }
        return singletonExample;
    }
}
