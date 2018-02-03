package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.one.modules.sys.dao.BasDoctorDao;
import com.one.modules.sys.entity.BasDoctorEntity;
import com.one.modules.sys.service.BasDoctorService;



@Service("basDoctorService")
public class BasDoctorServiceImpl implements BasDoctorService {
	@Autowired
	private BasDoctorDao basDoctorDao;
	
	@Override
	public BasDoctorEntity queryObject(Long docId){
		return basDoctorDao.queryObject(docId);
	}
	
	@Override
	public List<BasDoctorEntity> queryList(Map<String, Object> map){
		return basDoctorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basDoctorDao.queryTotal(map);
	}
	
	@Override
	public void save(BasDoctorEntity basDoctor){
		basDoctorDao.save(basDoctor);
	}
	
	@Override
	public void update(BasDoctorEntity basDoctor){
		basDoctorDao.update(basDoctor);
	}
	
	@Override
	public void delete(Long docId){
		basDoctorDao.delete(docId);
	}
	
	@Override
	public void deleteBatch(Long[] docIds){
		basDoctorDao.deleteBatch(docIds);
	}
	
}
