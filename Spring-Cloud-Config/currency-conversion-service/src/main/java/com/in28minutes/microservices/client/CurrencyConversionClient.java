package com.in28minutes.microservices.client;

import com.in28minutes.microservices.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchage",url="localhost:8000")
//Here we don't give the static url Feign Client talk with naming server and know all the instances of exchange service and
//load balance between them
@FeignClient(name="currency-exchange",url="localhost:8000")
public interface CurrencyConversionClient {
    @GetMapping("/currency-exchange/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retriveExchangeValue(@PathVariable String from, @PathVariable String to);

}
