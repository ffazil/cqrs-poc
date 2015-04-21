package com.tracebucket.x.cqrs.application.service;

import com.tracebucket.x.cqrs.annotation.CommandHandler;
import com.tracebucket.x.cqrs.command.Command;
import com.tracebucket.x.cqrs.domain.Customer;

/**
 * Created by ffl on 20-04-2015.
 */
public interface CustomerHandler {
	Customer newCustomer(Command<Customer> command);
	Customer findOne(String customerUid);
}
