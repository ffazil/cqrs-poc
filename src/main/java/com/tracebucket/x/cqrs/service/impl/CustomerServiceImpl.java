package com.tracebucket.x.cqrs.service.impl;

import com.tracebucket.x.cqrs.annotation.ApplicationService;
import com.tracebucket.x.cqrs.annotation.CommandHandler;
import com.tracebucket.x.cqrs.command.Command;
import com.tracebucket.x.cqrs.domain.Customer;

/**
 * Created by ffl on 16-04-2015.
 */
@ApplicationService
public class CustomerServiceImpl {

	@CommandHandler(value = "Command|newCustomer", reactor = "@commandBus")
	public Customer newCustomer(Command<Customer> command){
		Customer customer = command.getData();
		return customer;
	}
}
