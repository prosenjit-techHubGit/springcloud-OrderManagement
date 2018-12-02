package io.cts.msa.om.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.cts.msa.om.order.domain.ItemDetails;
import io.cts.msa.om.order.exception.InvalidItemException;
import io.cts.msa.om.order.exception.ItemServiceCommunicationException;
import io.cts.msa.om.order.service.messaging.CustomerMessageReceiver;

@Service

public class ItemService {

	private Logger logger = LoggerFactory.getLogger(ItemService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired

	private InvalidItemException itemException;

	@HystrixCommand(fallbackMethod = "getEmptyItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4") })
	public ItemDetails getItemByName(String itemName) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<>(headers);

		ResponseEntity<ItemDetails> response = null;
		try {
			response = restTemplate.exchange("http://140837-item-service/service/items/" + itemName, HttpMethod.GET,
					request, new ParameterizedTypeReference<ItemDetails>() {
					});
		} catch (RestClientException e) {
			logger.error("Error while calling Item Service " + e.getMessage());
			throw new ItemServiceCommunicationException(e.getMessage());

		}

		ItemDetails result = null;

		if (response.getStatusCode() == HttpStatus.OK) {

			result = response.getBody();

			if (result == null) {

				throw itemException;

			}

		}

		return result;
	}

	public ItemDetails getEmptyItem(String itemName) {
		logger.info("Fallback method invoked");
		return new ItemDetails();

	}

	@HystrixCommand(fallbackMethod = "getEmptyItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4") })
	public List<ItemDetails> getItemByNameIn(List<String> itemName) {

		Gson gsonBuilder = new GsonBuilder().create();
		// Convert Java Array into JSON

		String requestJson = gsonBuilder.toJson(itemName);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

		ResponseEntity<List<ItemDetails>> response = null;
		try {
			response = restTemplate.exchange("http://140837-item-service/service/items", HttpMethod.POST, request,
					new ParameterizedTypeReference<List<ItemDetails>>() {
					});
		} catch (RestClientException e) {
			logger.error("Error while calling Item Service " + e.getMessage());
			throw new ItemServiceCommunicationException(e.getMessage());

		}

		List<ItemDetails> result = null;

		if (response.getStatusCode() == HttpStatus.OK) {

			result = response.getBody();

			if (result == null) {

				throw new InvalidItemException();

			}

		}

		return result;
	}

	public List<ItemDetails> getEmptyItem(List<String> itemName) {
		logger.info("Fallback method invoked");
		return Arrays.asList(new ItemDetails[] { new ItemDetails() });

	}

}
