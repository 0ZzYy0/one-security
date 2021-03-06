package com.one.weixin.message.event;

import com.one.weixin.message.event.BaseEvent;

/**
 * 自定义菜单事件
 * 
 * @author zy
 * @date 2015-08-03
 */
public class MenuEvent extends BaseEvent {
	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
