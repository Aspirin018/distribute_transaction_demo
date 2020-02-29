package org.example.common.db.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public interface RedisStringTemplate {
    /**
     * redis移除key
     *
     * @param key
     */
    void del(ICacheKey key);

    /**
     * 为指定key设置生存时间
     *
     * @param key
     * @return
     */
    boolean expire(ICacheKey key);

    /**
     * 添加key value
     *
     * @param key   String
     * @param value String
     */
    void put(ICacheKey key, String value);

    /**
     * 获取指定key的value
     *
     * @param key
     * @return
     */
    String get(ICacheKey key);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean exists(ICacheKey key);


    /**
     * 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key   key
     * @param value value
     */
    void lpush(ICacheKey key, String... value);

    /**
     * 将一个或多个值 value 插入到列表 key 的表尾
     *
     * @param key   key
     * @param value value
     */
    void rpush(ICacheKey key, String... value);

    /**
     * 移除并返回列key的头元素
     *
     * @param key
     * @return
     */
    String lPop(ICacheKey key);

    /**
     * 移除并返回列表 key 的尾元素
     *
     * @param key
     * @return
     */
    String rPop(ICacheKey key);


    /**
     * 取出key指定的list中所有的元素
     *
     * @param key key
     * @return
     */
    List<String> lGetAll(ICacheKey key);

    /**
     * 取出key指定的list中 包含fromIndex - lastIndex 之间的所有元素，包含fromIndex、lastIndex
     *
     * @param key
     * @param fromIndex 起始范围(包含)
     * @param lastIndex 接触范围(包含)
     * @return
     */
    List<String> lrange(ICacheKey key, int fromIndex, int lastIndex);

    /**
     * 取出list中下标标识的元素
     *
     * @param key   key
     * @param index 下标
     * @return
     */
    String lGet(ICacheKey key, int index);

    /**
     * 获取指定list的长度
     *
     * @param key key
     * @return
     */
    long lLen(ICacheKey key);

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     *
     * @param key
     * @param members
     * @return
     */
    long sAdd(ICacheKey key, final String... members);

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     *
     * @param key
     * @return
     */
    Set<String> sMembers(ICacheKey key);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key
     * @param members
     * @return
     */
    Long sRem(ICacheKey key, String... members);

    /**
     * 返回集合 key 集合中元素的数量。当 key 不存在时，返回 0 。
     *
     * @param key
     * @return
     */
    Long sLen(ICacheKey key);

    /**
     * 判断 member 元素是否集合 key 的成员。
     *
     * @param key
     * @param member
     * @return
     */
    Boolean sIsMember(ICacheKey key, String member);

    /**
     * 将 key 中储存的数字值增一
     *
     * @param key
     * @return
     */
    long incr(ICacheKey key);

    /**
     * 将 key 中储存的数字值增一 ,并设定过期时间
     * 如果= -1 则不改变key的过期时间
     *
     * @param key
     * @param expireTime
     * @return
     */
    long incr(ICacheKey key, Integer expireTime);

    /**
     * 将 key 中存储的数字值加1，不修改过期时间
     * @param key redis中的key
     * @return 自增后的值
     */
    long incrWithoutRenewExpireTime(ICacheKey key);

    /**
     * 将 key 中储存的数字值减一
     *
     * @param key
     * @return
     */
    public long decr(ICacheKey key);


    /**
     * 将 key 的值设为 value ，当且仅当 key 不存在。
     *
     * @param key
     * @param value
     * @return 设置成功，返回 true || 设置失败，返回 false 。
     */
    public boolean setnx(ICacheKey key, String value);

    /**
     * getset方法
     *
     * @param key   key
     * @param value value
     * @return
     */
    public String getset(ICacheKey key, String value);

    /**
     * getd多个key的方法
     *
     * @param keyList key集合
     * @return
     */
    public List<String> multiGet(List<ICacheKey> keyList);

    /**
     * set多个key value的方法
     */
    public void multiSet(Map<ICacheKey, String> map);

    /**
     * 发布订阅的发布命令
     * key的代表channel名字
     *
     * @param key
     * @param value
     */
    public void publish(ICacheKey key, String value);

    /**
     * 获取key的过期时间
     *
     * @param key
     * @return
     */
    Long getExpire(ICacheKey key);

    /**
     * hset 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖
     * @param key
     * @param field
     * @param value
     */
    public void hset(ICacheKey key, String field, String value);

    /**
     * 批量添加记录
     * hmset 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * 此命令会覆盖哈希表中已存在的域。
     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
     * @param key
     * @param value
     */
    public void hsetAll(ICacheKey key, Map<String, String> value);

    /**
     * 删除hash中 field对应的值
     * hdel 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
     * @param key
     * @return
     */
    public long hdel(ICacheKey key, String... fields);

    /**
     * 获取hash中 指定的field的值
     * hmget 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET 操作将返回一个只带有 nil 值的表。
     * @param key
     * @return
     */
    public List<String> hget(ICacheKey key, List fields);

    /**
     * 获取hash中 所有的field value
     * @param key
     * @return
     */
    public Map<Object, Object> hgetAll(ICacheKey key);

    /**
     * 判断hash中 指定的field是否存在
     * @param key
     * @param field
     * @return
     */
    public boolean hisExist(ICacheKey key, String field);

    /**
     * 获取hash 的size
     * hlen 获取哈希表中字段的数量
     * @param key
     * @return
     */
    public long hsize(ICacheKey key);
}
