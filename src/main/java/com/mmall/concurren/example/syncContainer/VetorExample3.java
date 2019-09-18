package com.mmall.concurren.example.syncContainer;

import com.mmall.concurren.annoations.NotThreadSafe;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@NotThreadSafe
public class VetorExample3 {
    public static void main(String[] args) {
        List<Integer> list = new Vector<>();
        list.add(1);
        list.add(2);
        list.add(3);
        test4(list);
        for (int i = 0; i < list.size();i++){
            System.out.println(i);
        }
    }
    //foreach - java.util.ConcurrentModificationException
    public static void test1(List<Integer> list){
        for(Integer i:list){
            if (i == 3){
                list.remove(i);
            }
        }
        System.out.println("foreach Completed");
    }
    //for - success
    public static void test2(List<Integer> list){
        for (int i = 0 ; i < list.size() ; i++){
            if (list.get(i) == 3){
                System.out.println("i:"+i);
                list.remove(i);
            }
        }
        System.out.println("for Completed");
    }
    //iterator -java.util.ConcurrentModificationException
    public static void test3(List<Integer> list){
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i == 3){
               list.remove(i);
            }
        }
        System.out.println("iterator Completed");
    }

    //iterator -success
    public static void test4(List<Integer> list){
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            if (i == 3){
               iterator.remove();
            }
        }
        System.out.println("iterator Completed");
    }

}
