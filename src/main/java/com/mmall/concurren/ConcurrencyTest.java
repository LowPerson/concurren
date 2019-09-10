package com.mmall.concurren;

import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.*;

@Slf4j
public class ConcurrencyTest {
    //请求总数
    public static int clientTotal = 1000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    public static int count = 0;
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
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("erro",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        log.info("count:{}",count);
    }
    public static void add(){
        count++;
    }
}
