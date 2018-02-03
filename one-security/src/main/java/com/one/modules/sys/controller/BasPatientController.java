package com.one.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.service.BasPatientService;



/**
 * 患者信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-01 19:04:33
 */
@RestController
@RequestMapping("baspatient")
public class BasPatientController {
	@Autowired
	private BasPatientService basPatientService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("baspatient:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BasPatientEntity> basPatientList = basPatientService.queryList(query);
		int total = basPatientService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(basPatientList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{patId}")
	@RequiresPermissions("baspatient:info")
	public R info(@PathVariable("patId") Long patId){
		BasPatientEntity basPatient = basPatientService.queryObject(patId);
		
		return R.ok().put("basPatient", basPatient);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("baspatient:save")
	public R save(@RequestBody BasPatientEntity basPatient){
		basPatientService.save(basPatient);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("baspatient:update")
	public R update(@RequestBody BasPatientEntity basPatient){
		basPatientService.update(basPatient);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("baspatient:delete")
	public R delete(@RequestBody Long[] patIds){
		basPatientService.deleteBatch(patIds);
		
		return R.ok();
	}
	
	
	//测试
	@RequestMapping("/text")
	@ResponseBody
	public String text() {
		System.out.println("*********************");
		System.out.println("*********************");
		System.out.println("*********************");
		return "123132";
	}
	
	
}
