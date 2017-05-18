
package com.javalive09.sample.function.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;


/**
 * 缓冲线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：0。如果任务结束，在60秒后，会回收所有工作线程
 * 
 * @author peter
 *
 */
public class CachedThreadPool extends Entry {


	public void runCachedThreadPool() {
		ExecutorService executor = Executors.newCachedThreadPool();
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

