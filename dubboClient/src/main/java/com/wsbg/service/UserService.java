package com.wsbg.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import com.wsbg.entity.Resource;
import com.wsbg.entity.User;

public interface UserService {
       
	public List<User> findUserAll();
	
	public User findUserByAccount(User user);
	
	public Set<String> findRoleByUserId(Integer userId);
	
	public Set<String> findResourceByRoleID(Integer userId);
	 
    public Set<Resource> findMenuByUserAccount(String Account);

	public User findUserById(Integer currentUserId);
	
	public List<Integer> findResourceIds(String account);
    
	public ResultData findUserAllPageList(Pager page);

	public Integer deleteByUserId(Integer uId);

	public Integer disableById(Integer isable,Integer uId);

	public ResultData findMenuPageList(Pager page);

	public Integer findRoleIdByUserId(Integer uId);

	public Integer addResourceByRoleId(String rIds,Integer uId);

	public void update(User user);

	public void save(User user);
    /**
     * 获取资源的父级目录
     * **/
	public Map<Integer, Object> findResourceObj();
}
