package com.wsbg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbg.dao.ResourceDao;
import com.wsbg.service.ResourceService;

@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	public void save(Object obj) {
		  resourceDao.save(obj);
	}

	@Override
	public void update(Object obj) {
		  resourceDao.update(obj);
	}

}
