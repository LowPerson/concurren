package com.mmall.concurren.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockExamle6 {
    public static void main(String[] args) {
        //获得锁的实例
        ReentrantLock reentrantLock = new ReentrantLock();
        //从锁中获取Condition实例
        Condition condition = reentrantLock.newCondition();

        new Thread(()->{
            try {
                reentrantLock.lock();
                log.info("wait signal");//1.等待信号
                //会释放锁，并从AQS的等待队列中移除，添加到Condition的等待队列中去,等待信号的到来
                condition.await();
                //被唤醒并重新获得锁
            }catch (Exception e){
                log.error("exception:{}",e);
            }
            log.info("get signal");//收到信号
            //最后释放锁
            reentrantLock.unlock();
        }).start();

        new Thread(()->{
            try {
                //获取上一个线程释放的锁
                reentrantLock.lock();
                log.info("get lock");// 2获取锁
                Thread.sleep(3000);
            }catch (Exception e){
                log.error("exception:{}",e);
            }
            //发送信号,并把在等该该信号的线程从Condition队列中移除，重新添加到AQS队列中
            condition.signalAll();
            log.info("send signal");//3发送信号
            //释放锁
            reentrantLock.unlock();
        }).start();
    }
}
