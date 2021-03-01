package com.yc.wowo.biz.impl;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.GoodsInfo;
import com.yc.wowo.biz.GoodsInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.IGoodsInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class GoodsInfoBizImpl implements GoodsInfoBiz{
	@Autowired
	private IGoodsInfoMapper goodsInfo;

	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(goodsInfo.total(true), goodsInfo.findByPage(map));
	}

	@Override
	public int total() {
		return goodsInfo.total(false);
	}

	@Override
	public int add(GoodsInfo goods) {
		if(StringUtil.checkNull(goods.getGname(), String.valueOf(goods.getPrice()), String.valueOf(goods.getRebate()), goods.getSdate())) {
			return -1;
		}
		return goodsInfo.add(goods);
	}

	@Override
	public int update(GoodsInfo goods) {
		if(StringUtil.checkNull(goods.getSdate(), goods.getEdata())) {
			return -1;
		}
		return goodsInfo.update(goods);
	}

	@Override
	public List<GoodsInfo> finds(Map<String, Object> map) {
		return goodsInfo.finds(map);
	}

	@Override
	public GoodsInfo findByGid(String gid) {
		return goodsInfo.findByGid(gid);
	}

	@Override
	public int total1(Map<String, Object> map) {
		return goodsInfo.total1(map);
	}

	@Override
	public JsonObject findByCondition(Map<String, Object> map) {
		return new JsonObject(goodsInfo.total1(map), goodsInfo.findByCondition(map));
	}
	
	

}
