package com.codebag.code.mycode.product_consumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Action extends MyCode {

	public Action(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void start() {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		Thread producer = new Thread(new Producer(queue, 2, "producer"));
		Thread consumer = new Thread(new Consumer(queue, "consumer"));
		producer.start();
		consumer.start();
	}

	public static class Producer implements Runnable {
		private Queue<Integer> mQueue;
		private int maxSize;
		
		public Producer(Queue<Integer> q , int size, String name) {
			mQueue = q;
			maxSize = size;
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (mQueue) {
					while (mQueue.size() == maxSize) {//队列满了 则等待 不再生产
						try {
							if(mQueue.size() == 2) {
								Log.i("peter", "i == " + mQueue.size());
							}
							mQueue.notifyAll();//通知消费
							mQueue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
					
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				Random random = new Random();
				int i = random.nextInt();
				mQueue.add(i);
				Log.i("peter", "producing value = " + i);
			}
		}
		
	}
	
	public static class Consumer implements Runnable{
		private Queue<Integer> mQueue;
		
		public Consumer(Queue<Integer> q, String name) {
			mQueue = q;
		}
		
		@Override
		public void run() {
			while(true) {
				synchronized (mQueue) {
					while(mQueue.isEmpty()) {//队列中没有产品了
						try {
							mQueue.notifyAll();//通知生产
							mQueue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int i = mQueue.remove();
				Log.i("peter", "Consumer value = " + i);
				
			}
		}
	}
	
}
