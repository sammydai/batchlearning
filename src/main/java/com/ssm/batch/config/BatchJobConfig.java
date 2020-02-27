package com.ssm.batch.config;

import com.ssm.batch.dao.CustomerMapper;
import com.ssm.batch.entity.Customer;
import com.ssm.batch.listen.CommonJobListener;
import com.ssm.batch.listen.CommonStepListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

/**
 * @Package: com.ssm.batch.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/26 12:07
 */

@Configuration
public class BatchJobConfig {

	// 注入JobBuilderFactory，用来构建Job
	@Autowired
	JobBuilderFactory jobBuilderFactory;

	// 注入StepBuilderFactory，用来构建Step
	@Autowired
	StepBuilderFactory stepBuilderFactory;

	// 注入DataSource，用来支持持久化操作，这里持久化方案是Spring-Jdbc
	@Autowired
	DataSource dataSource;

	@Autowired
	SqlSessionFactory sqlSessionFactory;

	@Autowired
	CommonJobListener commonJobListener;

	@Autowired
	CommonStepListener commonStepListener;

	@Bean
	@StepScope
	public CommonMyBatisItemReader<Customer> customerReader(){
		return new CommonMyBatisItemReader<>(sqlSessionFactory);
	}

	@Bean
	@StepScope
	public CommonJsonItemWriter commonJsonItemWriter() {
		FileSystemResource fileSystemResource = new FileSystemResource("/Users/daiwenting/GitProject/batchlearning/src/main/resources/sample-data.json");
		return new CommonJsonItemWriter(fileSystemResource,new JacksonJsonObjectMarshaller());
	}

	// 配置一个Step
	@Bean
	Step customerStep() {
		// Step通过stepBuilderFactory进行配置
		return stepBuilderFactory.get("customerStep6") //通过get获取一个StepBuilder，参数数Step的name
				.listener(commonStepListener)
				.<Customer, Customer>chunk(2) //方法的参数2，表示每读取到两条数据就执行一次write操作
				.reader(customerReader()) // 配置reader
				.writer(commonJsonItemWriter()) // 配置writer
				.build();
	}

	// 配置一个Job
	@Bean
	Job csvJob() {
		// 通过jobBuilderFactory构建一个Job，get方法参数为Job的name
		return jobBuilderFactory.get("customerJob6")
				.start(customerStep()) // 配置该Job的Step
				.listener(commonJobListener)
				.build();
	}

}
