package com.javalive09.demos;

import java.lang.ref.WeakReference;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleUtils {

    public static void shutdownNow(WeakReference<ThreadPoolExecutor>... wspes) {
        for (WeakReference<ThreadPoolExecutor> wtpe : wspes) {
            if(wtpe != null) {
                ThreadPoolExecutor tpe = wtpe.get();
                if (tpe != null && !tpe.isShutdown()) {
                    tpe.shutdownNow();
                }
            }
        }
    }

    public static WeakReference<ThreadPoolExecutor> delay(long delay, Runnable runnable) {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(0);
        stpe.schedule(runnable, delay, TimeUnit.MILLISECONDS);
        return new WeakReference<>(stpe);
    }

    public static WeakReference<ThreadPoolExecutor> retry(long delay, long period, Retry retry) {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(0);
        stpe.scheduleWithFixedDelay(retry.setSTPE(stpe), delay, period, TimeUnit.MILLISECONDS);
        return new WeakReference<>(stpe);
    }

    public static abstract class Retry implements Runnable {
        private int count;
        private int currentCount;
        private WeakReference<ThreadPoolExecutor> weakReference;

        public Retry(int count) {
            this.count = count;
        }

        public Retry setSTPE(ThreadPoolExecutor stpe) {
            weakReference = new WeakReference<>(stpe);
            return this;
        }

        @Override
        public void run() {
            currentCount++;
            if (currentCount > count) {
                onComplete();
                shutdownNow(weakReference);
            } else {
                onTry();
            }
        }

        public abstract void onTry();

        public abstract void onComplete();

    }
}
