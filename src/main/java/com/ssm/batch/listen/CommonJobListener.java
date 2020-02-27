package com.ssm.batch.listen;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.ssm.batch.listen
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 13:18
 */
@Component
public class CommonJobListener implements JobExecutionListener {
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("before--job执行执行做处理");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after---job执行执行做处理");
		if (jobExecution.getStatus()==BatchStatus.COMPLETED){
			System.out.println("----job成功");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println("----job失败");
		}

	}

}
