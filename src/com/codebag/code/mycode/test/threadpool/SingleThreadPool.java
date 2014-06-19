
package com.codebag.code.mycode.test.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.content.Context;

import com.codebag.bag.CaseListView;

/**
 * @author peter
 *
 */
public class SingleThreadPool extends CaseListView {

	public SingleThreadPool(Context context) {
		super(context);
	}

	public void run_singleThreadExecutor() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
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
					try {
						throw new Exception("12345");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

