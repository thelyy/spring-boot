package com.yc.wowo.mapper;

import java.util.List;

import java.util.Map;

import com.yc.wowo.bean.TypeInfo;

public interface ITypeInfoMapper {
	public int add(TypeInfo ty);
	
	public int update(TypeInfo ty);
	
	public List<TypeInfo> findAll();
	
	public List<TypeInfo> finds();
	
	public List<TypeInfo> findByPage(Map<String, Object> map);
	
	public int total();
	
}
