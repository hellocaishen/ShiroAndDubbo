package com.wsbg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbg.dao.MediaFileDao;
import com.wsbg.entity.MediaFile;
import com.wsbg.service.MediaFileService;

@Service("mediaFileService")
public class MediaFileServiceImpl implements MediaFileService{
	
	@Autowired
	private MediaFileDao mediaFileDao;

	@Override
	public void saveObj(MediaFile arg0) {
		mediaFileDao.save(arg0);		
	}

	@Override
	public List<MediaFile> findList(MediaFile file) {
		return mediaFileDao.findList(file);
	}

}
