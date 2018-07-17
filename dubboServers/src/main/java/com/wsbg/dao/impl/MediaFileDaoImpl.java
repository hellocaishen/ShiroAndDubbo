package com.wsbg.dao.impl;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wsbg.dao.MediaFileDao;
import com.wsbg.entity.MediaFile;

@Repository
public class MediaFileDaoImpl extends BaseDaoImpl implements MediaFileDao{

	@Override
	public List<MediaFile> findList(MediaFile mediafile) {
		Session session = getSession();
		Query query = session.createQuery(" from MediaFile where fileType=:fileType");
		query.setParameter("fileType", mediafile.getFileType());
		List<MediaFile> list = query.list();
		return list;
	}

}
