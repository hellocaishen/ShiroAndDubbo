package com.wsbg.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import com.wsbg.entity.Resource;
import com.wsbg.entity.User;

/**
 * 用户数据
 * */
public interface UserDao extends BaseDao{
      
	//获取数据列表
	public  List<User> findListAll();
	//根据用户获取用户账号
	public User findUserByAccount(User user);
	//通过用户Id获取用户所拥有的角色
	public Set<String> findRoleByUserId(Integer userId);
	//获取角色所拥有的资源
	public Set<String> findResourceByRoleID(Integer userId);
	//获取登录账号获取资源
	public Set<Resource> findMenuByUserAccount(String account);
	//根据用户id获取用户信息
	public User findUserById(Integer currentUserId);
	//通过登录账号获取资源Id 集合
	public List<Integer> findResourceIds(String account);
	//获取用户列表集合
	public ResultData findUserAllPageList(Pager page);
	//删除当前用户信息
	public Integer deleteByUserId(Integer uId);
	//禁用当前用户
	public Integer disableById(Integer isable,Integer uId);
    //获取所有资源
	public ResultData findMenuPageList(Pager page);
	//获取角色Id
	public Integer findRoleIdByUserId(Integer integer);
	//
	public Integer addResourceByRoleId(String rIds,Integer uId);
	/***
	 * 获取系统父级资源
	 * */
	public Map<Integer, Object> findResourceObj();
	//保存角色
	public void saveRole(int in);
	
}
