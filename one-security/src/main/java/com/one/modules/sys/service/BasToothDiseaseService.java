package com.one.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.one.modules.sys.entity.BasToothDiseaseEntity;

/**
 * 牙位病灶表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public interface BasToothDiseaseService {
	
	BasToothDiseaseEntity queryObject(Long diseaseId);
	
	List<BasToothDiseaseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasToothDiseaseEntity basToothDisease);
	
	void update(BasToothDiseaseEntity basToothDisease);
	
	void delete(Long diseaseId);
	
	void deleteBatch(Long[] diseaseIds);
}
