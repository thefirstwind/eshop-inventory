package com.thefirstwind.eshop.inventory.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.thefirstwind.eshop.inventory.request.Request;
import com.thefirstwind.eshop.inventory.request.RequestQueue;

/**
 * 线程池：单例模式
 * 
 * @author xingxn
 *
 */
public class RequestProcessorThreadPool {

	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	/**
	 * 初始化将 RequestQueue和 线程池绑定在一起
	 */
	public RequestProcessorThreadPool() {
		
		// 内存队列的外部封装类 List 中 10个元素
		RequestQueue requestQueue = RequestQueue.getInstance();
		
		// 初始化创建10个内存队列的 ArrayBlockingQueue对象
		for(int i = 0 ; i < 10; i++) {

			ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<Request>(100);
			requestQueue.addQueue(queue);
			
			// 内存队列和线程池绑定
			threadPool.submit(new RequestProcessorThread(queue));
		}
	}
	/**
	 * 使用绝对线程安全的静态内部类方式，实现单例
	 */

	private static class Singleton {
		private static RequestProcessorThreadPool instance;
		static {
			instance = new RequestProcessorThreadPool();
		}

		public static RequestProcessorThreadPool getInstatnce() {
			return instance;
		}
	}
	
	/**
	 * jvm的机制，保证多线程并发安全，
	 * 
	 * 不管多少线程并发请求，一定只会发生一次
	 *
	 * @return
	 */
	public static RequestProcessorThreadPool getInstance() {
		return Singleton.getInstatnce();
	}
	/**
	 * 初始化便捷方法
	 * @param queue
	 * 
	 */
	public static void init() {
		getInstance();
	}

}
