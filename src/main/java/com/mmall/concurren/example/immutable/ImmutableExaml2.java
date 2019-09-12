package com.mmall.concurren.example.immutable;

import com.mmall.concurren.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExaml2 { ;

    private  static Map<Integer,Integer> map = new HashMap<>();
    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,5);
        log.info("{}",map.get(1));
    }
}
