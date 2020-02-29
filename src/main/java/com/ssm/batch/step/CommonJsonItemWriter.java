package com.ssm.batch.step;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.JsonObjectMarshaller;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Package: com.ssm.batch.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/26 16:21
 */

public class CommonJsonItemWriter<T> extends JsonFileItemWriter<T> {

	public CommonJsonItemWriter(Resource resource, JsonObjectMarshaller<T> jsonObjectMarshaller) {
		super(resource, jsonObjectMarshaller);
		setResource(resource);
		setJsonObjectMarshaller(jsonObjectMarshaller);
		setEncoding("UTF-8");
		setName("tradeJsonFileItemWriter");
	}
}
