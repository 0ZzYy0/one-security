package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.modules.sys.dao.BasFileDao;
import com.one.modules.sys.entity.BasFileEntity;
import com.one.modules.sys.service.BasFileService;

import java.util.List;
import java.util.Map;

@Service("basFileService")
public class BasFileServiceImpl implements BasFileService {
	@Autowired
	private BasFileDao basFileDao;

	@Override
	public BasFileEntity queryObject(String fileId) {
		return basFileDao.queryObject(fileId);
	}

	@Override
	public List<BasFileEntity> queryList(Map<String, Object> map) {
		return basFileDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return basFileDao.queryTotal(map);
	}

	@Override
	public void save(BasFileEntity basFile) {
		basFileDao.save(basFile);
	}

	@Override
	public void update(BasFileEntity basFile) {
		basFileDao.update(basFile);
	}

	@Override
	public void delete(String fileId) {
		basFileDao.delete(fileId);
	}

	@Override
	public void deleteBatch(String[] fileIds) {
		basFileDao.deleteBatch(fileIds);
	}

}
