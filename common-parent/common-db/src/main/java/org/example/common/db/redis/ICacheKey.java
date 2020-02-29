package org.example.common.db.redis;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public interface ICacheKey {

    String SEPARATE = ":";

    /**
     * cache key对key统一管理
     */
    String getKey();

    /**
     * cache timeout 绝对过期时间（秒），-1 代表永不过期
     */
    int getExpirationTime();

    /**
     * 本地缓存时间
     */
    int getLocalCacheTime();
}
