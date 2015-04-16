package com.tracebucket.x.cqrs.test.controller;

import com.tracebucket.x.cqrs.CQRSStarter;
import com.tracebucket.x.cqrs.domain.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by ffl on 16-04-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CQRSStarter.class)
@WebIntegrationTest(value = "server.port=8080")
public class CustomerControllerTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testNewCustomer() throws URISyntaxException {
		Customer customer = new Customer("Algernon", "Moncheff", 0.0);
		ResponseEntity<Customer> responseEntity = restTemplate.postForEntity(new URI("/customer"), customer, Customer.class);
		Assert.assertNotNull(responseEntity.getBody());
	}
}
