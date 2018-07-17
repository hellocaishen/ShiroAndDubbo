package com.wsbg.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wsbg.common.Pager;
import com.wsbg.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao{
	
    @Resource    
	private SessionFactory sessionFactory;

    @Resource
	private JdbcTemplate   jdbcTemplate;
	
	public Session getSession(){
		Session session = null;
		 if(sessionFactory!=null){
			  session =sessionFactory.getCurrentSession() ;
		 }
		 if(session==null){
			  session = sessionFactory.openSession();
		 }
		 return session;
	}

	/**
	 * 处理由于链接池数量过剩的问题
	 * */
   public void close(Session session){
		 if(session!=null){
			 session.close();
		 }
   }
	
   /**
    * @desc 公共分页类
    * @param Pager page
    * @param paramMap
    * @param obj 
    * @return
    * ***/
@SuppressWarnings("rawtypes")
protected List<?> findObjectPageList(Pager page,Map<String,Object> paramMap,Object obj){
	   Session session= getSession();
	   List<?> list = null;
	   try{
		   Query query = session.createSQLQuery(page.getSqlStr()).addEntity(obj.getClass());
	       if(paramMap!=null && paramMap.size()>0){
	    	     Iterator iterator = paramMap.keySet().iterator();
	    	     while(iterator.hasNext()){
	    	    	  String key = (String) iterator.next();
	    	    	  query.setParameter(key, paramMap.get(key));
	    	     }
	       }
	       //设定起始
	       int startPg = (int) ((page.getCurrentPage()-1)*page.getPageSize());
	       int endPg   = (int) (page.getPageSize()*page.getCurrentPage());
	       System.out.println("query page param:--->startPg:"+startPg+"  endPg:"+endPg);
	       query.setFirstResult(startPg);
	       query.setMaxResults(endPg);
	       list = query.list();
	   }catch(Exception e){
		     e.printStackTrace();
	   }
	   return list;
   }

   /**
    * @desc 获取链接池，原生态的sql
    * @author
    * */
    private Connection getConn() throws SQLException{
          if(jdbcTemplate!=null){
               DataSource ds = jdbcTemplate.getDataSource();
               if(ds!=null){
                   ds.getConnection();
               }
          }
        return null;
    }
    
    /***
     * @desc 保存用户
     * */
    @Override
	public Integer save(Object object) {
    	//Transaction tranc = getSession().beginTransaction();
		Long in = Long.parseLong(getSession().save(object).toString());
		System.out.println(in);
		getSession().flush();
		//tranc.commit();
		return 0;
	}

	@Override
	public void delete(Object object) {
		 getSession().delete(object);
	}
	
	@Override
	public void update(Object object) {
		getSession().saveOrUpdate(object);
	}
}
