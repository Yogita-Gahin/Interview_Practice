package com.in28minutes.microservices.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import com.in28minutes.microservices.bean.CurrencyConversion;
import com.in28minutes.microservices.client.CurrencyConversionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class CurrencyConversionController {

	@Autowired
	CurrencyConversionClient currencyConversionClient;
	
	@GetMapping("/currency-coversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity<CurrencyConversion>  responseEntity=  restTemplate.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		CurrencyConversion currencyConversion = responseEntity.getBody();
		return new CurrencyConversion(1000L, from, to, quantity, currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),"");
		
	}
	
	

	@GetMapping("/currency-coversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion= currencyConversionClient.retriveExchangeValue(from, to);
		return new CurrencyConversion(1000L, from, to, quantity, currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment());
		
	}
}
