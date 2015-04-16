package com.tracebucket.x.cqrs.web;

import com.tracebucket.x.cqrs.annotation.PublishCommand;
import com.tracebucket.x.cqrs.domain.Customer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Created by ffl on 16-04-2015.
 */

@RestController
public class CustomerController {

	@RequestMapping(value = "/customer/default", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PublishCommand(command = "newCustomer")
	public DeferredResult<Customer> newCustomer(@RequestBody Customer customer){
		DeferredResult<Customer> result = new DeferredResult<>();
		return result;
	}
}
