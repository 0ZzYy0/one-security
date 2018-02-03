package com.one.modules.sys.dao;

import com.one.modules.sys.entity.BasDoctorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医生信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-02 19:50:29
 */
@Mapper
public interface BasDoctorDao extends BaseDao<BasDoctorEntity> {
	
}
