package org.example.common.db.redis;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class ThreadMsgCacheKey {

    private static final String SEPARATE = ":";
    private static final int EXPIRATIONTIME = 300;
    private static final int LOCALCACHETIME = 300;

    private final String prefix = "cloud:threadmessagecache";
    private String currentThreadName;

    public ThreadMsgCacheKey() {
    }

    public ThreadMsgCacheKey(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    public void setCurrentThreadName(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    public String getKey() {
        return prefix + SEPARATE + currentThreadName;
    }

    public int getExpirationtime() {
        return EXPIRATIONTIME;
    }

    public int getLocalcachetime() {
        return LOCALCACHETIME;
    }
}
