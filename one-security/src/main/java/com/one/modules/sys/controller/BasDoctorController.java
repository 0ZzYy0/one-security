package com.one.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.modules.sys.entity.BasDoctorEntity;
import com.one.modules.sys.entity.SysUserEntity;
import com.one.modules.sys.service.BasDoctorService;
import com.one.modules.sys.service.SysUserService;
import com.one.modules.sys.shiro.ShiroUtils;

/**
 * 医生信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-02 19:50:29
 */
@Controller
@RequestMapping("basdoctor")
public class BasDoctorController {
	@Autowired
	private BasDoctorService basDoctorService;
	@Autowired
	private SysUserService sysUserService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("basdoctor:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<BasDoctorEntity> basDoctorList = basDoctorService.queryList(query);
		int total = basDoctorService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(basDoctorList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{docId}")
	@RequiresPermissions("basdoctor:info")
	public R info(@PathVariable("docId") Long docId) {
		BasDoctorEntity basDoctor = basDoctorService.queryObject(docId);

		return R.ok().put("basDoctor", basDoctor);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("basdoctor:save")
	public R save(@RequestBody BasDoctorEntity basDoctor) {
		basDoctorService.save(basDoctor);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody BasDoctorEntity basDoctor) {
		basDoctorService.update(basDoctor);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("basdoctor:delete")
	public R delete(@RequestBody Long[] docIds) {
		basDoctorService.deleteBatch(docIds);

		return R.ok();
	}

	/**
	 * 拉取当前登陆医生信息
	 */
	@RequestMapping("/getInfo")
	@ResponseBody
	public R getInfo() {
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());
		BasDoctorEntity basDoctor = basDoctorService.queryObject(user.getInfoId());
		return R.ok().put("basDoctor", basDoctor);
	}

	/**
	 * 跳转到医生个人信息页面
	 */
	@RequestMapping("/toBasDoctorInfo")
	public String toBasDoctorInfo() {
		return "redirect:/modules/mobile/bas_doctor_info.html";
	}

}
