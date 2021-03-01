package com.yc.wowo.wbsocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebServerSocket {
	private static int onlineCount = 0; //在线人数
	
	private static CopyOnWriteArraySet<WebServerSocket> webSocketSet = new CopyOnWriteArraySet<WebServerSocket>(); //存放每个客户对应的WebSocket对象
	
	private Session session;
	
	private String usid;
	
	private String aname;
	/**
	 * 建立连接
	 * 连接会话
	 * 用户id
	 */
	
	@OnOpen
	public void onOpen(Session session, @PathParam("id")String usid, @PathParam("aname")String aname) {
		this.session = session;
		this.usid = usid;
		
		webSocketSet.add(this);
		
		addOnlineCount();
		
		sendMessage("连接服务器成功....");
		
		System.out.println("用户" + aname + " 上线了,当前在线用户人数" + onlineCount);
		
	}

	/**
	 * 给客户端发送信息的方法
	 * @param msg
	 */
	public void sendMessage(String msg) {
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 下线时
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);// 移除当前对象
		subOnlineCount();
		WebServerSocket.onlineCount --;
		System.out.println("用户" + usid + " 下线了,当前在线用户人数" + onlineCount);
	}
	
	
	/**
	 * 接受客户端发过来的信息时
	 */
	@OnMessage
	public void onMessage(String msg, Session session) {
		System.out.println("收到来自用户" + usid + "发过来的信息" + msg);
		
		//转发给所以在线用户
		for(WebServerSocket wss : webSocketSet) {
			wss.sendMessage(msg);
		}
	}
	
	/**
	 * 出错时
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		new RuntimeException(error);
	}
	
	/**
	 * 根据id判断这个用户有没有登录
	 */
	public static WebServerSocket getWebSocket(String usid) {
		if(webSocketSet.isEmpty()) {
			return null;
		}
		
		for(WebServerSocket wss : webSocketSet) {
			if(usid.equals(wss.usid)) {
				return wss;
			}
		}
		
		return null;
	}
	private static synchronized void subOnlineCount() {
		WebServerSocket.onlineCount --;
	}

	private static synchronized void addOnlineCount() {
		WebServerSocket.onlineCount ++;
	}

	public String getUsid() {
		return usid;
	}

	public void setUsid(String usid) {
		this.usid = usid;
	}

	public static int getOnlineCount() {
		return WebServerSocket.onlineCount;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}
}
