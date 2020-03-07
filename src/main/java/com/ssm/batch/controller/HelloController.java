package com.ssm.batch.controller;

import com.ssm.batch.entity.Customer;
import com.ssm.batch.service.CustomerService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
 
    // JobLauncher 由框架提供
    @Autowired
    JobLauncher jobLauncher;
 
    // Job 为刚刚配置的
    @Autowired
    Job job;

    @Autowired
    CustomerService customerService;
 
    @GetMapping("/hello")
    public void hello() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
					.addString("customerAddress","长春市")
					.addString("customerTel","43")
					.addLong("customerId",null)
					.addString("filePath","/Users/daiwenting/Documents/dataoutput.csv")
                    .toJobParameters();
            // 通过调用 JobLauncher 中的 run 方法启动一个批处理
			JobExecution result = jobLauncher.run(job, jobParameters);
			System.out.println("jobresult-----:"+result);
		} catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/hello2")
    public void hello2() {
		List<Customer> list = customerService.list();
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}

}