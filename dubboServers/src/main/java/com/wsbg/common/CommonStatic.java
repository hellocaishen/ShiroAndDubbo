package com.wsbg.common;


public interface CommonStatic {
      
	  //数据库的加密密钥 test
	  public static final  String DATABASE_DES_KEY = "AqManager_encrypt_key_test";
	  
	  //数据库正式加密密钥
	  public static final String  DATA_DES_KEY = "AqManager_encrypt_key";
	  
	  //用戶名密碼加密方式
	  public static final String  ENCRYPT_WAY="MD5";
	  
	  //前台登录验证码
	  public final String  VALIDATE_CODE="ShiroCode";
	  
	  //shiro默认sessionkey
	  public final String SHIRO_SESSION_USER_KEY="org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";
	  
	  //系统存放sessionkey
	  public static String CURRENT_USER_ID="currentUserId";
	  
	  
	  
	  //分页-当前页码数
	  //public static Integer PAGE_SIZE= 20;
	  //测试分页条数
	  public static Integer PAGE_SIZE= 2;
	  
	  
}
