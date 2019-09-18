package com.mmall.concurren.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample2 {
    // 定义要等待多少线程
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            Thread.sleep(1000);
            final int threadNum = i;
            executorService.execute(()->{
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception:{}",e);
                }
            });
        }
        executorService.shutdown();
    }
    public static void test(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        try {
            // 等待固定数量线程的进入，才能继续执行下去
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            log.warn("exception:{}",e);
        }

        log.info("{} is continue",threadNum);
    }
}
