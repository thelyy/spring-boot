package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.biz.TypeInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.ITypeInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class TypeInfoBizImpl implements TypeInfoBiz{
	@Autowired
	private ITypeInfoMapper typeInfoMapper;
	
	
	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(typeInfoMapper.total(), typeInfoMapper.findByPage(map));
	}
	
	
	@Override
	public int add(TypeInfo ty) {
		if(StringUtil.checkNull(ty.getTname())) {
			return -1;
		}
		return typeInfoMapper.add(ty);
	}

	@Override
	public int update(TypeInfo ty) {
		if(StringUtil.checkNull(ty.getTname())) {
			return -1;
		}
		return typeInfoMapper.update(ty);
	}

	@Override
	public List<TypeInfo> findAll() {
		return typeInfoMapper.findAll();
	}

	@Override
	public List<TypeInfo> finds() {
		return typeInfoMapper.finds();
	}




}
