package com.mmall.concurren.example.cache;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Calendar;

@Component
public class RedisClient {
    @Resource(name = "redisPool")
    private JedisPool jedisPool;
    //string类型的set方法
    public void set(String key, String value) throws Exception{
        Jedis jedis = null;
        try {
            //获取连接
            jedis = jedisPool.getResource();
            //操作set
            jedis.set(key,value);
        }finally {
            if (jedis != null){
                //释放连接
                jedis.close();
            }
        }
    }
    //string类型的get方法
    public String get(String key) throws Exception{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }finally {
            if (jedis != null){
                jedis.close();
            }
        }
    }
}
