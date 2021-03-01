package com.yc.wowo.controller;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.AdminInfo;
import com.yc.wowo.biz.AdminInfoBiz;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.SendEMailUtil;
import com.yc.wowo.util.SessionKeyConstant;
import com.yc.wowo.wbsocket.WebServerSocket;

@RestController
@RequestMapping("/admin")
public class AdminInfoController {
	@Autowired
	private AdminInfoBiz admin;
	
	@Autowired
	private SendEMailUtil sendEmailUtil;
	
	@PostMapping("/login")
	public ResultDTO login(AdminInfo af, HttpSession session) {
		AdminInfo ad = admin.login(af);	
		if(ad == null) {
			return new ResultDTO(500, "失败");
		}
		
		session.setAttribute(SessionKeyConstant.ADMININFOLOGIN, af);
		
		//如果登录成功，则判断当前用户没有在其他地方登录
		WebServerSocket wss = WebServerSocket.getWebSocket(String.valueOf(af.getAid()));
		if(wss != null) {
			wss.sendMessage("101");
		}
		return new ResultDTO(200, "成功");
	}
	
	@RequestMapping("/add")
	public  ResultDTO reg(AdminInfo mf, HttpSession session) {
		String scode = String.valueOf(session.getAttribute(SessionKeyConstant.EMAILCODE));
		System.out.println(scode);
		System.out.println(mf);
		
		if( admin.add(mf) > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");
	}
	
	@RequestMapping("/send")
	public ResultDTO sendCode(@RequestParam("email") String receiveMail, String nickName, HttpSession session) {
		System.out.println(receiveMail);
		System.out.println(nickName);
		String code = "";
		Random rd = new Random();
		while(code.length() < 6) {
			code += rd.nextInt(10);
		}
		System.out.println(code);
		if(sendEmailUtil.sendEmail(receiveMail, code, nickName)) {
			session.setAttribute(SessionKeyConstant.EMAILCODE, code);
		
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					session.removeAttribute(SessionKeyConstant.EMAILCODE);
				}
			};
			
			Timer timer = new Timer();
			timer.schedule(task, 60000);//三分钟执行一次
			return new ResultDTO(200, "成功");
		}
		
		return new ResultDTO(500, "失败");

		
	}
	
	@GetMapping("/check")
	public ResultDTO checkLogin(HttpSession session) {
		Object obj = session.getAttribute(SessionKeyConstant.ADMININFOLOGIN);
		
		if(obj == null) {
			return new ResultDTO(500, "未登录");
		}
		
		return new ResultDTO(200, "已登录", obj);
	}
	
}
