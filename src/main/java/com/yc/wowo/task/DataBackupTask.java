package com.yc.wowo.task;

import java.io.File;
import java.time.LocalDateTime;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Async
public class DataBackupTask {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${spring.datasource.dbName}")
	private String dbName;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String pwd;
	
	@Value("${spring.datasource.backupPath}")
	private String path;
	
	@Scheduled(cron = "0 4 16 * * ?")
	public void run() {
		File fl = new File(path);
		
		if(!fl.exists()) {
			fl.mkdirs();
		}
		
		try {
			String pathExe = this.getClass().getClassLoader().getResource("").getPath();
			if(pathExe.startsWith("/")) {
				pathExe = pathExe.substring(1);
			}
			
			String cmd = "cmd /c " + pathExe + "mysqldump -u " + username + " -p" + pwd + " --database " + dbName + " > " + path + "\\" + dbName + "_" + System.currentTimeMillis() + ".sql";
			Process process = Runtime.getRuntime().exec(cmd);
			if(process.waitFor() == 0) {
				logger.info(LocalDateTime.now() + " 备份数据成功...");
			}
		} catch (Exception e) {
				logger.info(LocalDateTime.now() + " 备份数据失败...");
			e.printStackTrace();
	}
	}
}
