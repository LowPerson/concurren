package com.mmall.concurren.example.singleton;

import com.mmall.concurren.annoations.Recommend;
import com.mmall.concurren.annoations.ThreadSafe;

/*
枚举模式 -》目前最安全最好的 推荐
 */
@ThreadSafe
@Recommend
public class SingletonExample6 {
    //私有构造函数
    private SingletonExample6(){
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance(){
        return Singleton.INSTANCE.getSingleton();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonExample6 singleton;
        //JVM保证该构造方法只被调用一次
        Singleton(){
            singleton = new SingletonExample6();
        }
        public SingletonExample6 getSingleton() {
            return singleton;
        }
    }
}
