package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.NotRecommend;
import com.mmall.concurren.annoations.NotThreadSafe;
import com.mmall.concurren.annoations.ThreadSafe;

/*
懒汉模式
对象的实例在第一次调用时才创建
添加Synchronized
 */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {
    //私有构造函数
    private SingletonExample2(){
    }
    //单例对象
    private static SingletonExample2 singletonExample = null;
    //静态的工厂方法  通过添加synchronized可以保证线程安全
    public static synchronized SingletonExample2 getInstance(){
        if (singletonExample == null){
            singletonExample = new SingletonExample2();
        }
        return singletonExample;
    }
}
