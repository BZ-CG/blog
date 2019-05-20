package cn.edu.pzhu.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author:CG
 * @date:2019/05/16 22:34
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Object lGet(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public boolean lSet(String key, Object value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    public boolean lSet(String key, Object value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        return true;
    }

    public boolean lSet(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        if (time > 0) {
            expire(key, time);
        }
        return true;
    }

    public boolean lSet(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
        return true;
    }


    public boolean set(String key, Object value, long time) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
        return true;
    }

    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return true;
    }


    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }


    public boolean expire(String key, long time) {
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return true;
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }



    //private static String host = "47.107.110.158";
    //private static int port = 6379;
    //
    //private static volatile JedisPool jedisPool = null;
    //private static final int DEFAULT_CACHE_SECONDS = 60 * 60 * 1;
    //
    //static {
    //    try {
    //        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    //        jedisPoolConfig.setMaxTotal(100);
    //        jedisPoolConfig.setMaxIdle(20);
    //        jedisPoolConfig.setMinIdle(10);
    //
    //        jedisPool = new JedisPool(jedisPoolConfig, host, port);
    //    } catch (Exception e) {
    //        log.error("Jedis pool create fail!", e);
    //    }
    //}
    //
    //public static synchronized Jedis getJedis() {
    //    if (jedisPool != null) {
    //        Jedis jedis = jedisPool.getResource();
    //        return jedis;
    //    }
    //    return null;
    //}
    //
    //public static void close(Jedis jedis) {
    //    if (jedis != null) {
    //        jedis.close();
    //    }
    //}
    //
    //public static Object get(String key) {
    //    Jedis jedis = null;
    //    try {
    //        jedis = jedisPool.getResource();
    //        byte[] bytes = jedis.get(key.getBytes());
    //        if (bytes != null) {
    //            return SerializationUtils.deserialize(bytes);
    //        }
    //    } catch (Exception e) {
    //        log.error("get 获取 redis 键值异常", e);
    //    } finally {
    //        close(jedis);
    //    }
    //    return null;
    //}

}
