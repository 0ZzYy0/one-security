package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.modules.sys.dao.BasPatientDao;
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.service.BasPatientService;

import java.util.List;
import java.util.Map;



@Service("basPatientService")
public class BasPatientServiceImpl implements BasPatientService {
	@Autowired
	private BasPatientDao basPatientDao;
	
	@Override
	public BasPatientEntity queryObject(Long patId){
		return basPatientDao.queryObject(patId);
	}
	
	@Override
	public List<BasPatientEntity> queryList(Map<String, Object> map){
		return basPatientDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basPatientDao.queryTotal(map);
	}
	
	@Override
	public void save(BasPatientEntity basPatient){
		basPatientDao.save(basPatient);
	}
	
	@Override
	public void update(BasPatientEntity basPatient){
		basPatientDao.update(basPatient);
	}
	
	@Override
	public void delete(Long patId){
		basPatientDao.delete(patId);
	}
	
	@Override
	public void deleteBatch(Long[] patIds){
		basPatientDao.deleteBatch(patIds);
	}
	
}
