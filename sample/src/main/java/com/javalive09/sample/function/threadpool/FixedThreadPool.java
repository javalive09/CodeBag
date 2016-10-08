
package com.javalive09.sample.function.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.javalive09.codebag.Entry;

/**
 * FixedThreadPool 保持固定数量线程的线程池
 * 
 * @author peter
 *
 */
public class FixedThreadPool extends Entry {

	public void runFixedThreadPool() {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for(int i = 0; i < 10; i++ ) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName());
					} catch (InterruptedException e) {
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

