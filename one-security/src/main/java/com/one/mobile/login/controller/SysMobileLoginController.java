package com.one.mobile.login.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.common.utils.ConfigConstant;
import com.one.common.utils.R;
import com.one.mobile.login.config.WxStorageConfig;
import com.one.modules.sys.entity.BasPatientEntity;
import com.one.modules.sys.entity.SysUserEntity;
import com.one.modules.sys.service.BasPatientService;
import com.one.modules.sys.service.SysConfigService;
import com.one.modules.sys.service.SysUserService;
import com.one.modules.sys.shiro.ShiroUtils;
import com.weixin.pojo.SNSUserInfo;
import com.weixin.pojo.WeixinOauth2Token;
import com.weixin.util.AdvancedUtil;

/**
 * 微信登录
 * 
 * @author zy
 * @email 553224182@qq.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
public class SysMobileLoginController {
	
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private BasPatientService basPatientService;
    
    private final static String KEY = ConfigConstant.WX_CONFIG_KEY;
    
	/**
	 * 用户授权登录业务处理
	 */
	@RequestMapping(value = "oAuth2")
	public String oAuth2(HttpServletResponse response,HttpServletRequest request)throws IOException {

		System.out.println("--------------------------oAuth2授权------------------------------");
		WxStorageConfig config = sysConfigService.getConfigObject(KEY, WxStorageConfig.class);

		String appid = config.getAppId();
		String appsecret = config.getAppSecrect();
		//String oAuth2Url = config.getoAuth2Url();
		
		if(!"".equals(appid) && !"".equals(appsecret)){
			String code = request.getParameter("code");
			if (code != null && !"".equals(code)){
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appid, appsecret, code);
				if(weixinOauth2Token!=null){
					String accessToken = weixinOauth2Token.getAccessToken();
					String openId = weixinOauth2Token.getOpenId();
					SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

					if(snsUserInfo != null){
						SysUserEntity user = sysUserService.queryByUserName(openId);
						
						if(user != null){
							//用户存在
							Subject subject = ShiroUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(openId, "123456");
							subject.login(token);
						}else{
							//用户不存在
							
							//新增用户的同时也新增一条患者信息
							BasPatientEntity basPatientEntity = new BasPatientEntity();
							Calendar calendar = Calendar.getInstance();
							basPatientEntity.setPatId(Long.parseLong(calendar.getTime().getTime() + ""));
							basPatientService.save(basPatientEntity);
							//新增用户
							sysUserService.addUser(snsUserInfo,calendar.getTime().getTime(),"bas_patient");
							Subject subject = ShiroUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(openId, "123456");
							subject.login(token);
						}
					}
				}
			}
		}
		//跳转至患者首页
		return "redirect:modules/mobile/home.html";
		
	}
	
	/**
	 * 医生授权登录
	 */
	@RequestMapping(value = "oAuth2Doctor")
	public String oAuth2Doctor(HttpServletResponse response,HttpServletRequest request)throws IOException {

		System.out.println("--------------------------oAuth2Doctor授权------------------------------");
		WxStorageConfig config = sysConfigService.getConfigObject(KEY, WxStorageConfig.class);

		String appid = config.getAppId();
		String appsecret = config.getAppSecrect();
		//String oAuth2Url = config.getoAuth2Url();
		String returnStr = "";
		if(!"".equals(appid) && !"".equals(appsecret)){
			String code = request.getParameter("code");
			if (code != null && !"".equals(code)){
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appid, appsecret, code);
				if(weixinOauth2Token!=null){
					String accessToken = weixinOauth2Token.getAccessToken();
					String openId = weixinOauth2Token.getOpenId();
					SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);
					ShiroUtils.getSession().setAttribute("snsUserInfo", snsUserInfo);
					
					if(snsUserInfo != null){
						SysUserEntity user = sysUserService.queryByUserOpenId(openId,"bas_doctor");
						
						if(user != null){
							//用户存在
							Subject subject = ShiroUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getRemark());
							subject.login(token);

							returnStr = "redirect:modules/mobile/home_doctor.html";
						}else{
							returnStr = "redirect:modules/mobile/bind_doctor.html";
						}
					}
				}
			}
		}
		return returnStr;
		
	}
	
	/**
	 * 绑定
	 */
	@ResponseBody
	@RequestMapping(value = "/bindDoc")
	public R bindDoc(HttpServletResponse response,HttpServletRequest request,String username, String password)throws IOException {
		
		try{
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return R.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return R.error("账号或密码不正确");
		}catch (LockedAccountException e) {
			return R.error("账号已被锁定,请联系管理员");
		}catch (AuthenticationException e) {
			return R.error("账户验证失败");
		}
		SNSUserInfo snsUserInfo = (SNSUserInfo) ShiroUtils.getSession().getAttribute("snsUserInfo");
		
		ShiroUtils.getUserEntity().setOpenId(snsUserInfo.getOpenId());
		ShiroUtils.getUserEntity().setNickName(snsUserInfo.getNickname());
		ShiroUtils.getUserEntity().setHeadImgUrl(snsUserInfo.getHeadImgUrl());
		
		SysUserEntity user = ShiroUtils.getUserEntity();
		
		sysUserService.updateWxInfo(user);
		return R.ok();
	}
}
