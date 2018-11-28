package io.cts.msa.om.order.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.order.domain.CustomerDetails;
import io.cts.msa.om.order.entity.Customer;
import io.cts.msa.om.order.repository.CustomerRepository;
import io.cts.msa.om.order.service.messaging.CustomerMessageReceiver;

@Service
public class CustomerService {

	private Logger logger = LoggerFactory.getLogger(CustomerMessageReceiver.class);

	@Autowired
	private CustomerRepository customerRepository;

	public CustomerDetails getCustomerbyId(Long id) {

		CustomerDetails custDetails = null;

		Optional<Customer> opt = customerRepository.findById(id);

		if (opt.isPresent()) {

			Customer customer = opt.get();

			custDetails = new CustomerDetails();

			custDetails.setCustId(customer.getCust_id());
			custDetails.setCustFirstNmae(customer.getCustFirstNmae());
			custDetails.setCustLastName(customer.getCustLastName());
			custDetails.setCustEmail(customer.getCust_email());

		}

		return custDetails;

	}

	public void createCustomer(CustomerDetails customerDetails) {
		logger.info("creating custmer in Sales Order");

		Customer customer = new Customer();

		customer.setCust_id(customerDetails.getCustId());
		customer.setCustFirstNmae(customerDetails.getCustFirstNmae());
		customer.setCustLastName(customerDetails.getCustLastName());
		customer.setCust_email(customerDetails.getCustEmail());

		customerRepository.save(customer);

		logger.info("Customer created in Sales Order");

	}

}
