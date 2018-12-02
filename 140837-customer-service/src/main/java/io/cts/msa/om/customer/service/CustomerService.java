package io.cts.msa.om.customer.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.entity.Customer;
import io.cts.msa.om.customer.exception.CustomerCreateException;
import io.cts.msa.om.customer.repository.CustomerRepository;
import io.cts.msa.om.customer.service.messaging.CustomerMessageSender;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private CustomerMessageSender customerMessageSender;

	@Autowired
	private CustomerCreateException exception;

	@Autowired
	public CustomerService(CustomerRepository customerRepository, CustomerMessageSender customerMessageSender) {
		this.customerRepository = customerRepository;
		this.customerMessageSender = customerMessageSender;

	}

	public Long createCustomer(CustomerDetails customerDetails) {



			Customer customer = null;

			if (customerDetails != null) {
				customer = new Customer();
				customer.setEmail(customerDetails.getEmail());
				customer.setFirstName(customerDetails.getFirstName());
				customer.setLastName(customerDetails.getLastName());
				customerRepository.save(customer);

				return customer.getId();

			}
		

		return null;

	}

	public List<CustomerDetails> getCustomers() {

		List<Customer> customers = customerRepository.findAll();

		return customers.stream().map(c -> {

			CustomerDetails custDetails = new CustomerDetails();

			custDetails.setEmail(c.getEmail());
			custDetails.setFirstName(c.getFirstName());
			custDetails.setLastName(c.getLastName());
			custDetails.setId(c.getId());
			return custDetails;

		}).collect(Collectors.toList());

	}

}
