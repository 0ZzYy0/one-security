package com.one.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.one.modules.sys.entity.BasPatMemberEntity;

/**
 * 家庭成员表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-03 13:28:00
 */
@Mapper
public interface BasPatMemberDao extends BaseDao<BasPatMemberEntity> {
	List<BasPatMemberEntity> queryMyMemberList(Map<String,Object> map);
	
	List<Map<String,Object>> queryListByDeptId(String deptId);
	
	List<Map<String,Object>> queryCensusResultsList(Map<String ,Object> map);
}
