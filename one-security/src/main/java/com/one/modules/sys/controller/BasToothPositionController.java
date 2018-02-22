package com.one.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.modules.sys.entity.BasToothPositionEntity;
import com.one.modules.sys.service.BasToothPositionService;
import com.one.modules.sys.shiro.ShiroUtils;

/**
 * 患者牙位表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
@Controller
@RequestMapping("bastoothposition")
public class BasToothPositionController {
	@Autowired
	private BasToothPositionService basToothPositionService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bastoothposition:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<BasToothPositionEntity> basToothPositionList = basToothPositionService.queryList(query);
		int total = basToothPositionService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(basToothPositionList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{posId}")
	@RequiresPermissions("bastoothposition:info")
	public R info(@PathVariable("posId") Long posId) {
		BasToothPositionEntity basToothPosition = basToothPositionService.queryObject(posId);

		return R.ok().put("basToothPosition", basToothPosition);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bastoothposition:save")
	public R save(@RequestBody BasToothPositionEntity basToothPosition) {
		basToothPositionService.save(basToothPosition);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bastoothposition:update")
	public R update(@RequestBody BasToothPositionEntity basToothPosition) {
		basToothPositionService.update(basToothPosition);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bastoothposition:delete")
	public R delete(@RequestBody Long[] posIds) {
		basToothPositionService.deleteBatch(posIds);

		return R.ok();
	}

	/**
	 * 给出患者表或者患者家属的信息根据 infoId和 tableName
	 */
	@RequestMapping("/getPatient/{parame}")
	@ResponseBody
	public R getPatient(@PathVariable("parame") String parame) {
		String infoId = parame.split("-")[0];
		String tableName = parame.split("-")[1];
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, String> parameMap = new HashMap<>();
		parameMap.put("infoId", infoId);
		parameMap.put("tableName", tableName);
		dataList = basToothPositionService.getPatient(parameMap);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		if (dataList.size() > 0) {
			if(dataList.get(0).get("parentName") != null){
				String parentName = dataList.get(0).get("parentName").toString();
				if(!"".equals(parentName)){
					dataList.get(0).put("deptName", parentName + "-" + dataList.get(0).get("deptName").toString());
				}
			}
			return R.ok().put("entity", dataList.get(0));
		}
		return R.ok();
	}
	
	
	/**
	 * 跳转到wx上传患者照片界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toUploadPhotos")
	public String toUploadPhotos(HttpServletRequest request) {
		ShiroUtils.getSession().setAttribute("infoId", request.getParameter("infoId"));
		ShiroUtils.getSession().setAttribute("tableName", request.getParameter("tableName"));
		return "redirect:/modules/mobile/upload_photos.html";
	}
}
