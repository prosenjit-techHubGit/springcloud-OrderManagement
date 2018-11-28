package io.cts.msa.om.customer.request.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.service.CustomerService;
import io.cts.msa.om.customer.service.messaging.CustomerMessageSender;

@Component
public class CustomerRequestHandler {

	private CustomerService customerService;
	private CustomerMessageSender messageService;

	@Autowired
	public CustomerRequestHandler(CustomerService customerService, CustomerMessageSender messageService) {
		this.customerService = customerService;
		this.messageService = messageService;

	}

	public Long handleCreateCustomer(CustomerDetails customerDetails) {

		Long custid = customerService.createCustomer(customerDetails);
		System.out.println("Calling method asynchronously from Thread  "
			      + Thread.currentThread().getName());
		
		
		if (custid != null) {
			customerDetails.setId(custid);
			messageService.SendCreateCustomerEvent(customerDetails);

		}

		return custid;
	}

}
