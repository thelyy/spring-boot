

package com.yc.wowo.biz;

import java.util.List;

import java.util.Map;

import com.yc.wowo.bean.TypeInfo;
import com.yc.wowo.dto.JsonObject;

public interface TypeInfoBiz {

	public int add(TypeInfo ty);
	
	public int update(TypeInfo ty);
	
	public List<TypeInfo> findAll();
	
	public List<TypeInfo> finds();
	
	public JsonObject findByPage(Map<String, Object> map);
}
