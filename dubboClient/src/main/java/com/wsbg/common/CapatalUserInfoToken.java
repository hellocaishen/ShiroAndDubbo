package com.wsbg.common;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.util.StringUtils;

public class CapatalUserInfoToken extends UsernamePasswordToken{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	private String username;
	
	private char[] password;
	
	private boolean rememberMe = false;
	
	private String capatal;
	
	private String host;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(StringUtils.isEmpty(username))
			this.username= super.getUsername();
		else
		  this.username = username;
	}

	public char[] getPassword() {
		if(password==null){
			password=super.getPassword();
		}
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCapatal() {
		return capatal;
	}

	public void setCapatal(String capatal) {
		this.capatal = capatal;
	}

	private CapatalUserInfoToken(String username, char[] password,
			boolean rememberMe, String capatal, String host) {
		super();
		this.username = username;
		this.password = password;
		this.rememberMe = rememberMe;
		this.capatal = capatal;
		this.host = host;
	}
	
	
	 
	    
}
