package com.wsbg.dao;

import java.util.List;

import com.wsbg.entity.MediaFile;

public interface MediaFileDao extends BaseDao{
     
	   //获取列表数据
	   List<MediaFile> findList(MediaFile file);
}
