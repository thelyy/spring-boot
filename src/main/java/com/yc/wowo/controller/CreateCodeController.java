package com.yc.wowo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.wowo.util.SessionKeyConstant;

@Controller
@RequestMapping
public class CreateCodeController {

	
	@RequestMapping("/code")
	public void createCode(HttpServletRequest request, HttpServletResponse response) {
		//处理缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	
		//生成验证码
		String code = getRandomCode();
		
		//将验证码存到session中
		request.getSession().setAttribute(SessionKeyConstant.VERIFICATIONCODE, code);
		
		//创建验证码图片并返回
	}

	private String getRandomCode() {
		return null;
	}
	
}
