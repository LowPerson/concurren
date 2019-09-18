package com.mmall.concurren.example.communsafe;

import com.mmall.concurren.annoations.NotThreadSafe;
import com.mmall.concurren.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DataFormatExample1 {

    //请求总数
    private static int clientTotal = 5000;
    //同时并发执行的线程数
    private static int threadTotal = 200;
    public static void main(String[] args) throws Exception{
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }
    private static void update(){
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            simpleDateFormat.parse("2018-02-08");
        }catch (Exception e){
            log.error("parse exception",e);
        }
    }
}
