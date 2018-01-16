package com.one.mobile.login.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.common.utils.ConfigConstant;
import com.one.common.utils.R;
import com.one.mobile.login.config.WxStorageConfig;
import com.one.modules.sys.entity.SysUserEntity;
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
    
    private final static String KEY = ConfigConstant.WX_CONFIG_KEY;
    
	/**
	 * 查询是否绑定
	 */
	@RequestMapping(value = "oAuth2")
	public String oAuth2(HttpServletResponse response,HttpServletRequest request)throws IOException {

		System.out.println("--------------------------oAuth2授权------------------------------");
		WxStorageConfig config = sysConfigService.getConfigObject(KEY, WxStorageConfig.class);

		String appid = config.getAppId();
		String appsecret = config.getAppSecrect();
		//String oAuth2Url = config.getoAuth2Url();
		
		if("".equals(appid) || "".equals(appsecret)){
			return R.error(10003, "appid不能为空！").toString();
		}else{
			String code = request.getParameter("code");
			if(code==null || "".equals(code)){
				//return R.error(10004, "用户不同意授权！").toString();
				return "redirect:modules/mobile/home.html";
			}else{
				WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appid, appsecret, code);
				if(weixinOauth2Token==null){
					//return R.error(10005, "获取登录Token失败！").toString();
					return "redirect:modules/mobile/home.html";
				}else{
					String accessToken = weixinOauth2Token.getAccessToken();
					String openId = weixinOauth2Token.getOpenId();
					SNSUserInfo snsUserInfo = AdvancedUtil.getSNSUserInfo(accessToken, openId);

					if(snsUserInfo != null){
						SysUserEntity user = sysUserService.queryByUserName(openId);
						
						if(user != null){
							Subject subject = ShiroUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(openId, "123456");
							subject.login(token);
						}else{
							sysUserService.addUser(snsUserInfo);
							Subject subject = ShiroUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(openId, "123456");
							subject.login(token);
						}
					}else{
						//return R.error(10006, "获取用户信息不成功，登录失败！").toString();
						return "redirect:modules/mobile/home.html";
					}
					
					//通过openid查询医生是否存在，患者直接进入
					
					//存在,跳转链接
					return "redirect:modules/mobile/home.html";
					
					//不存在，跳至绑定页面
					
				}
			}
		}
	}
	
	/**
	 * 绑定
	 */
/*	@ResponseBody
	@RequestMapping(value = "/bindDoc", method = RequestMethod.POST)
	public R saveBindMobile(HttpServletResponse response,HttpServletRequest request)throws IOException {
		String jobNum = request.getParameter("jobNum");
	    
		return R.ok();
	}*/
	
}
