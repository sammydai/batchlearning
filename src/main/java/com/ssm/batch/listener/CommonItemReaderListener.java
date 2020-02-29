package com.ssm.batch.listener;

import com.ssm.batch.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * @Package: com.ssm.batch.listener
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 23:31
 */
@Slf4j
@Component
public class CommonItemReaderListener implements ItemReadListener<Customer> {

	@Override
	public void beforeRead() {
		System.out.println("=========================");
		System.out.println("========before read======");
	}

	@Override
	public void afterRead(Customer customer) {
		System.out.println("=========================");
		System.out.println("========after read======");
		System.out.println(customer.toString());
	}

	@Override
	public void onReadError(Exception e) {

	}


}
