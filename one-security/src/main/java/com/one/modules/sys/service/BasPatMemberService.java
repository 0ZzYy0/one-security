package com.one.modules.sys.service;


import java.util.List;
import java.util.Map;

import com.one.modules.sys.entity.BasPatMemberEntity;

/**
 * 家庭成员表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-03 13:28:00
 */
public interface BasPatMemberService {
	
	BasPatMemberEntity queryObject(Long memberId);
	
	List<BasPatMemberEntity> queryList(Map<String, Object> map);
	
	List<BasPatMemberEntity> queryMyMemberList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BasPatMemberEntity basPatMember);
	
	void update(BasPatMemberEntity basPatMember);
	
	void delete(Long memberId);
	
	void deleteBatch(Long[] memberIds);
}
