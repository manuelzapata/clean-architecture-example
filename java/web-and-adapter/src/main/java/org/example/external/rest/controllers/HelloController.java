package org.example.external.rest.controllers;

import org.example.domain.application.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    final FlightService service;

	public HelloController(FlightService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String index() {
        this.service.checkSetup();
		return "Greetings from Spring Boot!";
	}
    
}
