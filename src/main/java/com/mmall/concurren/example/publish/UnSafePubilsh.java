package com.mmall.concurren.example.publish;

import com.mmall.concurren.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnSafePubilsh {
    //定义的域
    private String [] states = {"a", "b", "c"};
    //发布给外界的public方法
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        UnSafePubilsh pubilsh = new UnSafePubilsh();
        log.info("{}", Arrays.toString( pubilsh.getStates()));
        //通过对象的public方法修改对象里的私有域
        pubilsh.getStates()[0] = "d";
        log.info("{}",Arrays.toString(pubilsh.getStates()));
    }
}
