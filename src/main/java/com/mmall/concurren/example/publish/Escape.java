package com.mmall.concurren.example.publish;

import com.mmall.concurren.annoations.NotRecommend;
import com.mmall.concurren.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    //定义的域
    private int thisCanBeEscape = 0;
    public Escape () {
        new Inner();
    }
    private class Inner{
        public Inner (){
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
