package com.ssm.batch.config;

import com.ssm.batch.entity.Customer;
import com.ssm.batch.listener.*;
import com.ssm.batch.step.CommonFileItemWriter;
import com.ssm.batch.step.CommonJsonItemWriter;
import com.ssm.batch.step.CommonMyBatisItemReader;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
	CommonSSmStepListener commonSSmStepListener;

	@Autowired
	CommonChunkListener commonChunkListener;

	@Autowired
	CommonItemReaderListener commonItemReaderListener;

	@Autowired
	CommonItemWriterListener commonItemWriterListener;



	@Bean
	@StepScope
	public CommonMyBatisItemReader<Customer> customerReader(@Value("#{@datesParameters}") Map<String, Object> datesParameters){
		return new CommonMyBatisItemReader<>(sqlSessionFactory,datesParameters);
	}

	@Bean
	@StepScope
	public CommonFileItemWriter<Customer> customerWriter(@Value("")String filePath){
		return new CommonFileItemWriter<>(Customer.class,filePath);
	}

	@StepScope
	@Bean
	public Map<String, Object> datesParameters(
			@Value("#{jobParameters[customerAddress]}") String customerAddress,
			@Value("#{jobParameters[customerTel]}") String customerTel,
			@Value("#{jobParameters[customerId]}") String customerId) {
		Map<String, Object> map = new HashMap<>();
		map.put("customerAddress", customerAddress);
		map.put("customerTel", customerTel);
		map.put("customerId", customerId);
		System.out.println("=========map get customerAddress======:"+map.get("customerAddress"));
		return map;
	}

	@StepScope
	@Bean
	public String filePathParameter(@Value("#{jobParameters[filePath]}")String filePath){
		// Map<String, Object> map = new HashMap<>();
		// map.put("filePath", filePath);
		return filePath;
	}


	// @Bean
	// @StepScope
	// public CommonJsonItemWriter commonJsonItemWriter() {
	// 	FileSystemResource fileSystemResource = new FileSystemResource("/Users/daiwenting/GitProject/batchlearning/src/main/resources/sample-data.json");
	// 	return new CommonJsonItemWriter(fileSystemResource,new JacksonJsonObjectMarshaller());
	// }

	// 配置一个Step
	@Bean
	Step customerStep() {
		return stepBuilderFactory.get("customerStep8")//通过get获取一个StepBuilder，参数数Step的name
				.<Customer, Customer>chunk(2)//方法的参数2，表示每读取到两条数据就执行一次write操作
				.reader(customerReader(datesParameters(null,null,null))).listener(commonItemReaderListener)// 配置reader
				.writer(customerWriter(filePathParameter(null))).listener(commonItemWriterListener)// 配置writer
				.build();

	}

	// 配置一个Job
	@Bean
	Job csvJob() {
		// 通过jobBuilderFactory构建一个Job，get方法参数为Job的name
		return jobBuilderFactory.get(UUID.randomUUID().toString())
				.start(customerStep()) // 配置该Job的Step
				.listener(commonJobListener)
				.build();
	}

}
