package com.wutue.queue;

public interface QueueService {
  
	public void send(String Key, String topic,String message) ;
	
	public void consume() ;
}
