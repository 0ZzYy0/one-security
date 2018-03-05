package com.one.weixin.message.resp;

import com.one.weixin.message.resp.BaseMessage;
import com.one.weixin.message.resp.Video;

/**
 * 视频消息
 * 
 * @author zy
 * @date 2015-08-03	
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
