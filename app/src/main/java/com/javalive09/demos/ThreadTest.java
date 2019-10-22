package com.javalive09.demos;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.javalive09.codebag.CodeActivity;
import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Code(name = "Thread")
public class ThreadTest {

    @Run(name = "死锁模型")
    public void deadLock(CodeActivity activity) {
        new Thread(this::methodA).start();
        new Thread(this::methodB).start();
    }

    private Object lockOne = new Object();
    private Object lockTwo = new Object();

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

    @Run(name = "cachedThreadPool\n 缓冲线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：0。如果任务结束，在60秒后，会回收所有工作线程")
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

    @Run(name = "FixedThreadPool\n 保持固定数量线程的线程池")
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

    @Run(name = "scheduleThreadPool\n 计划线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：核心线程数。")
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

    @Run(name = "singleThreadPool\n 有唯一线程的线程池，它和 Executors.newFixedThreadPool(1)的区别是：如果任务异常，会重新开启一个线程，继续执行")
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

    @Run
    public void customPool(CodeActivity codeActivity) {
        ExecutorService executor = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1));
        Log.i("peter2", "start >>" + Thread.currentThread().getName());
        executor.execute(() -> {
            SystemClock.sleep(5_000);
            Log.i("peter2", Thread.currentThread().getName());
        });

        executor.execute(() -> {
            SystemClock.sleep(2_000);
            Log.i("peter2", Thread.currentThread().getName());
        });

        executor.execute(() -> {
            SystemClock.sleep(4_000);
            Log.i("peter2", Thread.currentThread().getName());
        });


    }

    private int count = 0;

    @Run
    public void timer(CodeActivity activity) {
        TextView textView = new TextView(activity);
        activity.setContentView(textView);
        Handler handler = new Handler(Looper.getMainLooper());
        Timer timer = new Timer();
        WeakReference<CodeActivity> activityWeakReference = new WeakReference<>(activity);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if (activityWeakReference.get() != null) {
                    handler.post(() -> textView.setText("start:" + count));
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    @Run
    public void interrupt(CodeActivity codeActivity) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    Log.i("peter", String.valueOf(System.currentTimeMillis()));
                    SystemClock.sleep(1000);
                }
            }
        };
        thread.start();

        TextView textView = new TextView(codeActivity);
        textView.setText("interrupt");
        codeActivity.setContentView(textView);
        textView.setOnClickListener(v -> {
            thread.interrupt();
            Log.i("peter", "interrupt:" + thread.isInterrupted());
        });
    }

    @Run
    public void callable(CodeActivity codeActivity) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future1 = executorService.submit(() -> {
            Log.i("ThreadTest", "future1 start");
            SystemClock.sleep(5 * 1000);
            Log.i("ThreadTest", "future1 end");
            return "result1";
        });
        Future<String> future2 = executorService.submit(() -> {
            Log.i("ThreadTest", "future2 start");
            SystemClock.sleep(2 * 1000);
            Log.i("ThreadTest", "future2 end");
            return "result2";
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(6 * 1000);
                try {
                    String f1 = future1.get();
                    Log.i("ThreadTest", "f1 result =" + f1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        String f2 = future2.get();
        Log.i("ThreadTest", "f2 result");

        codeActivity.showText("result = " + f2);
    }

    @Run
    public void callableTimeout(CodeActivity codeActivity) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(() -> {
            SystemClock.sleep(20 * 1000);
            return "result";
        });
        String result = "time out";
        try {
            result = future.get(5, TimeUnit.SECONDS); // 当前线程阻塞5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        codeActivity.showText("result = " + result);
    }

    public void callableUse(CodeActivity codeActivity) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future1 = executorService.submit(() -> {
            Log.i("ThreadTest", "future1 start");
            SystemClock.sleep(5 * 1000);
            Log.i("ThreadTest", "future1 end");
            return "result1";
        });
        Future<String> future2 = executorService.submit(() -> {
            Log.i("ThreadTest", "future2 start");
            SystemClock.sleep(2 * 1000);
            Log.i("ThreadTest", "future2 end");
            return "result2";
        });
        Future<String> future3 = executorService.submit(() -> {
            Log.i("ThreadTest", "future3 start");
            SystemClock.sleep(3 * 1000);
            Log.i("ThreadTest", "future3 end");
            return "result3";
        });

        future1.get();
        future2.get();
        future3.get();
    }

    @Run
    public void joinTimeout(CodeActivity codeActivity) throws Exception {
        Thread t = new Thread(() -> SystemClock.sleep(50000));
        t.start();
        t.join(20000); // 当前线程阻塞20秒
        codeActivity.showText("xxxx");
    }

    @Run
    public void threadPoolTaskCountTest(CodeActivity codeActivity) {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(0);
        stpe.schedule(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.e("peter", "abc");
            }
        }, 2000, TimeUnit.MILLISECONDS);

        stpe.schedule(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.e("peter", "abc");
            }
        }, 4000, TimeUnit.MILLISECONDS);

        Handler handler = new Handler(Looper.getMainLooper());

        StringBuffer infoB = new StringBuffer();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                codeActivity.showText(infoB.append("1----" + getInfo(stpe)).toString());
            }
        }, 1000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                codeActivity.showText(infoB.append("2----" + getInfo(stpe)).toString());
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                codeActivity.showText(infoB.append("3----" + getInfo(stpe)).toString());
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                codeActivity.showText(infoB.append("4----" + getInfo(stpe)).toString());
            }
        }, 7000);

        codeActivity.showText("===========");

        codeActivity.getWindow().getDecorView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                handler.removeCallbacksAndMessages(null);
                stpe.shutdownNow();
            }
        });
    }

    private String getInfo(ScheduledThreadPoolExecutor stpe) {
        return "taskCount=" + stpe.getTaskCount() + ",\n" +
                "activeCount=" + stpe.getActiveCount() + ",\n" +
                "queueSize=" + stpe.getQueue().size() + ",\n" +
                "completeCount=" + stpe.getCompletedTaskCount() + ",\n" +
                "poolSize=" + stpe.getPoolSize() + "\n";
    }

    @Run
    public void delay(CodeActivity codeActivity) {
        final WeakReference<ThreadPoolExecutor> delay = ScheduleUtils.delay(5000, new Runnable() {
            @Override
            public void run() {
                Log.i("peter", "delay"+ Thread.currentThread().getName());
            }
        });

        TextView textView = new TextView(codeActivity);
        textView.setText("delay");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleUtils.shutdownNow(delay);
            }
        });
        codeActivity.setContentView(textView);
    }

    @Run
    public void retry(CodeActivity codeActivity) {
        final WeakReference<ThreadPoolExecutor> retry = ScheduleUtils.retry(1000, 2000, new ScheduleUtils.Retry(3) {
            @Override
            public void onTry() {
                Log.i("peter", "onTry" + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.i("peter", "onComplete"+ Thread.currentThread().getName());
            }
        });

        TextView textView = new TextView(codeActivity);
        textView.setText("retry");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleUtils.shutdownNow(retry);
            }
        });
        codeActivity.setContentView(textView);
    }

    @Run
    public void timerOOM(CodeActivity codeActivity) {
        HashMap<Integer, Timer> times = new HashMap<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            times.put(i, new Timer());
            times.get(i).schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.i("peter", "----------");
                }
            }, 10);
            Log.i("peter", String.valueOf(i));
        }
    }

    @Run
    public void multiThread(CodeActivity codeActivity) {
            for(int i = 0; i< 10000; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("peter", "thread 1");
                            }
                        }).start();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.i("peter", "thread 2");
                            }
                        }).start();

                    }
                }).start();
                Log.i("peter", "end ===============");
                SystemClock.sleep(1000);
            }
    }

    @Run
    public void asyncTask(CodeActivity codeActivity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AsyncTask asyncTask = new AsyncTask<Void, Void, Void>(){

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        Log.i("peter", "onPreExecute thread :" + Thread.currentThread());
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        Log.i("peter", "onPostExecute thread :" + Thread.currentThread());
                    }

                    @Override
                    protected Void doInBackground(Void... voids) {
                        Log.i("peter", "doInBackground thread :" + Thread.currentThread());
                        return null;
                    }
                }.execute();
            }
        }).start();

    }


}
