package com.ssm.batch.step;

import com.ssm.batch.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

/**
 * @Package: com.ssm.batch.config
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 11:02
 */

public class CommonProcess implements ItemProcessor<Customer,Customer> {
	@Override
	public Customer process(Customer customer) throws Exception {
		return customer;
	}
}
