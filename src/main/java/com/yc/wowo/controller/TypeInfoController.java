package com.yc.wowo.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.biz.TypeInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.dto.ResultDTO;
import com.yc.wowo.util.RequestParamUtil;

@RestController
@RequestMapping("/type")
public class TypeInfoController{
	@Autowired
	private TypeInfoBiz type;
	
	@RequestMapping("/finds")
	public List<TypeInfo> finds() {
		return type.finds();
	}

	@RequestMapping("/findByPage")
	public JsonObject findByPage(@RequestParam Map<String, Object> map) {
		return type.findByPage(RequestParamUtil.findByPagetUtil(map));
	}
	
	@RequestMapping("/update")
	public ResultDTO update(TypeInfo tf) {
		int result = type.update(tf);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}else {
			return new ResultDTO(500, "失败");
		}
		
	}
	
	@RequestMapping("/add")
	public ResultDTO add(TypeInfo tf) {
		int result = type.add(tf);
		if(result > 0) {
			return new ResultDTO(200, "成功");
		}else {
			return new ResultDTO(500, "失败");
		}
	}
	
	@RequestMapping("/findAll")
	public List<TypeInfo> findAll() {
		return type.findAll();
	}	
}
