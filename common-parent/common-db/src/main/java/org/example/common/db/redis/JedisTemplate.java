package org.example.common.db.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class JedisTemplate implements RedisObjectTemplate, RedisStringTemplate {
    private StringTemplate stringTemplate;
    private ObjectTemplate objectTemplate;

    public JedisTemplate(StringTemplate stringTemplate, ObjectTemplate objectTemplate) {
        this.stringTemplate = stringTemplate;
        this.objectTemplate = objectTemplate;
    }

    @Override
    public void del(ICacheKey key) {
        stringTemplate.del(key);
    }

    @Override
    public boolean expire(ICacheKey key) {
        return stringTemplate.expire(key);
    }

    @Override
    public void put(ICacheKey key, String value) {
        stringTemplate.put(key, value);
    }

    @Override
    public String get(ICacheKey key) {
        return stringTemplate.get(key);
    }

    @Override
    public boolean exists(ICacheKey key) {
        return stringTemplate.exists(key);
    }

    @Override
    public void lpush(ICacheKey key, String... value) {
        stringTemplate.lpush(key, value);
    }

    @Override
    public void rpush(ICacheKey key, String... value) {
        stringTemplate.rpush(key, value);
    }

    @Override
    public String lPop(ICacheKey key) {
        return stringTemplate.lPop(key);
    }

    @Override
    public String rPop(ICacheKey key) {
        return stringTemplate.rPop(key);
    }

    @Override
    public List<String> lGetAll(ICacheKey key) {
        return stringTemplate.lGetAll(key);
    }

    @Override
    public List<String> lrange(ICacheKey key, int fromIndex, int lastIndex) {
        return stringTemplate.lrange(key, fromIndex, lastIndex);
    }

    @Override
    public String lGet(ICacheKey key, int index) {
        return stringTemplate.lGet(key, index);
    }

    @Override
    public long lLen(ICacheKey key) {
        return stringTemplate.lLen(key);
    }

    @Override
    public long sAdd(ICacheKey key, String... members) {
        return stringTemplate.sAdd(key, members);
    }

    @Override
    public Set<String> sMembers(ICacheKey key) {
        return stringTemplate.sMembers(key);
    }

    @Override
    public Long sRem(ICacheKey key, String... members) {
        return stringTemplate.sRem(key, members);
    }

    @Override
    public Long sLen(ICacheKey key) {
        return stringTemplate.sLen(key);
    }

    @Override
    public Boolean sIsMember(ICacheKey key, String member) {
        return stringTemplate.sIsMember(key, member);
    }

    @Override
    public long incr(ICacheKey key) {
        return stringTemplate.incr(key);
    }

    @Override
    public long incr(ICacheKey key, Integer expireTime) {
        return stringTemplate.incr(key, expireTime);
    }

    @Override
    public long incrWithoutRenewExpireTime(ICacheKey key) {
        return stringTemplate.incrWithoutRenewExpireTime(key);
    }

    @Override
    public long decr(ICacheKey key) {
        return stringTemplate.decr(key);
    }

    @Override
    public boolean setnx(ICacheKey key, String value) {
        return stringTemplate.setnx(key, value);
    }

    @Override
    public String getset(ICacheKey key, String value) {
        return stringTemplate.getset(key, value);
    }

    @Override
    public List<String> multiGet(List<ICacheKey> keyList) {
        return stringTemplate.multiGet(keyList);
    }

    @Override
    public void multiSet(Map<ICacheKey, String> keyMap) {
        stringTemplate.multiSet(keyMap);
    }

    @Override
    public void publish(ICacheKey key, String value) {
        stringTemplate.publish(key, value);
    }

    @Override
    public Long getExpire(ICacheKey key) {
        return stringTemplate.getExpire(key);
    }

    @Override
    public void hset(ICacheKey key, String field, String value) {
        stringTemplate.hset(key, field, value);
    }

    @Override
    public void hsetAll(ICacheKey key, Map<String, String> value) {
        stringTemplate.hsetAll(key, value);
    }

    @Override
    public long hdel(ICacheKey key, String... fields) {
        return stringTemplate.hdel(key, fields);
    }

    @Override
    public List<String> hget(ICacheKey key, List fields) {
        return stringTemplate.hget(key, fields);
    }

    @Override
    public Map<Object, Object> hgetAll(ICacheKey key) {
        return stringTemplate.hgetAll(key);
    }

    @Override
    public boolean hisExist(ICacheKey key, String field) {
        return stringTemplate.hisExist(key, field);
    }

    @Override
    public long hsize(ICacheKey key) {
        return stringTemplate.hsize(key);
    }

    @Override
    public void put(ICacheKey key, Object obj) {
        objectTemplate.put(key, obj);
    }

    @Override
    public <T> T get(ICacheKey key, Class<T> clazz) {
        return objectTemplate.get(key, clazz);
    }

    @Override
    public <T> void lpush(ICacheKey key, T[] t) {
        objectTemplate.lpush(key, t);
    }

    @Override
    public <T> void rpush(ICacheKey key, T[] t) {
        objectTemplate.rpush(key, t);
    }

    @Override
    public <T> T lPop(ICacheKey key, Class<T> clazz) {
        return objectTemplate.lPop(key, clazz);
    }

    @Override
    public <T> T rPop(ICacheKey key, Class<T> clazz) {
        return objectTemplate.rPop(key, clazz);
    }

    @Override
    public <T> List<T> lGetAll(ICacheKey key, Class<T> clazz) {
        return objectTemplate.lGetAll(key, clazz);
    }

    @Override
    public <T> T lGet(ICacheKey key, int index, Class<T> clazz) {
        return objectTemplate.lGet(key, index, clazz);
    }

    @Override
    public <T> void hmsetObj(ICacheKey key, Map<String, T> map) {
        objectTemplate.hmsetObj(key, map);
    }

    @Override
    public <T> List<T> hmgetObj(ICacheKey key, List fields, Class<T> clazz) {
        return objectTemplate.hmgetObj(key,fields,clazz);
    }
}
