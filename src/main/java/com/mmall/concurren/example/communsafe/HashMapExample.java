package com.mmall.concurren.example.communsafe;

import com.mmall.concurren.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashMapExample {
    //请求总数
    private static int clientTotal = 5000;
    //同时并发执行的线程数
    private static int threadTotal = 200;
    private static Map<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception{
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("list.length:{}", map.size());
    }
    private static void update(int i){
        map.put(i,1);
    }
}
