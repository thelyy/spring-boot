package com.yc.wowo.listener;

import java.io.File;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@WebListener
public class CreateUploadPathListener implements ServletContextListener{
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		File fl = new File(uploadPath, "wowo_pics");
		System.out.println(fl);
		if(!fl.exists()) {
			fl.mkdirs();
		}
	}

}
