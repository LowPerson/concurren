package com.mmall.concurren.example.BlockingQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
@Slf4j
public class ArrayBlockingExample {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5);

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0 ; i < 10 ; i++){
                try {
                    queue.put(i);
                    log.info("put :{}",i);
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0 ; i < 10 ; i++){
                try {
                    Integer take = queue.take();
                    log.info("take:{}",take);
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                }
            }
        }).start();
    }
}
