package com.one.modules.sys.controller;


import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.one.common.annotation.SysLog;
import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.common.validator.Assert;
import com.one.common.validator.ValidatorUtils;
import com.one.common.validator.group.AddGroup;
import com.one.common.validator.group.UpdateGroup;
import com.one.modules.sys.entity.BasDoctorEntity;
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.entity.SysDeptEntity;
import com.one.modules.sys.entity.SysUserEntity;
import com.one.modules.sys.service.BasDoctorService;
import com.one.modules.sys.service.BasPatientService;
import com.one.modules.sys.service.SysDeptService;
import com.one.modules.sys.service.SysUserRoleService;
import com.one.modules.sys.service.SysUserService;
import com.one.modules.sys.shiro.ShiroUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private BasDoctorService basDoctorService;
	@Autowired
	private BasPatientService basPatientService;
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	public R info(){
		
		R r = new R();
		Map<String, Object> baseMap = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		//操作表是否为空
		boolean isBlank = getUser().getOperateTable() != null && !getUser().getOperateTable().equals("");
		//是患者
		if(isBlank && getUser().getOperateTable().equals("bas_patient")){
			BasPatientEntity pat = basPatientService.queryObject(getUser().getInfoId());
			SysDeptEntity dept = sysDeptService.queryObject(pat.getDeptId());
			baseMap.put("deptName", dept.getName());
			baseMap.put("name", pat.getPatName());
			baseMap.put("contactWay", pat.getContactWay());
		}
		//是医生
		//
		//
		//
		userMap.put("nickName", getUser().getNickName());
		userMap.put("headImgUrl", getUser().getHeadImgUrl());
		
		r.put("baseInfo", baseMap);
		r.put("user", userMap);
		return r;
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping("/password")
	public R password(String password, String newPassword){
		Assert.isBlank(newPassword, "新密码不为能空");
		String remark = newPassword;
		//原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		//新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());
				
		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword,remark);
		if(count == 0){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);

		Calendar calendar = Calendar.getInstance();
		Long doctorId=calendar.getTime().getTime();

		if(user.getOperateTable() != null && user.getOperateTable().equals("bas_doctor")){
			BasDoctorEntity doctor = new BasDoctorEntity();
			
			user.setInfoId(doctorId);
			doctor.setDocId(doctorId);
			
			basDoctorService.save(doctor);
		}
		
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
