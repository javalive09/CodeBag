package com.codebag.code.mycode.test.threadpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class TaskControl extends MyCode {

	public TaskControl(InovkedViewActivity act) {
		super(act);
	}

	public static final ThreadPoolExecutor mExecutor = buildDownloadExecutor();

	@SuppressLint("NewApi")
	private static ThreadPoolExecutor buildDownloadExecutor() {
		final int maxConcurrent = 1;

		// Create a bounded thread pool for executing downloads; it creates
		// threads as needed (up to maximum) and reclaims them when finished.
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(
				maxConcurrent, maxConcurrent, 0, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>()) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);

				if (t == null && r instanceof Future<?>) {
					try {
						((Future<?>) r).get();
					} catch (CancellationException ce) {
						t = ce;
					} catch (ExecutionException ee) {
						t = ee.getCause();
					} catch (InterruptedException ie) {
						Thread.currentThread().interrupt();
					}
				}

				if (t != null) {
					Log.w("DonwloadInfo", "Uncaught exception", t);
				}
			}
		};
		executor.allowCoreThreadTimeOut(false);
		return executor;
	}
	
	MyTask taskA = new MyTask("a");
	MyTask taskB = new MyTask("b");
	MyTask taskC = new MyTask("c");
	MyTask taskD = new MyTask("d");
	MyTask taskE = new MyTask("e");
	
	ArrayList<MyTask> tasks = new ArrayList<MyTask>();

	HashMap<MyTask, Future<?>> tasks_ = new HashMap<MyTask, Future<?>>();
	
	@Entry
    public void invoke() {
		tasks.clear();
		tasks.add(taskA);
		tasks.add(taskB);
		tasks.add(taskC);
		tasks.add(taskD);
		tasks.add(taskE);
		taskA.done = false;
		taskB.done = false;
		taskC.done = false;
		taskD.done = false;
		taskE.done = false;
		
		tasks_.put(taskA, mExecutor.submit(taskA));
		tasks_.put(taskB, mExecutor.submit(taskB));
		tasks_.put(taskC, mExecutor.submit(taskC));
		tasks_.put(taskD, mExecutor.submit(taskD));
		tasks_.put(taskE, mExecutor.submit(taskE));
    }
	
	public void changefirsit(MyTask myTask) {
		//结束任务
		for(MyTask task : tasks) {
			boolean suc = false;
			do {
				task.done = true;
				suc = mExecutor.remove(task);
			}while(suc);
		}
		
		//重排任务
		myTask.done = false;
		mExecutor.submit(myTask);
		for(MyTask task : tasks) {
			if(!task.equals(myTask)) {
				task.done = false;
				mExecutor.submit(task);
			}
		}
		
	}
	
	@Entry
	public void changeCfirsit() {
		changefirsit(taskC);
	}
	
	@Entry
	public void changeDfirsit() {
		changefirsit(taskD);
	}
	
	@Entry
	public void changeEfirsit() {
		changefirsit(taskE);
	}

	static class MyTask implements Runnable {

		String taskName;
		boolean done = false;
		
		public MyTask(String name) {
			taskName = name;
		}
		
		@Override
		public void run() {
			long start = System.currentTimeMillis();
			while(!done) {
				Log.i("peter", "taskName =" + taskName);
				try {
					Thread.sleep(1000);
					long delta = System.currentTimeMillis() - start;
					if(delta > 1000 * 5) {
						done = true;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
