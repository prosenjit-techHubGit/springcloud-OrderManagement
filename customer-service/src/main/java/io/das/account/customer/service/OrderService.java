package io.das.account.customer.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.das.account.customer.domain.Order;

@Service
public class OrderService {

	Logger log = LoggerFactory.getLogger(OrderService.class);

	public List<Order> getOrdersByCustomerId(Long custId) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<List<Order>> response = restTemplate.exchange(
				"http://localhost:64962/order/getCustomerOrders/12", HttpMethod.GET, request,
				new ParameterizedTypeReference<List<Order>>() {
				});

		return response.getBody();

	}

}
