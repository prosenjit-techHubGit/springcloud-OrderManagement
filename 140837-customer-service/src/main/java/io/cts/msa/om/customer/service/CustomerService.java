package io.cts.msa.om.customer.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.entity.Customer;
import io.cts.msa.om.customer.exception.CustomerCreateException;
import io.cts.msa.om.customer.exception.CustomerRecordQueryException;
import io.cts.msa.om.customer.repository.CustomerRepository;
import io.cts.msa.om.customer.service.messaging.CustomerMessageSender;

@Service
public class CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerService.class);

	private CustomerRepository customerRepository;
	private CustomerMessageSender customerMessageSender;

	@Autowired
	private CustomerCreateException createException;
	@Autowired
	private CustomerRecordQueryException queryException;

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

			try {
				customerRepository.save(customer);
			} catch (Exception e) {
				logger.error("Error in saving customer record to DB:  " + e.getMessage());

				throw createException;
			}

			return customer.getId();

		}

		return null;

	}

	public List<CustomerDetails> getCustomers() {

		List<Customer> customers = null;
		try {
			customers = customerRepository.findAll();
		} catch (Exception e) {
			logger.error("Error while querying Customer records from DB: " + e.getMessage());

			throw queryException;

		}

		List<CustomerDetails> customerDetails = null;

		if (customers != null && customers.size() != 0) {
			customerDetails = customers.stream().map(c -> {

				CustomerDetails custDetails = new CustomerDetails();

				custDetails.setEmail(c.getEmail());
				custDetails.setFirstName(c.getFirstName());
				custDetails.setLastName(c.getLastName());
				custDetails.setId(c.getId());
				return custDetails;

			}).collect(Collectors.toList());

		}

		return customerDetails;

	}

}
