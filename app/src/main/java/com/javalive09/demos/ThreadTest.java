package com.javalive09.demos;

import android.util.Log;
import android.widget.TextView;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;
import com.javalive09.codebag.PlayerActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Player(name = "线程")
public class ThreadTest {

    @Play(name = "死锁模型")
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

    @Play(name = "cachedThreadPool\n缓冲线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：0。如果任务结束，在60秒后，会回收所有工作线程")
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

    @Play(name = "FixedThreadPool 保持固定数量线程的线程池")
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

    @Play(name = "scheduleThreadPool\n计划线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：核心线程数。")
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

    @Play(name = "singleThreadPool\n有唯一线程的线程池，它和 Executors.newFixedThreadPool(1)的区别是：如果任务异常，会重新开启一个线程，继续执行")
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

    @Play
    public void timer() {
        TextView textView = new TextView(PlayerActivity.context());
        PlayerActivity.context().showView(textView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                PlayerActivity playerActivity = PlayerActivity.context();
                if (playerActivity != null) {
                    playerActivity.runOnUiThread(() -> textView.setText("start:" + count));
                } else {
                    timer.cancel();
                }
            }
        }, 0, 2000);
    }

}
