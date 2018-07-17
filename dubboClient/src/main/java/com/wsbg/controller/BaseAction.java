package com.wsbg.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wsbg.common.Pager;
import com.wsbg.entity.User;

/**
 * action的父类
 * 封装基本的代码数据项
 ***/
public class BaseAction {
	 
	 public Logger logger  = LoggerFactory.getLogger(this.getClass());
      /***
       * 退出当前系统
       * **/
	public void  logout(RedirectAttributes redirectAttributes){
		    SecurityUtils.getSubject().logout();
		    redirectAttributes.addFlashAttribute("message", "您已安全退出");
	}
	
	/**
     * 验证是否登陆
     * 
     * org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY ; true
     * org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY ; com.hncxhd.bywl.entity.manual.UserInfo@533752b2
     */
    public boolean isAuthenticated(String sessionID,HttpServletRequest request,HttpServletResponse response){
        boolean status = false;
        Session se = null;
        Object obj = null;
        SessionKey key = new WebSessionKey(sessionID,request,response);
        try{
             se = SecurityUtils.getSecurityManager().getSession(key);
             obj = se.getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY);
            if(obj != null){
                status = (Boolean) obj;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return status;
    }
    /**
     * 获取用户登录信息
     * 
     */
    public User getUserInfo(String sessionID,HttpServletRequest request,HttpServletResponse response){
      //  boolean status = false;
        SessionKey key = new WebSessionKey(sessionID,request,response);
        try{
            Session se = SecurityUtils.getSecurityManager().getSession(key);
            Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            //org.apache.shiro.subject.SimplePrincipalCollection cannot be cast to com.hncxhd.bywl.entity.manual.UserInfo
            SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
            return (User)coll.getPrimaryPrincipal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }
        return null;
    }
    /**
     * 获取真实的ip地址 
     * ***/
    public  String getRealAddrIp(HttpServletRequest request){
    	 String ip = request.getHeader("x-forwarded-for"); 
    	 logger.info("x-forwarded-for ip: " + ip);
         if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {  
             // 多次反向代理后会有多个ip值，第一个ip才是真实ip
             if( ip.indexOf(",")!=-1 ){
                 ip = ip.split(",")[0];
             }
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("Proxy-Client-IP");  
            logger.info("Proxy-Client-IP ip: " + ip);
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("WL-Proxy-Client-IP");  
            logger.info("WL-Proxy-Client-IP ip: " + ip);
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_CLIENT_IP");  
            logger.info("HTTP_CLIENT_IP ip: " + ip);
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
            logger.info("HTTP_X_FORWARDED_FOR ip: " + ip);
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getHeader("X-Real-IP");  
            logger.info("X-Real-IP ip: " + ip);
         }  
         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
             ip = request.getRemoteAddr();  
            logger.info("getRemoteAddr ip: " + ip);
         } 
        logger.info("获取客户端ip: " + ip);
         return ip;  
    }
    
    /**
     * @获取当前登录用户信息
     * */
	public User getUser(Session session){
    	   User user = new User();
    	   /*由于 new SimpleAuthencationInfo(User,pwd,realName) 第一个传入的是用户对象 那么 此处获取的 是user对象  
    	   * 如果放置时用户名称   那么取到的是用户名称
    	   * 1).获取当前用户方式
    	   */ 
    	   //Object objs =  session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
    	  //2).h获取当前登录用户
    	  user =  (User) SecurityUtils.getSubject().getPrincipal();
    	  return user;
    }
    
    /**
     * @DESC 获取当前页码数对象
     * ***/
    public  void setCurrentPg(Pager page){
    	 if(page.getCurrentPage()==null){
	    	    page.setCurrentPage(1L);
    	 }
    }
    
    /***
     * @desc 输出内容
     * @param response
     * */
    public void outTxt(HttpServletResponse response,String msg){
    	PrintWriter write =null;
    	try{
    	    write =	response.getWriter();
    	    write.write(msg);
    	    write.flush();
    	}catch(Exception e){
    		logger.info(e.getMessage());
    	}finally{
    		if(write!=null){
    			write.close();
    		}
    	}
    	
    }
}
