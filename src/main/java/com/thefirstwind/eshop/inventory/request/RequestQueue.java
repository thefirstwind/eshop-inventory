package com.thefirstwind.eshop.inventory.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class RequestQueue {

	private List<ArrayBlockingQueue<Request>> queues = new ArrayList<ArrayBlockingQueue<Request>>();

	private static class Singleton {
		
		private static RequestQueue instance;

		static {
			instance = new RequestQueue();
		}

		public static RequestQueue getInstance() {
			return instance;
		}
	}

	public static RequestQueue getInstance() {
		return Singleton.getInstance();
	}

	public void addQueue(ArrayBlockingQueue<Request> queue) {
		this.queues.add(queue);
	}
}
