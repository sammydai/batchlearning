package com.ssm.batch.service.impl;

import com.ssm.batch.dao.CustomerMapper;
import com.ssm.batch.entity.Customer;
import com.ssm.batch.entity.CustomerExample;
import com.ssm.batch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Package: com.ssm.batch.service.impl
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/26 14:44
 */

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public List<Customer> list() {
		CustomerExample customerExample = new CustomerExample();
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		return customers;
	}

}
