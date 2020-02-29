package org.example.common.db.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class StringTemplate implements RedisStringTemplate {

    private static final Logger logger = LoggerFactory.getLogger(StringTemplate.class);

    private StringRedisTemplate stringTemplate;

    public StringTemplate(StringRedisTemplate stringTemplate) {
        this.stringTemplate = stringTemplate;
    }

    @Override
    public void del(ICacheKey key) {
        stringTemplate.delete(key.getKey());
    }

    @Override
    public boolean expire(ICacheKey key) {
        return stringTemplate.expire(key.getKey(), key.getExpirationTime(), TimeUnit.SECONDS);
    }

    @Override
    public void put(ICacheKey key, String value) {
        Assert.notNull(value, "参数value不能为null....");
        stringTemplate.opsForValue().set(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public String get(ICacheKey key) {
        return stringTemplate.opsForValue().get(key.getKey());
    }

    @Override
    public boolean exists(ICacheKey key) {
        return stringTemplate.hasKey(key.getKey());
    }

    @Override
    public void lpush(ICacheKey key, String... value) {
        stringTemplate.opsForList().leftPushAll(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public void rpush(ICacheKey key, String... value) {
        stringTemplate.opsForList().rightPushAll(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public String lPop(ICacheKey key) {
        return stringTemplate.opsForList().leftPop(key.getKey());
    }

    @Override
    public String rPop(ICacheKey key) {
        return stringTemplate.opsForList().rightPop(key.getKey());
    }

    @Override
    public List<String> lGetAll(ICacheKey key) {
        return stringTemplate.opsForList().range(key.getKey(), 0, -1);
    }

    @Override
    public List<String> lrange(ICacheKey key, int fromIndex, int lastIndex) {
        return stringTemplate.opsForList().range(key.getKey(), fromIndex, lastIndex);
    }

    @Override
    public String lGet(ICacheKey key, int index) {
        return stringTemplate.opsForList().index(key.getKey(), index);
    }

    @Override
    public long lLen(ICacheKey key) {
        return stringTemplate.opsForList().size(key.getKey());
    }

    @Override
    public long sAdd(ICacheKey key, String... members) {
        long n = stringTemplate.opsForSet().add(key.getKey(), members);
        setExpireTime(key);
        return n;
    }

    @Override
    public Set<String> sMembers(ICacheKey key) {
        return stringTemplate.opsForSet().members(key.getKey());
    }

    @Override
    public Long sRem(ICacheKey key, String... members) {
        return stringTemplate.opsForSet().remove(key.getKey(), members);
    }

    @Override
    public Long sLen(ICacheKey key) {
        return stringTemplate.opsForSet().size(key.getKey());
    }

    @Override
    public Boolean sIsMember(ICacheKey key, String member) {
        return stringTemplate.opsForSet().isMember(key.getKey(), member);
    }

    @Override
    public long incr(ICacheKey key) {
        Long increment = stringTemplate.opsForValue().increment(key.getKey(), 1);
        setExpireTime(key);
        return increment;
    }

    @Override
    public long incr(ICacheKey key, Integer expireTime) {
        Long increment = stringTemplate.opsForValue().increment(key.getKey(), 1);
        if (expireTime == -1) {
            return increment;
        } else {
            setExpireTime(key, expireTime);
            return increment;
        }
    }

    /**
     * @param key redis中的key
     * @return 自增后的结果
     */
    @Override
    public long incrWithoutRenewExpireTime(ICacheKey key) {
        //对key进行自增，若key不存在，redis将自动生成此key，自增后值为1，并将过期时间置为永久
        long result = stringTemplate.opsForValue().increment(key.getKey(), 1);
        //若值为1，则此key为新生成，过期时间为永久，将其过期时间设为key中时间
        if (result == 1L) {
            expire(key);
        }
        return result;
    }

    @Override
    public long decr(ICacheKey key) {
        Long decrement = stringTemplate.opsForValue().increment(key.getKey(), -1);
        setExpireTime(key);
        return decrement;
    }

    @Override
    public boolean setnx(ICacheKey key, String value) {
        boolean  flag= stringTemplate.opsForValue().setIfAbsent(key.getKey(), value);
        if(flag)
        {
            setExpireTime(key);
        }
        return flag;
    }

    @Override
    public String getset(ICacheKey key, String value) {
        return stringTemplate.opsForValue().getAndSet(key.getKey(), value);
    }

    @Override
    public List<String> multiGet(List<ICacheKey> keyList) {
        List<String> strKeyList = new ArrayList<>();
        for (int i = 0; i < keyList.size(); i++) {
            strKeyList.add(keyList.get(i).getKey());
        }
        return stringTemplate.opsForValue().multiGet(strKeyList);
    }

    @Override
    public void multiSet(Map<ICacheKey, String> keyMap) {
        Map<String, String> strKeyMap = new HashMap<>();
        for (ICacheKey key : keyMap.keySet()) {
            strKeyMap.put(key.getKey(), keyMap.get(key));
        }
        stringTemplate.opsForValue().multiSet(strKeyMap);
    }

    @Override
    public void publish(ICacheKey key, String value) {
        stringTemplate.convertAndSend(key.getKey(), value);
    }

    @Override
    public Long getExpire(ICacheKey key) {
        return stringTemplate.getExpire(key.getKey());
    }

    @Override
    public void hset(ICacheKey key, String field, String value) {
        stringTemplate.opsForHash().put(key.getKey(), field, value);
        setExpireTime(key);
    }

    @Override
    public void hsetAll(ICacheKey key, Map<String, String> value) {
        stringTemplate.opsForHash().putAll(key.getKey(), value);
        setExpireTime(key);
    }

    @Override
    public long hdel(ICacheKey key, String... fields) {
        return stringTemplate.opsForHash().delete(key.getKey(), fields);
    }

    @Override
    public List<String> hget(ICacheKey key, List fields) {
        return stringTemplate.opsForHash().multiGet(key.getKey(), fields);
    }

    @Override
    public Map<Object, Object> hgetAll(ICacheKey key) {
        return stringTemplate.opsForHash().entries(key.getKey());
    }

    @Override
    public boolean hisExist(ICacheKey key, String field) {
        return stringTemplate.opsForHash().hasKey(key.getKey(), field);
    }

    @Override
    public long hsize(ICacheKey key) {
        return stringTemplate.opsForHash().size(key.getKey());
    }

    /**
     * 设置key的过期时间
     *
     * @param key
     */
    private void setExpireTime(ICacheKey key) {
        if (key.getExpirationTime() != -1) {
            stringTemplate.expire(key.getKey(), key.getExpirationTime(), TimeUnit.SECONDS);
        }
    }

    /**
     * 设置key的过期时间
     *
     * @param key        key
     * @param expireTime expireTime
     */
    private void setExpireTime(ICacheKey key, Integer expireTime) {
        if (expireTime != -1) {
            stringTemplate.expire(key.getKey(), expireTime, TimeUnit.SECONDS);
        }
    }
}
