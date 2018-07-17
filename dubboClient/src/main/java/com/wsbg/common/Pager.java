package com.wsbg.common;

import java.io.Serializable;


/***
 * @desc 配加分页实体类
 * @author liguocai
 * 
 * */
public class Pager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6584998793938876441L;
	
	 public String sqlStr;
   
	 //当前页
	 public Long currentPage;
	 
	 //下一页
	 public Long nextPage;
	 
	 //上一页
	 public Long lastPage;
	 
	 //每页的条数
	 public Integer pageSize = CommonStatic.PAGE_SIZE;
	 
	 //总条数
	 public Long totalNum;
	 
	 //总页码数
	 public Long totalPageSize;

    
	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public Long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}

	public Long getNextPage() {
		return nextPage;
	}

	public void setNextPage(Long nextPage) {
		this.nextPage = nextPage;
	}

	public Long getLastPage() {
		return lastPage;
	}

	public void setLastPage(Long lastPage) {
		this.lastPage = lastPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		if(totalNum==0){
			 totalPageSize = 0L;
		}else{
			 if(pageSize<1){
				 throw new CutomeException(ExceptionCodeEnum.ParamIsNotIllegalException);
			 }
			 totalPageSize = (long) Math.ceil(totalNum*1.0/pageSize);
		}
		this.totalNum = totalNum;
	}
	
	

	public Long getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(Long totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	@Override
	public String toString() {
		return "Pager [currentPage=" + currentPage + ", nextPage=" + nextPage
				+ ", lastPage=" + lastPage + ", pageSize=" + pageSize
				+ ", totalNum=" + totalNum + ", totalPageSize=" + totalPageSize
				+ "]";
	}

}
