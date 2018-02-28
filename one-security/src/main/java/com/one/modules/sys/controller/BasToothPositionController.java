package com.one.modules.sys.controller;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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
import com.one.modules.sys.entity.BasToothPositionEntity;
import com.one.modules.sys.service.BasFileService;
import com.one.modules.sys.service.BasToothPositionService;
import com.one.modules.sys.service.SysConfigService;
import com.one.modules.sys.shiro.ShiroUtils;
import com.weixin.pojo.Ticket;
import com.weixin.pojo.Token;
import com.weixin.util.AdvancedUtil;
import com.weixin.util.CommonUtil;

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

	@Autowired
	private BasFileService basFileService;
	

	@Autowired
	private SysConfigService sysConfigService;
	private final static String KEY = ConfigConstant.WX_CONFIG_KEY;
	public ServletContext application;

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
	public R getPatient(@PathVariable("parame") String parame,HttpServletRequest request) throws Exception {
		R r = R.ok();
		if (parame != null) {
			String infoId = parame.split("-")[0];
			String tableName = parame.split("-")[1];
			//取出基本信息
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			Map<String, String> parameMap = new HashMap<>();
			parameMap.put("infoId", infoId);
			parameMap.put("tableName", tableName);
			dataList = basToothPositionService.getPatient(parameMap);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if (dataList.size() > 0) {
				if (dataList.get(0).get("parentName") != null) {
					String parentName = dataList.get(0).get("parentName").toString();
					if (!"".equals(parentName)) {
						dataList.get(0).put("deptName", parentName + "-" + dataList.get(0).get("deptName").toString());
					}
				}
				r.put("entity", dataList.get(0));
			}
			
			//取出图片信息
			Map<String,Object> paraMap = new HashMap<String , Object>();
			paraMap.put("infoId", infoId);
			paraMap.put("tableName", tableName);
			List<BasToothPositionEntity> basToothPositionList = basToothPositionService.queryListByInfoId(paraMap);
			
			if(basToothPositionList.size() >= 1){
				List<BasFileEntity> basFileList = new ArrayList<BasFileEntity>();
				
				
				
				
				
				
				//这里有问题,暂时先这么写回头处理
				for(int i = 0 ; i < basToothPositionList.size() ; i++){
					basFileList.addAll(basFileService.queryListByPosId(basToothPositionList.get(i).getPosId()+""));
				}

				
				
				
				
				
				InetAddress address = InetAddress.getLocalHost();
				String hostAddress = address.getHostAddress();//获取的是本地的IP地址
				if(basFileList.size() > 0 ){
					for(BasFileEntity basFile : basFileList){
						basFile.setFileAddr("http://"+hostAddress+":"+request.getLocalPort()+"/one-security"+basFile.getFileAddr().replace("\\", "/"));
					}
					r.put("basFileList", basFileList);
				}
			}
		}
		return r;
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

	/**
	 * 跳转到wx上传患者照片界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "addBasToothPosition")
	@ResponseBody
	public String addBasToothPosition(HttpServletRequest request) {
		String infoId = request.getParameter("infoId");
		String tableName = request.getParameter("tableName");
		String serverIds = request.getParameter("serverIds");
		String folder = request.getParameter("folder");
		String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
		String realPath = rootPath + folder;
		
		if (serverIds != null && !serverIds.equals("")) {
			WxStorageConfig config = sysConfigService.getConfigObject(KEY, WxStorageConfig.class);
			Map<String, String> map = new HashMap<String, String>();
			map.put("appid", config.getAppId());
			map.put("appsecret", config.getAppSecrect());

			// 获取接口访问凭证
			Token token = CommonUtil.getToken(map.get("appid"), map.get("appsecret"));
			String accessToken = token.getAccessToken();
			if (serverIds != null && !"".equals(serverIds)) {
				for (String serverId : serverIds.split(",")) {
					String fileName = AdvancedUtil.getMedia(accessToken, serverId, realPath);
					BasToothPositionEntity basToothPosition = new BasToothPositionEntity();
					// 根据时间生成随机id
					int random = (int) (Math.random() * 900 + 100);
					Long timeRandom = Long.parseLong((Calendar.getInstance().getTime().getTime()) + "");
					basToothPosition.setPosId(timeRandom + random);
					basToothPosition.setInfoId(Long.parseLong(infoId));
					basToothPosition.setOperateTable(tableName);
					basToothPosition.setPosCode("1");
					basToothPositionService.save(basToothPosition);

					random = (int) (Math.random() * 900 + 100);
					timeRandom = Long.parseLong((Calendar.getInstance().getTime().getTime()) + "");
					BasFileEntity basFile = new BasFileEntity();
					basFile.setFileId((timeRandom + random) + "");
					basFile.setPosId(basToothPosition.getPosId());
					basFile.setFileType("x");
					basFile.setFileAddr(folder + "\\" + fileName);
					basFile.setFileSuffix("");
					basFileService.save(basFile);
				}
				return "ok";
			}
		}
		return "err";
	}

}
