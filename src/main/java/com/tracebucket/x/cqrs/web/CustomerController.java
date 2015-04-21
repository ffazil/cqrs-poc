package com.tracebucket.x.cqrs.web;

import com.tracebucket.x.cqrs.annotation.PublishCommand;
import com.tracebucket.x.cqrs.application.command.NewCustomer;
import com.tracebucket.x.cqrs.application.service.CustomerHandler;
import com.tracebucket.x.cqrs.command.Command;
import com.tracebucket.x.cqrs.domain.Customer;
import com.tracebucket.x.cqrs.support.CommandWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by ffl on 16-04-2015.
 */

@RestController
public class CustomerController {

	@Autowired
	private CustomerHandler customerHandler;

	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PublishCommand(command = "newCustomer")
	public String newCustomer(@RequestBody NewCustomer newCustomer){
		return "forward:/customer";
	}

	@RequestMapping(value = "/customer/{customer_uid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> get(@RequestParam("customer_uid") String customerUid){
		Customer customer = customerHandler.findOne(customerUid);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
}
