package com.yc.wowo.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@EnableScheduling  // 开启定时任务支持
//@Async
public class TaskDemo1 {
	
	/**
	 * cron 从左到右以空格隔开分别是 ： 秒  分 小时 月份中的日期 月份 星期中的日期 年份
	 *    * : 表示匹配该域的任意一个值
	 *    ? : 只能用于DayOfMonth 或 DayOfWeek两个域。它也可以匹配域的任意值，但实际不会。因为DayOfMonth和DayOfWeek会相互影响。例如：
	 *    	现在每个月的20日触发任务，不管20日到底是星期几，则只能使用如下写法：0 0 0 20 * ?，其中最后一位只能用?
	 *    - : 表示范围，例如在Minutes域使用 5-20，表示从5分钟到20分钟每分钟触发一次
	 *    / : 表示起始时间开始触发，然后每个固定时间触发一次。例如在Minutes域使用 5/20，意味着5分钟后触发一次，然后每隔20分钟触发一次
	 *    , : 表示列出枚举值。例如：在Minutes域使用5,20，则意味着在5和20分钟的时候触发
	 *    L : 表示最后，只能出现在DayOfWeek 或 DayOfMonth域，如果用在DayOfWeek域上使用5L，意味着在最后一个星期四触发
	 *    W : 表示有效工作日(周一到周五)，只能出现在DayOfMonth域，系统就在离指定日期的最近的有效工作日触发。例如：
	 *    	在DayOfMonth使用5W，如果5日是星期天，则将在最近的工作日6号星期一触发。如果5日是星期一到星期五中的一天，则就在5日触发。
	 *    LW: 表示在某个月最后最后一个工作日，即最后一个星期五
	 *    # : 用于确定每个月的第几个星期几，只能出现在DayOfMonth域。例如：4#2，表示某月的第二个星期三
	 *    
	 *    (1)0 0 2 1 * ? *   表示在每月的1日的凌晨2点调整任务
	 *    (2)0 15 10 ? * MON-FRI   表示周一到周五每天上午10:15执行作业
	 *    (3)0 15 10 ? 6L 2002-2006   表示2002-2006年的每个月的最后一个星期五上午10:15执行作
	 *    (4)0 0 10,14,16 * * ?   每天上午10点，下午2点，4点 
	 *    (5)0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时 
	 *    (6)0 0 12 ? * WED    表示每个星期三中午12点 
	 *    (7)0 0 12 * * ?   每天中午12点触发 
	 *    (8)0 15 10 ? * *    每天上午10:15触发 
	 *    (9)0 15 10 * * ?     每天上午10:15触发 
	 *    (10)0 15 10 * * ? *    每天上午10:15触发 
	 *    (11)0 15 10 * * ? 2005    2005年的每天上午10:15触发 
	 *    (12)0 * 14 * * ?     在每天下午2点到下午2:59期间的每1分钟触发 
	 *    (13)0 0/5 14 * * ?    在每天下午2点到下午2:55期间的每5分钟触发 
	 *    (14)0 0/5 14,18 * * ?     在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
	 *    (15)0 0-5 14 * * ?    在每天下午2点到下午2:05期间的每1分钟触发 
	 *    (16)0 10,44 14 ? 3 WED    每年三月的星期三的下午2:10和2:44触发 
	 *    (17)0 15 10 ? * MON-FRI    周一至周五的上午10:15触发 
	 *    (18)0 15 10 15 * ?    每月15日上午10:15触发 
	 *    (19)0 15 10 L * ?    每月最后一日的上午10:15触发 
	 *    (20)0 15 10 ? * 6L    每月的最后一个星期五上午10:15触发 
	 *    (21)0 15 10 ? * 6L 2002-2005   2002年至2005年的每月的最后一个星期五上午10:15触发 
	 *    (22)0 15 10 ? * 6#3   每月的第三个星期五上午10:15触发
	 */
	@Scheduled(cron = "0/5 * * * * *")
	public void run() {
		System.out.println(Thread.currentThread().getName() + " ===>>  run ==== " + System.currentTimeMillis() / 1000);
	}
	
	@Scheduled(fixedDelay = 5000)// 每隔5秒执行一次
	public void run2() {
		System.out.println(Thread.currentThread().getName() + " ===>>  run2 ==== " + System.currentTimeMillis() / 1000);		
	}
	
	@Scheduled(initialDelay  = 2000, fixedDelay = 5000 )// 每隔5秒执行一次
	public void run3() {
		System.out.println(Thread.currentThread().getName() + " ===>>  run3 ==== " + System.currentTimeMillis() / 1000);		
	}
}
