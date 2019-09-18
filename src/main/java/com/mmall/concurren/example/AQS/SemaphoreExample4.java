package com.mmall.concurren.example.AQS;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample4 {
    private static final int threadCount = 20;

    public static void main(String[] args)  throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);//每次放行三个许可
        for (int i=0;i<threadCount;i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    // 尝试在2秒内获取3个许可
                    if (semaphore.tryAcquire(3,2, TimeUnit.SECONDS)) {
                        test(threadNum);
                        semaphore.release(3);// 释放3个许可
                    }
                } catch (Exception e) {
                    log.error("exception:{}", e);
                }
            });
        }
        // 要等待现有的线程全部执行完再销毁线程池
        executorService.shutdown();
    }
    public static void test(int threadNum) throws Exception{
      log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
