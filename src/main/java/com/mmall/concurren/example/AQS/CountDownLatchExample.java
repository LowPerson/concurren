package com.mmall.concurren.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class CountDownLatchExample {
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
        // 等待前面所有线程都执行完才继续执行下去
        countDownLatch.await();
        log.info("finish");
        // 要等待现有的线程全部执行完再销毁线程池
        executorService.shutdown();
    }
    public static void test(int threadNum) throws Exception{
        java.lang.Thread.sleep(100);
      log.info("{}",threadNum);
        java.lang.Thread.sleep(100);
    }
}
