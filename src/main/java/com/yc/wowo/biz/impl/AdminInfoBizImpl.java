package com.yc.wowo.biz.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.wowo.bean.AdminInfo;
import com.yc.wowo.biz.AdminInfoBiz;
import com.yc.wowo.mapper.IAdminInfoMapper;
import com.yc.wowo.util.StringUtil;

@Service
public class AdminInfoBizImpl implements AdminInfoBiz{
	@Autowired
	private IAdminInfoMapper adminInfo;
	
	@Override
	public AdminInfo login(AdminInfo af) {
		return adminInfo.login(af);
	}

	@Override
	public int add(AdminInfo ad) {
		if(StringUtil.checkNull(ad.getAname(), ad.getPwd())) {
			return -1;
		}
		
		return adminInfo.add(ad);
	}

}
