package io.cts.msa.om.customer.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.entity.Customer;
import io.cts.msa.om.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;

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
