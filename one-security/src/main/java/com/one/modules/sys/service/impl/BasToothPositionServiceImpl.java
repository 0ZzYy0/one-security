package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.modules.sys.dao.BasToothPositionDao;
import com.one.modules.sys.entity.BasToothPositionEntity;
import com.one.modules.sys.service.BasToothPositionService;

import java.util.List;
import java.util.Map;

@Service("basToothPositionService")
public class BasToothPositionServiceImpl implements BasToothPositionService {
	@Autowired
	private BasToothPositionDao basToothPositionDao;

	@Override
	public BasToothPositionEntity queryObject(Long posId) {
		return basToothPositionDao.queryObject(posId);
	}

	@Override
	public List<BasToothPositionEntity> queryList(Map<String, Object> map) {
		return basToothPositionDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return basToothPositionDao.queryTotal(map);
	}

	@Override
	public void save(BasToothPositionEntity basToothPosition) {
		basToothPositionDao.save(basToothPosition);
	}

	@Override
	public void update(BasToothPositionEntity basToothPosition) {
		basToothPositionDao.update(basToothPosition);
	}

	@Override
	public void delete(Long posId) {
		basToothPositionDao.delete(posId);
	}

	@Override
	public void deleteBatch(Long[] posIds) {
		basToothPositionDao.deleteBatch(posIds);
	}

	@Override
	public List<Map<String, Object>> getPatient(Map<String, String> map) {
		return basToothPositionDao.getPatient(map);
	}

}
