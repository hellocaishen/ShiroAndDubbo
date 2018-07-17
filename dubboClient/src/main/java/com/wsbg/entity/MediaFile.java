package com.wsbg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "media_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MediaFile implements Serializable{

	/***/
	private static final long serialVersionUID = -5269160591113544688L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="Id",unique=true,nullable=false)
	private Long id;
	@Column(name="FILE_TYPE")	
	private Integer fileType;
	@Column(name="COME_FROM")	
	private Integer comeFrom;
	@Column(name="FILE_NAME")	
	private String fileName;
	@Column(name="LIKE_NUM")	
	private Long likeNum;
	@Column(name="IMG_DESC")	
	private String imgDesc;
	@Column(name="IS_DEL")	
	private Integer isDel;
	@Column(name="URL")	
	private String url;
	@Column(name="FILE_SIZE")	
	private Double fileSize;
	@Column(name="CREATE_MAN")	
	private String createMan;
	@Column(name="CREATE_DATE")	
	private Date createDate;
	@Column(name="UPDATE_MAN")	
	private String updateMan;
	@Column(name="UPDATE_DATE")	
	private Date updateDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getComeFrom() {
		return comeFrom;
	}
	public void setComeFrom(Integer comeFrom) {
		this.comeFrom = comeFrom;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Long likeNum) {
		this.likeNum = likeNum;
	}
	public String getImgDesc() {
		return imgDesc;
	}
	public void setImgDesc(String imgDesc) {
		this.imgDesc = imgDesc;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getFileSize() {
		return fileSize;
	}
	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateMan() {
		return updateMan;
	}
	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "[id=" + id + ", fileType=" + fileType + ", comeFrom=" + comeFrom + ", fileName=" + fileName
				+ ", likeNum=" + likeNum + ", imgDesc=" + imgDesc + ", isDel=" + isDel + ", url=" + url + ", fileSize="
				+ fileSize + ", createMan=" + createMan + ", createDate=" + createDate + ", updateMan=" + updateMan
				+ ", updateDate=" + updateDate + "]";
	}
	

}
