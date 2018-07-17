package com.wsbg.mq;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.wsbg.common.ResultMap;

@Component("topicSender")
public class TopicSender {
   
	 //@Resource
	 private JmsTemplate  jmsTopicTemplate;
	 
	 private static final String queueName = "wsbg.topic";

     public ResultMap sendInfo(String info){
    	 // Destination：消息的目的地;消息发送给谁.
    	  Destination destination=null;
    	  MessageConsumer consumer=null;
    	  MessageProducer producer=null;
    	  TextMessage message = null;
    	  Connection conn= null;
    	 try{
    		ConnectionFactory connfactory= jmsTopicTemplate.getConnectionFactory();
    	    conn = connfactory.createConnection();
    	   Session session = conn.createSession(true,Session.AUTO_ACKNOWLEDGE);//创建session 不带事物
    	   destination= session.createTopic(queueName);
    	   producer=session.createProducer(destination);
    	   message = session.createTextMessage(info);
    	   //设置不持久化，此处学习，实际根据项目决定
           producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
           producer.send(message);
    	   //设置不持久化，此处学习，实际根据项目决定
           session.commit();
    	 }catch(Exception e){
    		 e.printStackTrace();
    		 System.out.println(e.getMessage());
    		 return new ResultMap(false,"发送内容失败！");
    	 }finally{
    			   try {
    				 if(conn!=null){
					      conn.close();
    				 }
					} catch (JMSException e) {
						e.printStackTrace();
					}
    	 }
    	 return new ResultMap(true,"发送jms成功!");
     }
}
