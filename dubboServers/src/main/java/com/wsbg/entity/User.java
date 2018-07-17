package com.wsbg.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends ComEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="Id",unique=true,nullable=false)
	private Integer userId;
	
	@Column(name="name",length=32,unique=true)
	private String  username;
	
	@Column(name="age",length=4)
	private Integer age;
	
	@Column(name="password",length=32)
	private String  password;
	
	@Column(name="telephone",length=11)
	private String  telephone;
	
	@Column(name="email",length=16)
	private String  email;
	
	@Column(name="isable") // 0 启用，1 禁用
	private Integer isable;
	
	@Column(name="headurl",length=500) 
	private String headurl;
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getHeadurl() {
		return headurl;
	}

	public void setHeadurl(String headurl) {
		this.headurl = headurl;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", age="
				+ age + ", password=" + password + ", telephone=" + telephone
				+ ", email=" + email +",isable="+isable+ "]";
	}

	public Integer getIsable() {
		return isable;
	}

	public void setIsable(Integer isable) {
		this.isable = isable;
	}
	
	
	 
}
