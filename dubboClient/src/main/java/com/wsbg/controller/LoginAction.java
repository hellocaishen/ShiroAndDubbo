package com.wsbg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.wsbg.entity.User;
import com.wsbg.service.UserService;

@Controller
public class LoginAction extends BaseAction{
	   
	   private Logger logger = LoggerFactory.getLogger(LoginAction.class);
	    
	    @Autowired
		public UserService userService;
	    
	    @RequestMapping(name="/login.do")    
	    public String login(User user,RedirectAttributes redirectAttributes,String yzm,HttpServletRequest request,HttpServletResponse response,Model model){
	    	logger.info("get login start ...."+yzm);
	    	if(StringUtils.isBlank(yzm)) {
	    		return "/login";
	    	}
	    	Object obj = request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	        try { 
	            if(user==null || user.getUsername()==null || "".equals(user.getUsername()) || user.getPassword()==null || "".equals(user.getPassword())){
	            	return "/login";
	            }
	            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！    
	            //User loginUser = userService.findUserByAccount(user);
	            //logger.info("get login end ......."+loginUser.getHeadurl());
//	            request.getSession().setAttribute("user",loginUser);
	           // SecurityUtils.getSubject().getSession().setAttribute("user", loginUser);
	            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword()));
	            return "/index";    
	        }catch (AuthenticationException e) {
	        	String msg = null;
	            if( obj != null ){
	                if( "org.apache.shiro.authc.UnknownAccountException".equals( obj ) )
	                      msg = "账户未注册！";
	                else if("org.apache.shiro.authc.IncorrectCredentialsException".equals( obj ))
	                      msg = "密码错误！";
	                else if("com.wsbg.tool.IncorrectCaptchaException".equals( obj ))
	                      msg = "验证码错误！";
	                else if( "org.apache.shiro.authc.AuthenticationException".equals( obj ))
	                      msg = "认证失败！";
	                else if("com.wsbg.tool.AccountEnableException".equals(obj)){
	                	  msg="该账号已禁用,请联系管理员!";
	                }
	            }
	        	redirectAttributes.addFlashAttribute("message",msg);
	            return "/login";    
	        }
	    }
	    
	  /*  @RequestMapping(value="/login",method=RequestMethod.GET)    
	    public String loginForm(Model model,HttpServletRequest request){
	    	String url = request.getRequestURL().toString();
	        model.addAttribute("user", new User());    
	        return "/login";    
	    }  */
	    
	    @RequestMapping(value="/logout")    
	    public String loginForm(RedirectAttributes redirectAttributes){
	    	logout(redirectAttributes);
	        return "/login";    
	    }  
	    
	    @RequestMapping("/index")    
	    public String login(RedirectAttributes redirectAttributes){ 
	    	return "/index";
	    } 
	    
	    @RequestMapping("/unthenc")    
	    public String unthenc(){ 
	    	return "/403";
	    }

		@RequestMapping("/timeout")
		public String timeout(){
			return "/timeout";
		}
}
