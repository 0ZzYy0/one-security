package com.one.weixin.message.event;

import com.one.weixin.message.event.BaseEvent;

/**
 * 扫描带参数二维码事件
 * 
 * @author zy
 * @date 2015-08-03
 */
public class QRCodeEvent extends BaseEvent {
	// 事件KEY值
	private String EventKey;
	// 用于换取二维码图片
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
