package com.wsbg.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

/**
 * @desc 重写FormAuthenticationFilter 类中 onLoginSuccess 
 * @author lgc
 * */
@Service
public class MyFormAuthenticationFilter extends FormAuthenticationFilter{
	
    public static final String DEFAULT_CAPTCHA_PARAM = "yzm";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String locale = request.getParameter("locale");
        
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = getCaptcha(request);    
        return new CapitalUserToken(username, password.toCharArray(),locale, rememberMe, host, captcha);
    }


	  
	
	  
    /**
	 * @desc 登录成功跳转到我们指定的首页
	 * */
//	@Override
//	protected boolean onLoginSuccess(AuthenticationToken token,Subject subject, ServletRequest request, ServletResponse response)
//			throws Exception {
//		//清除shiro原先的request信息
//		WebUtils.getAndClearSavedRequest(request);
//		WebUtils.redirectToSavedRequest(request, response, "/index.do");
//		return super.onLoginSuccess(token, subject, request, response);
//	}
    
    
}
