package com.yc.wowo.mapper;



import com.yc.wowo.bean.AdminInfo;

public interface IAdminInfoMapper {
	public AdminInfo login(AdminInfo af);
	
	public int add(AdminInfo  me);
}
