package com.one.modules.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.modules.sys.dao.BasPatMemberDao;
import com.one.modules.sys.entity.BasPatMemberEntity;
import com.one.modules.sys.service.BasPatMemberService;


import java.util.List;
import java.util.Map;

@Service("basPatMemberService")
public class BasPatMemberServiceImpl implements BasPatMemberService {
	@Autowired
	private BasPatMemberDao basPatMemberDao;
	
	@Override
	public BasPatMemberEntity queryObject(Long memberId){
		return basPatMemberDao.queryObject(memberId);
	}
	
	@Override
	public List<BasPatMemberEntity> queryList(Map<String, Object> map){
		return basPatMemberDao.queryList(map);
	}
	
	@Override
	public List<BasPatMemberEntity> queryMyMemberList(Map<String, Object> map){
		return basPatMemberDao.queryMyMemberList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return basPatMemberDao.queryTotal(map);
	}
	
	@Override
	public void save(BasPatMemberEntity basPatMember){
		basPatMemberDao.save(basPatMember);
	}
	
	@Override
	public void update(BasPatMemberEntity basPatMember){
		basPatMemberDao.update(basPatMember);
	}
	
	@Override
	public void delete(Long memberId){
		basPatMemberDao.delete(memberId);
	}
	
	@Override
	public void deleteBatch(Long[] memberIds){
		basPatMemberDao.deleteBatch(memberIds);
	}
	
}
