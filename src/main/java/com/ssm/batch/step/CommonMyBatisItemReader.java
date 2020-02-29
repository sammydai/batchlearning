package com.ssm.batch.step;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @Package: com.ssm.batch.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/26 15:34
 */

public class CommonMyBatisItemReader<T> extends MyBatisPagingItemReader<T> {

	private static Logger logger = LoggerFactory.getLogger(CommonMyBatisItemReader.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;



	public CommonMyBatisItemReader(SqlSessionFactory sqlSessionFactory, Map<String, Object> testMap){
		logger.info("---------start reader------------");
		setSqlSessionFactory(sqlSessionFactory);
		setParameterValues(testMap);
		setQueryId("com.ssm.batch.dao.CustomerMapper.selectAllCustomer");
		setPageSize(5);
	}





}
