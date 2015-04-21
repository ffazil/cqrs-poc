package com.tracebucket.x.cqrs.application.service.impl;

import com.tracebucket.x.cqrs.annotation.ApplicationService;
import com.tracebucket.x.cqrs.annotation.CommandHandler;
import com.tracebucket.x.cqrs.annotation.QueryHandler;
import com.tracebucket.x.cqrs.application.service.CustomerHandler;
import com.tracebucket.x.cqrs.command.Command;
import com.tracebucket.x.cqrs.domain.Customer;

/**
 * Created by ffl on 16-04-2015.
 */
@ApplicationService
public class CustomerHandlerImpl implements CustomerHandler {

	@Override
	@CommandHandler(value = "Command|newCustomer", reactor = "@commandBus")
	public Customer newCustomer(Command<Customer> command){
		Customer customer = command.getData();
		return customer;
	}

	@Override
	@QueryHandler
	public Customer findOne(String customerUid) {
		return new Customer("Algernon", "Moncheff", 0.0);
	}
}
