package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.modules.sys.dao.BasToothDiseaseDao;
import com.one.modules.sys.entity.BasToothDiseaseEntity;
import com.one.modules.sys.service.BasToothDiseaseService;

import java.util.List;
import java.util.Map;

@Service("basToothDiseaseService")
public class BasToothDiseaseServiceImpl implements BasToothDiseaseService {
	@Autowired
	private BasToothDiseaseDao basToothDiseaseDao;

	@Override
	public BasToothDiseaseEntity queryObject(Long diseaseId) {
		return basToothDiseaseDao.queryObject(diseaseId);
	}

	@Override
	public List<BasToothDiseaseEntity> queryList(Map<String, Object> map) {
		return basToothDiseaseDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return basToothDiseaseDao.queryTotal(map);
	}

	@Override
	public void save(BasToothDiseaseEntity basToothDisease) {
		basToothDiseaseDao.save(basToothDisease);
	}

	@Override
	public void update(BasToothDiseaseEntity basToothDisease) {
		basToothDiseaseDao.update(basToothDisease);
	}

	@Override
	public void delete(Long diseaseId) {
		basToothDiseaseDao.delete(diseaseId);
	}

	@Override
	public void deleteBatch(Long[] diseaseIds) {
		basToothDiseaseDao.deleteBatch(diseaseIds);
	}

}
