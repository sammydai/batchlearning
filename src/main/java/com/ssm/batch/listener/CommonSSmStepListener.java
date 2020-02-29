package com.ssm.batch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.ssm.batch.listener
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 13:20
 */
@Component
public class CommonSSmStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before----step执行之前");

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		int readCount = stepExecution.getReadCount();
		int commitCount = stepExecution.getCommitCount();
		String stepName = stepExecution.getStepName();
		System.out.println("after---step执行之后 readCount: "+readCount);
		System.out.println("after---step执行之后 stepName: "+stepName);
		System.out.println("after---step执行之后 commitCount: "+commitCount);

		System.out.println("========================================");
		return null;
	}
}
