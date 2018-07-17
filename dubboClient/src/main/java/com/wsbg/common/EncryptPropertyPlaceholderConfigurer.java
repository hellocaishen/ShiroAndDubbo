package com.wsbg.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/*
 * 实现数据接口加密密钥
 * @author lgc
 * **/
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private final Logger logger = LoggerFactory.getLogger(EncryptPropertyPlaceholderConfigurer.class);
	private static final String[] encryKeys = {"db.driver","db.user","db.password","db.url"};
	/**
	 * 根据加密的属性一次进行解密
	 * */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		//通过属性进行属性还原
		if(isEncryKey(propertyName)){
			  logger.info("database encryptString start "+propertyName); 
			  try {
				  String pwd = propertyName;
				  propertyName = AESUtil.decrypt(CommonStatic.DATA_DES_KEY, propertyValue);
				  logger.info("DataBase:key:"+propertyName+",value:"+propertyValue);
			   } catch (Exception e) {
				  e.printStackTrace();
				  logger.info("connect encryString is error");
			   }
			  logger.info("database decryString end "+propertyName);
			  return propertyName;
		}
		return propertyValue;
	}

	private static boolean isEncryKey(String property){
		for (int i = 0; i<encryKeys.length; i++) {
			 if (property!=null && property.equals(encryKeys[i])) {
				  return true;
			 }
		}
		return false;
	}

}
