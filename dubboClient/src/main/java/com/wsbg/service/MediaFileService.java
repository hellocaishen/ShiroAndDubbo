package com.wsbg.service;

import java.util.List;

import com.wsbg.entity.MediaFile;

public interface MediaFileService {
     void saveObj(MediaFile file);
    //获取列表数据
	List<MediaFile> findList(MediaFile file);
}
