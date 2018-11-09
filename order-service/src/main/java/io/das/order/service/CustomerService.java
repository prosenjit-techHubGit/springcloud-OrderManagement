package io.das.order.service;

import java.util.Arrays;

import javax.xml.ws.Response;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.das.order.domain.Customer;

@Service
public class CustomerService {

	public Customer getCustomerById(Long custId) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<Customer> response = restTemplate.exchange("http://localhost:64954/customer/12", HttpMethod.GET,
				request, new ParameterizedTypeReference<Customer>() {
				});

		return response.getBody();
	}

}
