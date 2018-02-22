package com.one.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.one.modules.sys.entity.BasToothPositionEntity;

/**
 * 患者牙位表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public interface BasToothPositionService {
	
	BasToothPositionEntity queryObject(Long posId);
	
	List<BasToothPositionEntity> queryList(Map<String, Object> map);
	
	List<Map<String,Object>> getPatient(Map<String, String> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasToothPositionEntity basToothPosition);
	
	void update(BasToothPositionEntity basToothPosition);
	
	void delete(Long posId);
	
	void deleteBatch(Long[] posIds);
}
