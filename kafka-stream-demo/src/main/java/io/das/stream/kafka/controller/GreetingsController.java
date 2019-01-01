package io.das.stream.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.das.stream.kafka.model.Greetings;
import io.das.stream.kafka.service.GreetingsService;

public class GreetingsController {

	private final GreetingsService greetingsService;

	public GreetingsController(GreetingsService greetingsService) {

		this.greetingsService = greetingsService;

	}

	@GetMapping("/greetings")

	public void greetings(@RequestParam("message") String message) {

		Greetings greetings = Greetings.builder()

				.message(message)

				.timestamp(System.currentTimeMillis())

				.build();

		greetingsService.sendGreeting(greetings);

	}

}
