package com.one.weixin.message.req;

import com.one.weixin.message.req.BaseMessage;

/**
 * 图片消息
 * 
 * @author zy
 * @date 2015-08-03
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
