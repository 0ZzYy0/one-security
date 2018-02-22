package com.one.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.one.modules.sys.entity.BasPatientEntity;

/**
 * 患者信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-01 19:04:33
 */
@Mapper
public interface BasPatientDao extends BaseDao<BasPatientEntity> {
	
	List<Map<String, Object>> queryListByDeptId(String deptId);
	
}
