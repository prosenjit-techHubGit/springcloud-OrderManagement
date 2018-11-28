package io.cts.msa.om.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.request.handler.CustomerRequestHandler;
import io.cts.msa.om.customer.service.CustomerService;

@RequestMapping("/service")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRequestHandler handler;

	@PostMapping("/customer")
	public Long create(@RequestBody CustomerDetails customerDetails) {

		Long custid = handler.handleCreateCustomer(customerDetails);

		return custid;

	}

	@GetMapping("/customers")
	public List<CustomerDetails> getCustomers() {

		return customerService.getCustomers();

	}

}
