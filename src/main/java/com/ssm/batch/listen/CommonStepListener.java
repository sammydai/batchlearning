package com.ssm.batch.listen;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.StepListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

/**
 * @Package: com.ssm.batch.listen
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 13:20
 */
@Component
public class CommonStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("before----step执行之前");

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		int readCount = stepExecution.getReadCount();
		String stepName = stepExecution.getStepName();
		System.out.println("after---step执行之后 readCount: "+readCount);
		System.out.println("after---step执行之后 stepName: "+stepName);
		System.out.println("========================================");
		return null;
	}
}
