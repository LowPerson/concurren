package com.mmall.concurren.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.schedule(()->{
            log.info("schedule run");
            //延迟三秒后执行
        },3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(()->{
            log.info("scheduleAtFixedRate run");
            //延迟1秒后执行   每隔3秒执行一次
        },1,3,TimeUnit.SECONDS);
//        executorService.shutdown();
        //定时器 从当前时间开始执行 每隔5秒执行一次
        Timer timer = new Timer();
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               log.info("timer run");
           }
       },new Date(),5*1000);
    }
}
