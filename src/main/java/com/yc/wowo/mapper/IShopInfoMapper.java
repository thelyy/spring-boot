package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.ShopInfo;

public interface IShopInfoMapper {
	public List<ShopInfo> findByPage(Map<String, Object>map);

	public int total();
	
	public int add(ShopInfo sp);
	
	public int update(ShopInfo sp);
	
	public List<ShopInfo> finds();
	
	public ShopInfo findBySid(String sid);
	
	public int totals(Map<String, Object> map);
	
	public List<ShopInfo> findByCondition(Map<String, Object> map);
}
