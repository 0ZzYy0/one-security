package com.one.weixin.message.resp;

import com.one.weixin.message.resp.BaseMessage;
import com.one.weixin.message.resp.Voice;

/**
 * 语音消息
 * 
 * @author zy
 * @date 2015-08-03
 */
public class VoiceMessage extends BaseMessage {
	// 语音
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
