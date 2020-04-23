package com.muscidae.parrot.common.util.date;

import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 高并发场景下System.currentTimeMillis()的性能问题的优化
 * <p><p>
 * System.currentTimeMillis()的调用比new一个普通对象要耗时的多 <p>
 * System.currentTimeMillis()之所以慢是因为去跟系统打了一次交道<p>
 * 后台定时更新时钟, JVM退出时, 线程自动回收<p>
 * 10亿: 43410,206,210.72815533980582%<p>
 * 1亿: 4699,29,162.0344827586207%<p>
 * 1000万: 480,12,40.0%<p>
 * 100万: 50,10,5.0%<p>
 * @author lry
 */
public final class SystemClock {

    private final long period;
    private final AtomicLong now;

    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, "SystemClock");
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), period, period, TimeUnit.MILLISECONDS);
    }

    private long currentTimeMillis() {
        return now.get();
    }

    public long now() {
        return Singleton.INSTANCE.newInstance().currentTimeMillis();
    }

    public String nowStr() {
        return new Timestamp(Singleton.INSTANCE.newInstance().currentTimeMillis()).toString();
    }

    public enum Singleton{
        INSTANCE;
        private SystemClock systemClock;
        Singleton(){ systemClock = new SystemClock(1); }
        public SystemClock newInstance(){ return systemClock; }
    }

    public static void main(String[] args) {
        SystemClock systemClock = Singleton.INSTANCE.newInstance();
        long clockStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            systemClock.now();
        }
        long clockEndTime = System.currentTimeMillis();
        System.out.println("SystemClock创建『时间戳』,耗时:『"+ (clockEndTime - clockStartTime) +"/ms』");

        long systemStartTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            System.currentTimeMillis();
        }
        long systemEndTime = System.currentTimeMillis();
        System.out.println("System创建『时间戳』,耗时:『"+ (systemEndTime - systemStartTime) +"/ms』");
    }

}
