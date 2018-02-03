package com.one.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.one.modules.sys.entity.BasPatientEntity;

/**
 * 患者信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-01 19:04:33
 */
public interface BasPatientService {
	
	BasPatientEntity queryObject(Long patId);
	
	List<BasPatientEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasPatientEntity basPatient);
	
	void update(BasPatientEntity basPatient);
	
	void delete(Long patId);
	
	void deleteBatch(Long[] patIds);
}
