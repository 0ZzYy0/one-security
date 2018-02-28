package com.one.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.one.modules.sys.entity.BasFileEntity;

/**
 * 文件信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
public interface BasFileService {
	
	BasFileEntity queryObject(String fileId);
	
	List<BasFileEntity> queryList(Map<String, Object> map);
	
	List<BasFileEntity> queryListByPosId(String posId);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasFileEntity basFile);
	
	void update(BasFileEntity basFile);
	
	void delete(String fileId);
	
	void deleteBatch(String[] fileIds);
}
