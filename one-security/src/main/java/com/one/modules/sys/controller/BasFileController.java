package com.one.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.common.utils.ConfigConstant;
import com.one.common.utils.PageUtils;
import com.one.common.utils.Query;
import com.one.common.utils.R;
import com.one.mobile.login.config.WxStorageConfig;
import com.one.modules.sys.entity.BasFileEntity;
import com.one.modules.sys.service.BasFileService;
import com.one.modules.sys.service.SysConfigService;
import com.weixin.pojo.Ticket;
import com.weixin.pojo.Token;
import com.weixin.util.CommonUtil;
import com.weixin.util.SHA1;

/**
 * 文件信息表
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2018-02-09 09:52:17
 */
@Controller
@RequestMapping("basfile")
public class BasFileController {
	@Autowired
	private BasFileService basFileService;
	private final static String KEY = ConfigConstant.WX_CONFIG_KEY;
	public ServletContext application;
	@Autowired
	private SysConfigService sysConfigService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("basfile:list")
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
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
	public R info(@PathVariable("fileId") String fileId) {
		BasFileEntity basFile = basFileService.queryObject(fileId);

		return R.ok().put("basFile", basFile);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("basfile:save")
	public R save(@RequestBody BasFileEntity basFile) {
		basFileService.save(basFile);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("basfile:update")
	public R update(@RequestBody BasFileEntity basFile) {
		basFileService.update(basFile);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("basfile:delete")
	public R delete(@RequestBody String[] fileIds) {
		basFileService.deleteBatch(fileIds);

		return R.ok();
	}

	/**
	 * 加载js获取相关config参数
	 * 
	 * @return
	 */
	@RequestMapping("/getConfig")
	@ResponseBody
	public R getConfig(HttpServletRequest request, HttpServletResponse response) {
		String htmlUrl = (String) request.getParameter("htmlUrl");
		WxStorageConfig config = sysConfigService.getConfigObject(KEY, WxStorageConfig.class);

		Map<String, String> map = new HashMap<String, String>();
		map.put("appid", config.getAppId());
		map.put("appsecret", config.getAppSecrect());

		// 获取接口访问凭证
		Token token = CommonUtil.getToken(map.get("appid"), map.get("appsecret"));
		Ticket jsTicket = CommonUtil.getJSTicket(token);
		
		String timestamp = System.currentTimeMillis() / 1000 + "";
		String nonceStr = CommonUtil.getRandomString(8);
		String wxOri = "jsapi_ticket=" + jsTicket.getTicket() + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + htmlUrl;
		String signature = SHA1.hex_sha1(wxOri);

		R r = new R();
		r.put("appId", map.get("appid"));
		r.put("timestamp", timestamp);
		r.put("nonceStr", nonceStr);
		r.put("signature", signature);
		String context = request.getContextPath();
		String server = request.getServerName();
		int port = request.getServerPort();
		r.put("path", "http://" + server + ":" + port + context);
		return r;
	}
}
