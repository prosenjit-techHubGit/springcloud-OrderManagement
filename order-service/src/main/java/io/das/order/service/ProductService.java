package io.das.order.service;

import java.util.Arrays;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.das.order.domain.Customer;
import io.das.order.domain.Product;

@Service
public class ProductService {

	public Product getProductBySku(String sku) {

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<Product> response = restTemplate.exchange("http://localhost:64981/product/12", HttpMethod.GET,
				request, new ParameterizedTypeReference<Product>() {
				});

		return response.getBody();
	}

}
