package io.cts.msa.om.customer.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import io.cts.msa.om.customer.service.messaging.CustomerMessageSender;

@Configuration
@EnableAsync
public class MessageSenderConfig {

	@Bean
	public Exchange eventExchange() {
		return new TopicExchange("140837-exchange");
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public CustomerMessageSender customerMessageSender(RabbitTemplate rabbitTemplate, Exchange eventExchange) {
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return new CustomerMessageSender(rabbitTemplate, eventExchange);
	}

}
