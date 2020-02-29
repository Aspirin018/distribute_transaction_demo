package org.example.common.db.redis;

import java.util.List;
import java.util.Map;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public interface RedisObjectTemplate {
    /**
     * 添加key value
     *
     * @param key
     * @param obj
     */
    public void put(ICacheKey key, Object obj);

    /**
     * 获取指定key的value
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(ICacheKey key, Class<T> clazz);

    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void lpush(ICacheKey key, T... t);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾
     *
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void rpush(ICacheKey key, T... t);

    /**
     * 移除并返回列key的头元素
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T lPop(ICacheKey key, Class<T> clazz);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * @param key   key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T rPop(ICacheKey key, Class<T> clazz);


    /**
     * 取出key指定的list中所有的元素
     *
     * @param key key
     * @param clazz
     * @return
     */
    public <T> List<T> lGetAll(ICacheKey key, Class<T> clazz);

    /**
     * * 取出list中下标标识的元素
     *
     * @param key   key
     * @param index 下标
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T lGet(ICacheKey key, int index, Class<T> clazz);

    /**
     * * 设置hash
     *
     * @param key   key
     * @param map hashmap
     * @param <T>
     * @return
     */
    public <T> void hmsetObj(ICacheKey key, Map<String, T> map);

    /**
     * * 取出hash中指定元素
     *
     * @param key
     * @param fields
     * @return
     */
    public <T> List<T> hmgetObj(ICacheKey key, List fields, Class<T> clazz);

}
