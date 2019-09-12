package com.mmall.concurren.example.atomic;

import com.mmall.concurren.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicExampleFieldUpdater {
    private static AtomicIntegerFieldUpdater<AtomicExampleFieldUpdater> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExampleFieldUpdater.class,"count");
    @Getter
    public volatile int count = 100;
    private static AtomicExampleFieldUpdater example = new AtomicExampleFieldUpdater();
    public static void main(String[] args) {
        if (updater.compareAndSet(example,100,120)){
            log.info("update success,count:{}",example.getCount());
        }
        if (updater.compareAndSet(example,100,120)){
            log.info("update success,count:{}",example.getCount());
        }else {
            log.info("update fail ,count:{}",example.getCount());
        }
    }
}