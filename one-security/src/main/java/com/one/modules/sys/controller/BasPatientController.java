package com.one.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.entity.SysDeptEntity;
import com.one.modules.sys.entity.SysUserEntity;
import com.one.modules.sys.service.BasPatMemberService;
import com.one.modules.sys.service.BasPatientService;
import com.one.modules.sys.service.SysConfigService;
import com.one.modules.sys.service.SysDeptService;
import com.one.modules.sys.service.SysUserService;
import com.one.modules.sys.shiro.ShiroUtils;

/**
 * 患者信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-01 19:04:33
 */
@Controller
@RequestMapping("baspatient")
public class BasPatientController {
	@Autowired
	private BasPatientService basPatientService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private BasPatMemberService basPatMemberService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
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
	public R info(@PathVariable("patId") Long patId) {
		BasPatientEntity basPatient = basPatientService.queryObject(patId);
		return R.ok().put("basPatient", basPatient);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BasPatientEntity basPatient) {
		basPatientService.save(basPatient);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody BasPatientEntity basPatient) {
		basPatientService.update(basPatient);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] patIds) {
		basPatientService.deleteBatch(patIds);
		return R.ok();
	}

	/**
	 * 查询自己的患者信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public R queryInfoByPatientID() {
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());
		BasPatientEntity basPatient = basPatientService.queryObject(user.getInfoId());
		return R.ok().put("basPatient", basPatient);
	}

	/**
	 * 机构下拉列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "getDeptSelect")
	@ResponseBody
	public List<SysDeptEntity> getDeptSelect() {
		List<SysDeptEntity> deptList = sysDeptService.queryAllList(new HashMap<String, Object>());
		return deptList;
	}

	/**
	 * 跳转到wx患者列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "toDeptPatientInfo")
	public String toMyMemberInfo(HttpServletRequest request) {
		ShiroUtils.getSession().setAttribute("deptId", request.getParameter("deptId"));
		return "redirect:/modules/mobile/basPatient_list.html";
	}

	/**
	 * 跳转到wx端机构列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toDeptList/{uploadPermissions}")
	public String toDeptList(@PathVariable("uploadPermissions") String uploadPermissions) {
		ShiroUtils.getSession().setAttribute("uploadPermissions", uploadPermissions);
		//跳转到wx端机构列表页面
		return "redirect:/modules/mobile/dept_list.html";
	}

	/**
	 * 拉取某一个机构下的所有患者
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBasPatientList/{deptId}")
	@ResponseBody
	public List<Map<String, Object>> getBasPatientList(@PathVariable("deptId") String deptId) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> basPatientList = basPatientService.queryListByDeptId(deptId);
		List<Map<String, Object>> basPatMembertList = basPatMemberService.queryListByDeptId(deptId);
		
		for(Map basPatientMap : basPatientList){
			dataList.add(basPatientMap);
			for(Map basPatMembertMap : basPatMembertList){
				if(basPatientMap.get("patId").toString().equals(basPatMembertMap.get("patId").toString())){
					basPatMembertMap.put("pName", basPatientMap.get("name"));
					dataList.add(basPatMembertMap);
				}
			}
		}
		basPatMembertList.removeAll(dataList);
		dataList.addAll(basPatMembertList);
		return dataList;
	}

	// 测试
	@RequestMapping(value = "toMyInfo")
	public String toMyInfo() {
		// 跳转至患者个人信息
		return "redirect:/modules/mobile/my_info.html";
	}
	
	@RequestMapping(value = "toHome")
	public String toHome() {
		// 跳转至主页
		System.out.println("******************");
		System.out.println("******************");
		System.out.println("******************");
		System.out.println("******************");
		System.out.println("******************");
		return "redirect:/modules/mobile/home.html";
	}
}
