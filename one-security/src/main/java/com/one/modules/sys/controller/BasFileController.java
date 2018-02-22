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
import com.one.modules.sys.entity.BasFileEntity;
import com.one.modules.sys.service.BasFileService;


/**
 * 文件信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
@RestController
@RequestMapping("basfile")
public class BasFileController {
	@Autowired
	private BasFileService basFileService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("basfile:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BasFileEntity> basFileList = basFileService.queryList(query);
		int total = basFileService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(basFileList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{fileId}")
	@RequiresPermissions("basfile:info")
	public R info(@PathVariable("fileId") String fileId){
		BasFileEntity basFile = basFileService.queryObject(fileId);
		
		return R.ok().put("basFile", basFile);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("basfile:save")
	public R save(@RequestBody BasFileEntity basFile){
		basFileService.save(basFile);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("basfile:update")
	public R update(@RequestBody BasFileEntity basFile){
		basFileService.update(basFile);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("basfile:delete")
	public R delete(@RequestBody String[] fileIds){
		basFileService.deleteBatch(fileIds);
		
		return R.ok();
	}
	
}
