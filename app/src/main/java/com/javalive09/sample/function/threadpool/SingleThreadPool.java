
package com.javalive09.sample.function.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

/**
 * 有唯一线程的线程池，它和 Executors.newFixedThreadPool(1)的区别是：如果任务异常，会重新开启一个线程，继续执行
 * 
 * @author peter
 *
 */
public class SingleThreadPool extends Entry {

	public void runSingleThreadExecutor() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 10; i++ ) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						Log.i(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						throw new Exception("12345");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
//		executor.shutdown();
//		try {
//			executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}

