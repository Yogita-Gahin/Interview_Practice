package com.com.in28minutes.microservices.controller;



import java.math.BigDecimal;

import com.com.in28minutes.microservices.bean.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from,@PathVariable String to) {
		CurrencyExchange currencyExchangec= new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
		currencyExchangec.setEnvironment(environment.getProperty("server.port"));
		return currencyExchangec;
		
	}
}
