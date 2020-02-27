package com.ssm.batch.config;

import com.ssm.batch.entity.Customer;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

/**
 * @Package: com.ssm.batch.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/26 15:34
 */

public class CommonMyBatisItemReader<T> extends MyBatisPagingItemReader<T> {

	private static Logger logger = LoggerFactory.getLogger(CommonMyBatisItemReader.class);

	public CommonMyBatisItemReader(SqlSessionFactory sqlSessionFactory){
		logger.info("---------start reader------------");
		setSqlSessionFactory(sqlSessionFactory);
		setQueryId("com.ssm.batch.dao.CustomerMapper.selectAllCustomer");
		setPageSize(2);
	}
}
