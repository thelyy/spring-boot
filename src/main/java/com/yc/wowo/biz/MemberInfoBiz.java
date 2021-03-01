package com.yc.wowo.biz;

import java.util.List;
import java.util.Map;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.dto.JsonObject;

public interface MemberInfoBiz {
	public JsonObject findByPage(Map<String, Object> map);

	public int total();
	
	public List<MemberInfo> findAll();
	
	public int add(MemberInfo  me);
	
	public int update(MemberInfo  me);
	
	public int updates(MemberInfo  me);
	
	public int updatepwd(String pwd ,String nickName);

	public MemberInfo finds(String nickName);
	
	public MemberInfo findByMid(Integer Mid);
	
	public MemberInfo login(Map<String, Object> map);
}
