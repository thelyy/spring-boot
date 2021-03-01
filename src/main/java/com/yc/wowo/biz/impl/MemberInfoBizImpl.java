package com.yc.wowo.biz.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.MemberInfo;
import com.yc.wowo.biz.MemberInfoBiz;
import com.yc.wowo.dto.JsonObject;
import com.yc.wowo.mapper.IMemberInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class MemberInfoBizImpl implements MemberInfoBiz{
	@Autowired
	private IMemberInfoMapper memberInfo;
	
	@Override
	public JsonObject findByPage(Map<String, Object> map) {
		return new JsonObject(memberInfo.total(true), memberInfo.findByPage(map));
	}

	@Override
	public int total() {
		return memberInfo.total(false);
	}

	@Override
	public List<MemberInfo> findAll() {
		return memberInfo.findAll();
	}

	@Override
	public int add(MemberInfo me) {
		if(StringUtil.checkNull(me.getNickName(), me.getPwd(), me.getEmail())) {
			return -1;
		}
		return memberInfo.add(me);
	}

	@Override
	public int update(MemberInfo me) {
		if(StringUtil.checkNull(me.getRealName(), me.getPhoto())) {
			return -1;
		}
		return memberInfo.update(me);
	}

	@Override
	public int updates(MemberInfo me) {
		if(StringUtil.checkNull(me.getRealName(), me.getPhoto())) {
			return -1;
		}
		return memberInfo.update(me);
	}

	@Override
	public int updatepwd(String pwd, String nickName) {
		if(StringUtil.checkNull(pwd, nickName)) {
			return -1;
		}
		return memberInfo.updatepwd(pwd, nickName);
	}

	@Override
	public MemberInfo finds(String nickName) {
		return memberInfo.finds(nickName);
	}

	@Override
	public MemberInfo findByMid(Integer Mid) {
		return memberInfo.findByMid(Mid);
	}

	@Override
	public MemberInfo login(Map<String, Object> map) {
		return memberInfo.login(map);
	}



}
