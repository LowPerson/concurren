package com.mmall.concurren.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizecExample {
    //修饰一个代码块
    public void test1(){
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1-{}", i);
            }
        }
    }
    //修饰一个方法
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
            log.info("test2-{}", i);
        }
    }

    public static void main(String[] args) {
        SynchronizecExample example = new SynchronizecExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example.test1();
        });
        executorService.execute(()->{
            example.test1();
        });
        executorService.shutdown();
    }
}
