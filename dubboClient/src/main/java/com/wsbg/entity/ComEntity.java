package com.wsbg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 注解映射实体超类
 * */
@MappedSuperclass
public class ComEntity{
     
	 @Column(name="createTime")
	 private Date createTime;
	 
	 @Column(name="updateTime")
	 private Date updateTime;
	 
	 @Column(name="createUser",length=32)
	 private String createUser;
	 
	 @Column(name="updateUser",length=32)
	 private String updateUser;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	 
	 
}
