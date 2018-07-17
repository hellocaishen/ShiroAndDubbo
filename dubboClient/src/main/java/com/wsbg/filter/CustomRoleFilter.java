package com.wsbg.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class CustomRoleFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject sub = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;  
		  
        if (rolesArray == null || rolesArray.length == 0) { //没有角色限制，有权限访问  
            return true;  
        }  
        
        for (int i = 0; i < rolesArray.length; i++) {  
            if (sub.hasRole(rolesArray[i])) { //若当前用户是rolesArray中的任何一个，则有权限访问  
                return true;  
            }  
        }  
		return false;
	}

}
