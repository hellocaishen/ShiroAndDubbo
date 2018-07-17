package com.wsbg.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/***
 * @desc 第一个消费者
 * **/
public class Customer1 implements MessageListener{
	
  
	@Override
	public void onMessage(Message msg) {
		try {
			TextMessage text = (TextMessage) msg;
			System.out.println("topic:"+":"+text.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
