package com.one.modules.sys.controller;

import java.util.Calendar;
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
import com.one.modules.sys.entity.BasPatMemberEntity;
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.entity.SysDeptEntity;
import com.one.modules.sys.entity.SysUserEntity;
import com.one.modules.sys.service.BasPatMemberService;
import com.one.modules.sys.service.SysDeptService;
import com.one.modules.sys.service.SysUserService;
import com.one.modules.sys.shiro.ShiroUtils;

/**
 * 家庭成员表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-03 13:28:00
 */
@Controller
@RequestMapping("baspatmember")
public class BasPatMemberController {
	@Autowired
	private BasPatMemberService basPatMemberService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDeptService sysDeptService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<BasPatMemberEntity> basPatMemberList = basPatMemberService
				.queryList(query);
		int total = basPatMemberService.queryTotal(query);

		PageUtils pageUtil = new PageUtils(basPatMemberList, total,
				query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{memberId}")
	public R info(@PathVariable("memberId") Long memberId) {
		BasPatMemberEntity basPatMember = basPatMemberService.queryObject(memberId);
		return R.ok().put("basPatMember", basPatMember);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public R save(@RequestBody BasPatMemberEntity basPatMember) {
		System.out.println("确实是在新增!!!!");
		Calendar calendar = Calendar.getInstance();
		basPatMember.setMemberId(Long.parseLong(calendar.getTime().getTime() + ""));
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());
		basPatMember.setPatId(user.getInfoId());
		basPatMemberService.save(basPatMember);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody BasPatMemberEntity basPatMember) {
		basPatMemberService.update(basPatMember);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] memberIds) {
		basPatMemberService.deleteBatch(memberIds);

		return R.ok();
	}

	@RequestMapping(value = "toMyMemberList")
	public String toMyInfo() {
		// 跳转至家庭成员列表页面
		return "redirect:/modules/mobile/my_membe_list.html";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "getMyMember")
	@ResponseBody
	public List<BasPatMemberEntity> getMyMember() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 查询列表数据
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());
		paraMap.put("patId", user.getInfoId());
		List<BasPatMemberEntity> dataList = basPatMemberService.queryMyMemberList(paraMap);
		return dataList;
	}
	
	/**
	 * 跳转到家庭成员详细信息页
	 * @return
	 */
	@RequestMapping(value = "toMyMemberInfo")
	public String toMyMemberInfo(HttpServletRequest request){
		ShiroUtils.getSession().setAttribute("memberId", request.getParameter("memberId"));
		return "redirect:/modules/mobile/my_member_info.html";
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/getinfo/{memberId}")
	@ResponseBody
	public R getinfo(@PathVariable("memberId") Long memberId) {
		BasPatMemberEntity basPatMember = basPatMemberService.queryObject(memberId);
		System.out.println(basPatMember);
		return R.ok().put("basPatMember", basPatMember);
	}
	
	/**
	 * 机构下拉列表
	 * @return
	 */
	@RequestMapping(value = "getDeptSelect")
	@ResponseBody
	public List<SysDeptEntity> getDeptSelect(){
		Map<String,Object> pareMap = new HashMap<>();
		List<SysDeptEntity> deptList = sysDeptService.queryAllList(new HashMap<String, Object>());
		return deptList;
	}
}
