package io.cts.msa.om.customer.service.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;

import io.cts.msa.om.customer.domain.CustomerDetails;
import io.cts.msa.om.customer.exception.CustomerMessageSendException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Exchange;

public class CustomerMessageSender {

	private Logger logger = LoggerFactory.getLogger(CustomerMessageSender.class);

	private final RabbitTemplate rabbitTemplate;

	private final Exchange exchange;

	public CustomerMessageSender(RabbitTemplate rabbitTemplate, Exchange exchange) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchange = exchange;
	}

	@Async
	public void SendCreateCustomerEvent(CustomerDetails customerDetails) {

		System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
		String routingKey = "customer.created";
		String message = "customer created";

		try {
			rabbitTemplate.convertAndSend(exchange.getName(), routingKey, customerDetails);
		} catch (AmqpException e) {

			logger.error(e.getMessage());

			throw new CustomerMessageSendException(e.getMessage());

		}
	}

}
