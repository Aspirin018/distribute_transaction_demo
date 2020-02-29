package com.example.messagebroker.strategy;

import com.google.common.base.Preconditions;
import org.example.common.util.ip.WorkerIdUtils;

import java.util.Calendar;

/**
 * @Description: TODO
 * @Author: liyu
 * @Date: 2020/2/29
 */
public class DefaultKeyGenerator implements KeyGenerator {

    public static final long EPOCH;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_BITS = 10L;
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;
    private static long workId;
    private long sequence;
    private long lastTime;
    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.FEBRUARY, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        EPOCH = calendar.getTimeInMillis();
        workId = WorkerIdUtils.initWorkerId();
    }

    @Override
    public Number generateKey() {
        long currentMills = System.currentTimeMillis();
        Preconditions.checkState(lastTime <= currentMills,
                "Clock is moving backwards, last time is %d milliseconds", lastTime, currentMills);
        if(lastTime == currentMills){
            if(0L == (sequence = ++sequence & SEQUENCE_MASK)){
                currentMills = waitUntilNextTime(currentMills);
            }
        } else {
            sequence = 0;
        }
        lastTime = currentMills;
        return ((currentMills - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (workId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private long waitUntilNextTime(final long lastTime){
        long time = System.currentTimeMillis();
        while (time <= lastTime){
            time = System.currentTimeMillis();
        }
        return time;
    }
}
