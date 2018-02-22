package com.one.modules.sys.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.modules.sys.entity.BasToothDiseaseEntity;
import com.one.modules.sys.service.BasToothDiseaseService;


/**
 * 牙位病灶表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
@RestController
@RequestMapping("bastoothdisease")
public class BasToothDiseaseController {
	@Autowired
	private BasToothDiseaseService basToothDiseaseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bastoothdisease:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BasToothDiseaseEntity> basToothDiseaseList = basToothDiseaseService.queryList(query);
		int total = basToothDiseaseService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(basToothDiseaseList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{diseaseId}")
	@RequiresPermissions("bastoothdisease:info")
	public R info(@PathVariable("diseaseId") Long diseaseId){
		BasToothDiseaseEntity basToothDisease = basToothDiseaseService.queryObject(diseaseId);
		
		return R.ok().put("basToothDisease", basToothDisease);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bastoothdisease:save")
	public R save(@RequestBody BasToothDiseaseEntity basToothDisease){
		basToothDiseaseService.save(basToothDisease);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bastoothdisease:update")
	public R update(@RequestBody BasToothDiseaseEntity basToothDisease){
		basToothDiseaseService.update(basToothDisease);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bastoothdisease:delete")
	public R delete(@RequestBody Long[] diseaseIds){
		basToothDiseaseService.deleteBatch(diseaseIds);
		
		return R.ok();
	}
	
}
