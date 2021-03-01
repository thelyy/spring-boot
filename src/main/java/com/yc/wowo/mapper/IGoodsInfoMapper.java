package com.yc.wowo.mapper;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.GoodsInfo;

public interface IGoodsInfoMapper {
	/**
	 * 后端分页
	 * @param goods
	 * @return
	 */
	public List<GoodsInfo> findByPage(Map<String, Object> map);
	
	/**
	 * 获取总记录数
	 * @param goods
	 * @return
	 */
	public int total(boolean flag);
	
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 */
	public int add(GoodsInfo goods);
	
	/**
	 * 修改商品信息
	 * @param tid
	 * @return
	 */
	public int update(GoodsInfo goods);
	
	/**
	 * 查询所有在架商品信息
	 * @param tid
	 * @return
	 */
	public List<GoodsInfo> finds(Map<String, Object>map);
	
	/**
	 * 根据编号查询商品详细
	 * @param tid
	 * @return
	 */
	public GoodsInfo findByGid(String gid);
	
	public int total1(Map<String, Object> map);
	
	/**
	 * 多条件组合分页查询
	 * @param tid
	 * @return
	 */
	public List<GoodsInfo> findByCondition(Map<String, Object> map);
	
	
}
