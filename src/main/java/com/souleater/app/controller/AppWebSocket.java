package com.souleater.app.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocket服务端
 * @author love720720@163.com
 * @creation 2017年9月26日 下午3:01:04
 */
@ServerEndpoint("/aws/ws")
public class AppWebSocket {

	// 客户端session
	private static Map<String, Session> sessionMap;
	
	// 当前客户端session
	private Session session;
	
	static{
		sessionMap = new HashMap<String, Session>();
	}
	
	/**
	 * 打开链接
	 * 获取客户端session存入map
	 * @param session
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		String id = this.session.getId();
		sessionMap.put(id, this.session);
	}
	/**
	 * 链接关闭
	 * 获取当前客户端session移除map
	 */
	@OnClose
	public void onClose() {
		String id = this.session.getId();
		sessionMap.remove(id);
	}
	
	/**
	 * 链接错误
	 * @param session
	 * @param e
	 */
	@OnError
	public void onError(Session session, Throwable e) {
	}
	
	/**
	 * 消息推送
	 * @param json
	 * @param session
	 */
	@OnMessage
	public void onMessage(String json, Session session) {
		try {
			Iterator<Entry<String, Session>> iterator = sessionMap.entrySet().iterator();
			while (iterator.hasNext()) {
				// 循环客户端session
				Entry<String, Session> next = iterator.next();
				Session mapSession = next.getValue();
				// 异步发送消息
				mapSession.getAsyncRemote().sendText(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
