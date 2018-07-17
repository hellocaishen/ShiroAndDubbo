package com.wsbg.common;

import org.apache.shiro.authc.AuthenticationException;

// 自定义 账号禁用异常
public class AccountEnableException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8859205653519169180L;
	public AccountEnableException() {
      super();
 }
 public AccountEnableException(String message, Throwable cause) {
      super(message, cause);
 }
 public AccountEnableException(String message) {
      super(message);
 }
 public AccountEnableException(Throwable cause) {
      super(cause);
 }
	
}
