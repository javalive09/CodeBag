package com.javalive09.demos;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import com.javalive09.codebag.CodeBag;
import com.javalive09.codebag.Test;
import com.javalive09.codebag.Tester;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Tester(name = "线程")
public class ThreadTest {

    @Test(name = "死锁模型")
    public void deadLock() {
        new Thread(() -> methodA()).start();
        new Thread(() -> methodB()).start();
    }

    Object lockOne = new Object();
    Object lockTwo = new Object();

    private void methodA() {
        synchronized (lockOne) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockTwo) {
                Log.i("threadTest", "methodA");
            }
        }
    }

    private void methodB() {
        synchronized (lockTwo) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockOne) {
                Log.i("threadTest", "methodB");
            }
        }
    }

    @Test(name = "cachedThreadPool\n缓冲线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：0。如果任务结束，在60秒后，会回收所有工作线程")
    public void cachedThreadPool() {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    String name = Thread.currentThread().getName();
                    Log.i("threadTest", "currentThread name =" + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test(name = "FixedThreadPool 保持固定数量线程的线程池")
    public void fixedThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    String name = Thread.currentThread().getName();
                    Log.i("threadTest", "currentThread name =" + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test(name = "scheduleThreadPool\n计划线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：核心线程数。")
    public void scheduleThreadPool() {
        ExecutorService executor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    String name = Thread.currentThread().getName();
                    Log.i("threadTest", "currentThread name =" + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Test(name = "singleThreadPool\n有唯一线程的线程池，它和 Executors.newFixedThreadPool(1)的区别是：如果任务异常，会重新开启一个线程，继续执行")
    public void singleThreadPool() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    String name = Thread.currentThread().getName();
                    Log.i("threadTest", "currentThread name =" + name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    throw new Exception("12345");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    int count = 0;


    @Test
    public void timer() {
        TextView textView = new TextView(CodeBag.context());
        CodeBag.showView(textView);
        Handler handler = new Handler(Looper.getMainLooper());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if (CodeBag.context() != null) {
                    handler.post(() -> textView.setText("start:" + count));
                } else {
                    timer.cancel();
                }
            }
        }, 0, 2000);
    }

}
