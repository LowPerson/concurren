package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.NotThreadSafe;
import com.mmall.concurren.annoations.Recommend;
import com.mmall.concurren.annoations.ThreadSafe;

/*
懒汉模式 -> 双重同步锁单例模式
对象的实例在第一次调用时才创建
 */
@ThreadSafe
@Recommend
public class SingletonExample3 {
    //私有构造函数
    private SingletonExample3(){
    }
    //单例对象
    private static SingletonExample3 singletonExample = null;
    //静态的工厂方法
    public static SingletonExample3 getInstance(){
        //双重检测机制+锁
        if (singletonExample == null){
            synchronized (SingletonExample3.class) {
                if (singletonExample == null) {
                    singletonExample = new SingletonExample3();
                }
            }
        }
        return singletonExample;
    }
    /*
    1.memory = allocate() 分配内存空间
    2.ctorInstace() 初始化对象
    3.instance = memory 设置instance指向刚分配的内存空间

    jvm可能发生指令重排序
    例如：第三步和第二步调换，那么其实instance指向的对象还没有初始化，那么下一个线程检测到有值就会
    越过new 对象，直接调用对象，这时候可能会出现问题
    */
}
