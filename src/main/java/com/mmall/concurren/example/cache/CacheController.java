package com.mmall.concurren.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cache")
public class CacheController {
    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("key") String key,@RequestParam("value") String value){
        try {
            redisClient.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "SUCCESS";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("key") String key){
        String value = null;
        try {
            value= redisClient.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  value;
    }
}
