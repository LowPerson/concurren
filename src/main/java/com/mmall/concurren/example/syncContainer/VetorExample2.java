package com.mmall.concurren.example.syncContainer;

import com.mmall.concurren.annoations.NotThreadSafe;
import javafx.print.PrinterAttributes;

import java.util.List;
import java.util.Vector;
@NotThreadSafe
public class VetorExample2 {
    private static List<Integer> list = new Vector<>();
    public static void main(String[] args) {
        while (true) {
        for (int i = 0; i < 10 ; i++){
            list.add(i);
        }

    Thread t1 = new Thread(() -> {
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
        }
    });

    Thread t2 = new Thread(() -> {
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    });

    t1.start();
    t2.start();
}
    }
}
