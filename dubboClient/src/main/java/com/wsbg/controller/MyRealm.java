package com.wsbg.controller;

import java.util.Set;

import javax.annotation.Resource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.wsbg.common.AccountEnableException;
import com.wsbg.common.CommonStatic;
import com.wsbg.common.CutomeException;
import com.wsbg.common.ExceptionCodeEnum;
import com.wsbg.common.IncorrectCaptchaException;
import com.wsbg.entity.User;
import com.wsbg.service.UserService;

/**
 * @desc 自定义的realm类
 * 
 * */
public class MyRealm extends AuthorizingRealm{
    
	private Logger logger = LoggerFactory.getLogger(MyRealm.class);
	
	@Resource
	private UserService userService;
	
	/**
	 * shiro 权限认证
	 * */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/**
		 * 获取登录用户信息   根据MyRealm 时传入的 new SimpleAuthencationInfo(),如果传入的是用户账号信息
		 * 那么整个系统就是账号信息 
		**/
		User user = new User();
		user =(User) principalCollection.getPrimaryPrincipal();
		//String name = (String) principalCollection.fromRealm(getName()).iterator().next();
		//user.setUsername(name);
		//查询数据库中是否有此对象
		User loginUser = userService.findUserByAccount(user);
		if(loginUser!=null&&loginUser.getUserId()>0){
			 //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）    
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //获取登陆用户所拥有的权限
            Set<String> roles = userService.findRoleByUserId(loginUser.getUserId());
            Set<String> resources = userService.findResourceByRoleID(loginUser.getUserId());
            info.setRoles(roles);
            info.setStringPermissions(resources);
            return info;
		}
		return null;
	}

	/**
	 * 登陆认证
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationToken) throws AuthenticationException{
		 //UsernamePasswordToken对象用来存放提交的登录信息
		CapitalUserToken token = (CapitalUserToken) authenticationToken;
        //保存登录时选择的语言
		
        SecurityUtils.getSubject().getSession().setAttribute("locale", token.getLocale());
        String code = (String) SecurityUtils.getSubject().getSession().getAttribute(CommonStatic.VALIDATE_CODE);
        if(token.getCaptcha()==null || code == null || !code.equals(token.getCaptcha()) ){
        	 throw new IncorrectCaptchaException(ExceptionCodeEnum.CapatalIsErrException.msg);
        }
		User user = new User();
		user.setUsername(token.getUsername());
		//查询数据库中是否有此对象
		if(!StringUtils.isEmpty(token.getUsername())){
			if(userService==null){
				logger.info("inject userservie is error");
			}
			User loginUser = userService.findUserByAccount(user);
			if(loginUser!=null && loginUser.getUserId()!=null && loginUser.getUserId()>0){
				    //存放认证之后的信息
				//判断用户是否可用
				    if(loginUser.getIsable()==1){
					      throw  new AccountEnableException(ExceptionCodeEnum.AccountIsStopException.msg);
				    }
				    SecurityUtils.getSubject().getSession().setAttribute(CommonStatic.CURRENT_USER_ID,user.getUserId());
				     // simpleAuthenticationInfo   可以存放用户信息 ,用户获取用户数
				     //也可以第一个传入用户名 new SimpleAuthenticationInfo(user.getUserName(),password,"realNAME");
					return  new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(),"MyRealm");
			}else{
				   throw new UnknownAccountException();
			}
		}else{
			throw new CutomeException(ExceptionCodeEnum.AccountIsNull);
		}
	}

	/**
     * @desc  清空用户关联权限认证，待下次使用时重新加载
     * 根据具体需求来
     */
   /* public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }*/

    /**
     * @desc 清空所有关联认证
     * 按照具体需求来
     */
    public void clearAllCachedAuthorizationInfo() {
       /* Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }*/
    }
}
