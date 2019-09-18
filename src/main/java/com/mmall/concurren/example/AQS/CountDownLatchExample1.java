package com.mmall.concurren.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample1 {
    private static final int threadCount = 200;

    public static void main(String[] args)  throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i=0;i<threadCount;i++){
            final int threadNum = i;

            executorService.execute(()->{

                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception:{}",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        // 设置最大的等待时间
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("finish");
        executorService.shutdown();
    }
    public static void test(int threadNum) throws Exception{
      log.info("{}",threadNum);
        Thread.sleep(1);
    }
}
