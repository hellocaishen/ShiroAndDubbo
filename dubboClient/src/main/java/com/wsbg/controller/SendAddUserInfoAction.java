package com.wsbg.controller;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wsbg.common.ResultMap;
import com.wsbg.mq.QueueSender;
import com.wsbg.mq.TopicSender;

@Controller
@RequestMapping("/msg")
public class SendAddUserInfoAction extends BaseAction{
    
	//@Resource
	 private QueueSender  queueSender;
	 
	 //@Resource
	 private TopicSender  topicSender;
	// @Value("#{configProperties['mq.queue']}")
	 public String queueName;
	 
	// @Value("${mq.topic}")
	 public String topicName;
	 
     
     @RequestMapping("/sendqueue.do")
     @ResponseBody
     public ResultMap sendInfo(String info){
    	// System.out.println("param:"+queueName);
    	 System.out.println(topicName+":参数");
    	 if(null!=info && !"".equals(info)){
    		 queueSender.sendInfo(info);
    	 }else{
    		 queueSender.sendInfo("Hi,I'm queue");
    	 }
    	 return new ResultMap(true,"发送queue-jms成功!");
     }
	  
     @RequestMapping("/sendtopic.do")
     @ResponseBody
     public ResultMap sendTopicInfo(String info){
    	 if(null!=info && !"".equals(info)){
    		 topicSender.sendInfo(info);
    	 }else{
    		 topicSender.sendInfo("Hi,I'm Topic");
    	 }
    	 return new ResultMap(true,"发送topic-jms成功!");
     }

}
