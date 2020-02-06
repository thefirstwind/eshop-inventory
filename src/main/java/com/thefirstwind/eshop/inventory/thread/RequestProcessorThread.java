package com.thefirstwind.eshop.inventory.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

import com.thefirstwind.eshop.inventory.request.Request;

public class RequestProcessorThread implements Callable<Boolean>{

	private ArrayBlockingQueue<Request> queue;
	
	public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
		this.queue = queue;
	}
	
	@Override
	public Boolean call() throws Exception {
		
		while(true) {
			break;
		}
		return true;
	}

}
