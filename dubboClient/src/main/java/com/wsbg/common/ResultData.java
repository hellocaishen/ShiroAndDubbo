package com.wsbg.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @desc 装配数据
 * @author ligc
 * */
public class ResultData implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 4781632849709927689L;

	public Map<String,Object> paramMap= new HashMap<String, Object>();
	  
	  public List<?> list= null;
	  
	  public Pager page;
	    
      
	  public ResultData() {
	  	super();
	  }
	  
	  public ResultData(Map<String, Object> paramMap, List<Object> list,
	  		Pager page) {
	  	super();
	  	this.paramMap = paramMap;
	  	this.list = list;
	  	this.page = page;
	  }
	  
	  public Map<String, Object> getParamMap() {
	  	return paramMap;
	  }
	  
	  public void setParamMap(Map<String, Object> paramMap) {
	  	this.paramMap = paramMap;
	  }
	  
	  public List<?> getList() {
	  	return list;
	  }
	  
	  public void setList(List<?> list) {
	  	this.list = list;
	  }
	  
	  public Pager getPage() {
	  	return page;
	  }
	  
	  public void setPage(Pager page) {
	  	this.page = page;
	}
	 
	  
}
