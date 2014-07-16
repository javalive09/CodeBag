
package com.codebag.code.mycode.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;

import com.codebag.bag.CaseListView;
import com.codebag.bag.Entry;

/**
 * 计划线程池，工作线程上限：无限制。跟jvm的线程上限有关。 工作线程下限：核心线程数。
 * 
 * @author peter
 *
 */
public class ScheduleThreadPool extends CaseListView {

	public ScheduleThreadPool(Context context) {
		super(context);
	}

	@Entry()
	public void runScheduledThreadPool() {
		ExecutorService executor = Executors.newScheduledThreadPool(3);
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

