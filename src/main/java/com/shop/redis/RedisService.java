package com.shop.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;
    @Autowired
    public RedisService(JedisPool pool) {
        this.jedisPool=pool;
    }

    /**
     * 获取当个对象
     * */
    public <T> T get(String key,  Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();

            String  str = jedis.get(key);
            T t =  stringToBean(str, clazz);
            return t;
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     * */
    public <T> boolean set( String key,  T value) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length() <= 0) {
                return false;
            }

            jedis.set(key, str);

            return true;
        }finally {
            returnToPool(jedis);
        }
    }


    /**
     * 判断key是否存在
     * */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();

            return  jedis.exists(key);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 增加值
     * */
    public <T> Long incr( String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key

            return  jedis.incr(key);
        }finally {
            returnToPool(jedis);
        }
    }

    /**
     * 减少值
     * */
    public <T> Long decr(String key) {
        Jedis jedis = null;
        try {
            jedis =  jedisPool.getResource();
            //生成真正的key

            return  jedis.decr(key);
        }finally {
            returnToPool(jedis);
        }
    }

    public  <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }

    @SuppressWarnings("unchecked")
    public  <T> T stringToBean(String str, Class<T> clazz) {
        if(str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if(jedis != null) {
            jedis.close();
        }
    }

}
