package io.cts.msa.om.order.service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.cts.msa.om.order.domain.CustomerDetails;
import io.cts.msa.om.order.entity.Customer;
import io.cts.msa.om.order.service.CustomerService;

@Service

public class CustomerMessageReceiver {

	@Autowired
	private CustomerService customerService;

	private Logger logger = LoggerFactory.getLogger(CustomerMessageReceiver.class);

	@RabbitListener(queues = "140837-orderServiceQueue")
	public void receive(CustomerDetails customerDetails) {
		logger.info("Received message '{}'", customerDetails.getCustFirstNmae());

		if (customerDetails != null) {
			customerService.createCustomer(customerDetails);
		}

	}

}