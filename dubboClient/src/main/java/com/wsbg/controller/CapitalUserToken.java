package com.wsbg.controller;

import org.apache.shiro.authc.UsernamePasswordToken;

import com.wsbg.entity.User;

public class CapitalUserToken extends UsernamePasswordToken{

	/**
	 * 
	 */
	   private static final long serialVersionUID = 1L;

	    private String captcha;
	    private String locale;
	    private User user = new User();
	    private boolean rememberMe;
	    
	    public String getCaptcha() {
	        return captcha;
	    }

	    public void setCaptcha(String captcha) {
	        this.captcha = captcha;
	    }

	    public String getLocale() {
	        return locale;
	    }

	    public void setLocale(String locale) {
	        this.locale = locale;
	    }

	    
	
		public boolean getRememberMe() {
			return rememberMe;
		}

		public void setRememberMe(boolean rememberMe) {
			this.rememberMe = rememberMe;
		}

		@SuppressWarnings("unused")
		private CapitalUserToken() {
			super();
		}
        
		
		@SuppressWarnings("unused")
		private CapitalUserToken(User user,String captcha, String locale,
				boolean rememberMe) {
			super();
			this.user=user;
			this.captcha = captcha;
			this.locale = locale;
			this.rememberMe = rememberMe;
		}

		public CapitalUserToken(String username, char[] password, boolean rememberMe, String host, String captcha) {
	        super(username, password, rememberMe, host);
	        this.captcha = captcha;
	    }
	    public CapitalUserToken(String username, char[] password, String locale,boolean rememberMe, String host, String captcha) {
	        super(username, password, rememberMe, host);
	        this.captcha = captcha;
	        this.locale = locale;
	    }
	    
	    public CapitalUserToken(String username, char[] password, String locale,String rememberMe, String host, String captcha) {
	        super(username,password,host);
	        this.rememberMe=super.isRememberMe();
	        this.captcha = captcha;
	        this.locale = locale;
	    }
}
