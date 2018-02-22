package com.one.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.one.modules.sys.entity.BasToothPositionEntity;

/**
 * 患者牙位表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
@Mapper
public interface BasToothPositionDao extends BaseDao<BasToothPositionEntity> {
	List<Map<String, Object>> getPatient(Map<String, String> map);
}
