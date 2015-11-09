package cn.baiing.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value = false)
public class DataSyncTask {
	private static Logger logger = LoggerFactory.getLogger(DataSyncTask.class);

	// cron表达式：* * * * * *（共6位，使用空格隔开，具体如下）
	// cron表达式：*(秒0-59) *(分钟0-59) *(小时0-23) *(日期1-31)
	// *(月份1-12或是JAN-DEC) *(星期1-7或是SUN-SAT)
	/**
	 * 执行定时任务
	 */
	// @Scheduled(cron = "0 30 3 * * *")
	public void syncData() {
		if (logger.isInfoEnabled())
			logger.info("执行定时任务 " + new Date());
	}

	/**
	 * 固定周期计划任务，时间单位毫秒
	 */
	// @Scheduled(fixedRate = 3000)
	public void syncDataInFixedRate() {
		if (logger.isInfoEnabled())
			logger.info("执行固定周期计划任务 " + new Date());
	}
}
