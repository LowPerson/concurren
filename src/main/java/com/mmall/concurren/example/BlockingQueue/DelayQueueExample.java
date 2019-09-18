package com.mmall.concurren.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class DelayQueueExample {
    private static BlockingQueue queue = new DelayQueue();

    public static void main(String[] args) {
        class DelayClass implements Delayed{
            int count ;
            public DelayClass(int count){
                this.count = count;
            }
            @Override
            public long getDelay(TimeUnit unit) {
                return this.count;
            }
            @Override
            public int compareTo(Delayed o) {
                Long a = (Long)(this.count-o.getDelay(TimeUnit.MILLISECONDS));
                return a.intValue();
            }
        }
        new Thread(()->{
            for (int i = 0 ; i < 10 ; i++){
                try {
                    DelayClass delayClass = new DelayClass(i);
                    queue.put(delayClass);
                    log.info("put :{}",delayClass.getDelay(TimeUnit.MILLISECONDS));
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0 ; i < 10 ; i++){
                try {
                    DelayClass take = (DelayClass) queue.take();
                    log.info("take:{}",take.getDelay(TimeUnit.MILLISECONDS));
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                }
            }
        }).start();
    }
}
