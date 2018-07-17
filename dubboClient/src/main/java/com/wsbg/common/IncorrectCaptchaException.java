package com.wsbg.common;

import org.apache.shiro.authc.AuthenticationException;

/***
 * 自定义图形码异常
 * */
	    //图形码验证异常
   public class  IncorrectCaptchaException extends AuthenticationException{
 
	private static final long serialVersionUID = 1L;
	
	public IncorrectCaptchaException() {
         super();
    }
    public IncorrectCaptchaException(String message, Throwable cause) {
         super(message, cause);
    }
    public IncorrectCaptchaException(String message) {
         super(message);
    }
    public IncorrectCaptchaException(Throwable cause) {
         super(cause);
    }
   } 
	  