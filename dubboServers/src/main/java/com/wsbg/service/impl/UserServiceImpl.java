package com.wsbg.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import com.wsbg.dao.UserDao;
import com.wsbg.entity.Resource;
import com.wsbg.entity.User;
import com.wsbg.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findUserAll() {
		return userDao.findListAll();
	}

	@Override
	public User findUserByAccount(User user) {
		return userDao.findUserByAccount(user);
	}

	@Override
	public Set<String> findRoleByUserId(Integer userId) {
		return userDao.findRoleByUserId(userId);
	}

	@Override
	public Set<String> findResourceByRoleID(Integer userId) {
		return userDao.findResourceByRoleID(userId);
	}

	@Override
	public Set<Resource> findMenuByUserAccount(String Account) {
		return userDao.findMenuByUserAccount(Account);
	}

	@Override
	public User findUserById(Integer currentUserId) {
		return userDao.findUserById(currentUserId);
	}

	@Override
	public List<Integer> findResourceIds(String account) {
		return userDao.findResourceIds(account);
	}

	@Override
	public ResultData findUserAllPageList(Pager page) {
		return userDao.findUserAllPageList(page);
	}

	@Override
	public Integer deleteByUserId(Integer uId){
		return userDao.deleteByUserId(uId);
	}

	@Override
	public Integer disableById(Integer isable,Integer uId) {
		return userDao.disableById(isable,uId);
	}

	@Override
	public ResultData findMenuPageList(Pager page) {
		return userDao.findMenuPageList(page);
	}

	@Override
	public Integer findRoleIdByUserId(Integer uId){return userDao.findRoleIdByUserId(uId);}

	@Override
	public Integer addResourceByRoleId(String rIds,Integer uId){
		   return userDao.addResourceByRoleId(rIds,uId);
	}

	@Override
	public void update(User user) {
		 userDao.update(user);
	}

	@Override
	public void save(User user) {
		 int in = userDao.save(user);
		 //新建用户保存一般角色
		 userDao.saveRole(in);
	}

	@Override
	public Map<Integer, Object> findResourceObj() {
		return userDao.findResourceObj();
	}
}
