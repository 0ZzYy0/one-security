package com.one.modules.sys.service;

import com.one.modules.sys.entity.BasDoctorEntity;

import java.util.List;
import java.util.Map;

/**
 * 医生信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-02 19:50:29
 */
public interface BasDoctorService {
	
	BasDoctorEntity queryObject(Long docId);
	
	List<BasDoctorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasDoctorEntity basDoctor);
	
	void update(BasDoctorEntity basDoctor);
	
	void delete(Long docId);
	
	void deleteBatch(Long[] docIds);
}
