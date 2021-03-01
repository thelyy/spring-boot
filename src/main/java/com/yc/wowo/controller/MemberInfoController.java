package com.yc.wowo.controller;



import java.util.HashMap;

import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.biz.MemberInfoBiz;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;
import com.yc.wowo.util.SendEMailUtil;
import com.yc.wowo.util.SessionKeyConstant;

@RestController
@RequestMapping("/member")
public class MemberInfoController{
	@Autowired
	private MemberInfoBiz member;
	
	@Autowired
	private SendEMailUtil sendEmailUtil;
	@RequestMapping("/findByFirst")
	public  Map<String, Object> findByFirst(@RequestParam Map<String, Object> map){
		
		Map<String, Object> result = new HashMap<String, Object>();
		//要返回第一页的数据以及总记录数
		result.put("total", member.total());
		result.put("rows", member.findByPage(RequestParamUtil.findByPagetUtil(map)));
		return result;
	}
	
	@RequestMapping("/finds")
	public ResultDTO finds(HttpSession session, HttpServletRequest request) {
		Object obj = request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		MemberInfo mf1 = (MemberInfo) obj;
		MemberInfo mf2 = member.finds(mf1.getNickName());		
		System.out.println("名字是" +  mf1.getNickName());
		System.out.println("我的mf为" +  mf2);
		if(mf2 != null) {
			session.setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, mf2);
			return new ResultDTO(200, mf2);
		}
		return new ResultDTO(500, "失败");
	}

	@RequestMapping("/login")
	public ResultDTO login(@RequestParam Map<String, Object> map, HttpSession session) {
		MemberInfo mf = member.login(map);		
		System.out.println(mf);
		if(mf != null) {
			session.setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, mf);
			return new ResultDTO(200, mf);
		}
		return new ResultDTO(500, "失败");
	}
	
	@RequestMapping("/register")
	public  ResultDTO reg(MemberInfo mf, HttpSession session, String codes) {
		String scode = String.valueOf(session.getAttribute(SessionKeyConstant.EMAILCODE));
		System.out.println(scode);
		System.out.println(mf);
		if(!scode.equalsIgnoreCase(codes)) {
			return new ResultDTO(300, "验证码错误");
		}
		
		if( member.add(mf) > 0) {
			return new ResultDTO(200, "成功");
		}
		return new ResultDTO(500, "失败");
	}
	
	
	@RequestMapping("/log")
	public  ResultDTO log(HttpSession session) {
		session.invalidate();
		return new ResultDTO(200, "成功");
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
}
