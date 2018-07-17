package com.wsbg.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wsbg.common.Pager;
import com.wsbg.common.ResultData;
import com.wsbg.dao.UserDao;
import com.wsbg.entity.Resource;
import com.wsbg.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao{
   

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findListAll() {
		Session session = getSession();
		Query query = session.createQuery(" from User");
		List<User> list = query.list();
		return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public User findUserByAccount(User user) {
		Session session = getSession();
		Query query = session.createQuery("from User where name=:uname");
		query.setParameter("uname", user.getUsername());
		List<User> list = query.list();
		if(list!=null && !list.isEmpty()){
			user = list.get(0);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findRoleByUserId(Integer userId) {
		Query query = getSession().createSQLQuery("select r.roleName FROM Role r INNER JOIN user_role us ON r.roleId = us.roleId where us.userId =:uId");
		query.setParameter("uId", userId);
		List<String> roles = query.list();
		Set<String> set= new HashSet<String>();
		if(roles==null||roles.isEmpty())
			   return set;
		  else
			for (String role : roles) {
				set.add(role);
			}
		
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findResourceByRoleID(Integer userId) {
		Query query = getSession().createSQLQuery("select r.operationName FROM resource r  INNER JOIN role_resource s on r.resourceId = s.resourceId INNER JOIN user_role us ON s.roleId = us.roleId where us.userId =:uId");
		query.setParameter("uId", userId);
		List<String> list = query.list();
		Set<String> set= new HashSet<String>();
		
		if(list==null || list.isEmpty())
			   return set;
		  else
			   for (String permission : list) {
				   if(permission!=null && !"".equals(permission)){
					   set.add(permission);
				   }
		       }
		return set;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Resource> findMenuByUserAccount(String account) {
//		String sql ="select r.name PNAME,r.operationName POPERATIONNAME,r.url PURL,r.resourceId PRESOURCEID,o.resourceId ORESOURCEID,o.url OURL,o.name ONAME,o.operationName OPERATIONNAME from resource r " 
//                   +"LEFT JOIN resource o on r.resourceId = o.parentId "
//                   +"where r.url is null and r.resourceId in (select c.resourceId  from  user a inner join user_role b on a.Id = b.userId "
//                   +"INNER JOIN role_resource c on b.roleId = c.roleId "
//                   +"where a.name=:uname)";
		Session session = getSession();
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);//添加系统主菜单
		String hql = "from Resource r where r.url is null  and r.resourceId in (";
		StringBuffer buffer = new StringBuffer();
		if(list!=null && list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				buffer.append(list.get(i));
				if(i==list.size()-1){
					buffer.append(")");
				}else{
					buffer.append(",");
				}
			}
		}
		System.out.println(hql+buffer.toString());
		Query querys =  session.createQuery(hql+buffer.toString());
		List<Resource>  objs = querys.list();
		Set<Resource> set= new HashSet<Resource>();
		if(objs==null || objs.size()<1){
			  return set;
		}else{
			for (Resource resource : objs) {
				//已经确定系统主根资源  Id 为8
					set.add(resource);
			}
		}
		
		return set;
	}

	@Override
	public User findUserById(Integer currentUserId) {
		Session session = getSession();
		Query query = session.createQuery("from User where id=:uid");
		query.setInteger("uid", currentUserId);
		User user = (User) query.uniqueResult();
		
		return user;
	}

	@Override
	public List<Integer> findResourceIds(String account) {
		String sql = "select c.resourceId  from  user a inner join user_role b on a.Id = b.userId INNER JOIN role_resource c on b.roleId = c.roleId where a.name=:uname";
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		query.setString("uname", account);
		List<Integer> list = query.list();
		return list;
	}

	@Override
	public ResultData findUserAllPageList(Pager page) {
		Long totalNum = (long) this.findListAll().size();
		page.setTotalNum(totalNum);
	    page.setSqlStr("select * from User");
		List<?> listObj = findObjectPageList(page, null,new User());
		ResultData result = new ResultData();
		result.setParamMap(null);
		result.setList(listObj);
		result.setPage(page);
		return result;
	}

	@Override
	public Integer deleteByUserId(Integer uId){
		String sql = "delete from User a where a.id=:uId";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setInteger("uId",uId);
		int in = query.executeUpdate();
        
		return in;
	}

	@Override
	public Integer disableById(Integer isable,Integer uId){
		String sql = "update User a set a.isable=? where a.userId=?";
		Session session = getSession();
		Query query = session.createQuery(sql);
		query.setInteger(0,isable);
		query.setInteger(1,uId);
		int in = query.executeUpdate();
		
		return in;
	}

	@Override
	public ResultData findMenuPageList(Pager page){
		int pagesize = 0;
		Long totalNum = (long) this.findResourceIds("wsbg").size();
		page.setTotalNum(totalNum);
		page.setSqlStr("select * from Resource");
		page.setPageSize(pagesize);
		List<?> listObj = findObjectPageList(page, null,new Resource());
		ResultData result = new ResultData();
		result.setParamMap(null);
		result.setList(listObj);
		result.setPage(page);
		return result;
	}

	@Override
	public Integer findRoleIdByUserId(Integer uId){
		Query query = getSession().createSQLQuery("select r.roleId FROM Role r INNER JOIN user_role us ON r.roleId = us.roleId where us.userId =:uId");
		query.setParameter("uId", uId);
		Integer  rid = (Integer)query.uniqueResult();
		return rid;
	}

    @Override
	public Integer addResourceByRoleId(String rIds,Integer uid){
		Session session = getSession();
		//先删除存在的用户资源信息
		int rId = (int) session.createSQLQuery("select r.roleId from user_role r where r.userId="+uid).uniqueResult();
		session.createSQLQuery("delete from role_resource  where roleId =?").setInteger(0,rId).executeUpdate();
		session.clear();
		session.flush();
		int in = 0;
		String strs[] = rIds.split(",");
		for (int i = 0 ;i<strs.length;i++){
			  in = session.createSQLQuery("insert into role_resource(roleId,resourceId) values("+rId+","+Integer.valueOf(strs[i])+")").executeUpdate();
			 session.clear();
			 session.flush();
		}
		return in;
	}


	@Override
	public Map<Integer, Object> findResourceObj() {
		String hql = "from Resource r where r.url is null or r.url=''";
		Session session = getSession();
		Query query = session.createQuery(hql);
		List<Resource> list = query.list();
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		for (Resource resource : list) {
			 map.put(resource.getResourceId(), resource.getName());
		}
		return map;
	}


	@Override
	public void saveRole(int in) {
		Session session = getSession();
		int ins = session.createSQLQuery("insert into user_role (userId,roleId) values("+in+","+3+")").executeUpdate();
		System.out.println(ins+"____");
		
	}
}
