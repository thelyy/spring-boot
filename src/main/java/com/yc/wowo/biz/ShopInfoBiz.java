package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.ShopInfo;
import com.yc.wowo.dto.JsonObject;

public interface ShopInfoBiz {
	public JsonObject findByPage(Map<String, Object> map);
	
	public int add(ShopInfo sp);
	
	public int update(ShopInfo sp);
	
	public List<ShopInfo> finds();
	
	public ShopInfo findBySid(String sid);
	
	public JsonObject findByCondition(Map<String, Object> map);
}
