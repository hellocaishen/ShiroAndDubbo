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
@Table(name="role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Role extends ComEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8654725848534652126L;
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="roleId",length=10,unique=true,nullable=false)
	private Integer roleId;
	
	@Column(name="roleName",length=32)
	private String  roleName;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	private User user;*/

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
	
	
}
