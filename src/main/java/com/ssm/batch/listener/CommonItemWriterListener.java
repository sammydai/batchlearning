package com.ssm.batch.listener;

import com.ssm.batch.entity.Customer;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @Package: com.ssm.batch.listener
 * @Description:
 * @Author: Sammy
 * @Date: 2020/2/27 23:31
 */
@Component
public class CommonItemWriterListener implements ItemWriteListener<Customer> {

	@Override
	public void beforeWrite(List<? extends Customer> list) {
		// System.out.println("=========================");
		System.out.println("========before write=====");
		System.out.println("========size============="+list.size());
		for (Object o : list) {
			Customer customer = (Customer) o;
			System.out.println(customer.getCustomerUser());
			System.out.println(customer.toString());
		}
	}

	@Override
	public void afterWrite(List<? extends Customer> list) {
		// System.out.println("=========================");
		System.out.println("========after write=====");
		System.out.println("========size============="+list.size());
	}

	@Override
	public void onWriteError(Exception e, List<? extends Customer> list) {

	}
}
