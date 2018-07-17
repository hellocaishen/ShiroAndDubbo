package com.wsbg.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * 用户许需要的资源
 * 
 * */
@Entity
@Table(name="resource")
@GenericGenerator(name="genID", strategy="increment")
public class Resource extends ComEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1888509392864193922L;
   
	@Id
	@GeneratedValue(generator="genID")  
	@Column(name="resourceId",unique=true,nullable=false)
	private Integer resourceId;
	
	@Column
	private String  name;
	
//	@Column
//	private Integer parentId;
	
	@Column
	private String operationName;
	
	@Column
	private String  url;

	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="resource")
	private Set<com.wsbg.entity.Resource> resourceSet = new HashSet<Resource>();
	
	@ManyToOne
	@JoinColumn(name="parentId")
	private com.wsbg.entity.Resource resource;
	
	@Transient
	private Integer isParent;//0 父级 1 非父级
	
	
	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Integer getParentId() {
//		return parentId;
//	}
//
//	public void setParentId(Integer parentId) {
//		this.parentId = parentId;
//	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

//	@Override
//	public String toString() {
//		return "Resource [resourceId=" + resourceId + ", name=" + name
//				+ ", parentId=" + parentId + ", operationName=" + operationName
//				+ ", url=" + url + "]";
//	}

	public Set<Resource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

	public com.wsbg.entity.Resource getResource() {
		return resource;
	}

	public void setResource(com.wsbg.entity.Resource resource) {
		this.resource = resource;
	}

	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	
	
	
}
