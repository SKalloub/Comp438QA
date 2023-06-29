package com.example.comp438assignment.currencyConverter.controller;


import com.example.comp438assignment.currencyConverter.model.Currency;
import com.example.comp438assignment.currencyConverter.service.ConverterService;
import com.example.comp438assignment.currencyConverter.service.CurrencyService;
import com.example.comp438assignment.currencyConverter.service.impl.ConverterServiceImpl;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/exchange")
@CrossOrigin(origins = "http://localhost:3000")
public class ConverterController {

	@Autowired
	private ConverterService converterService;
	@Autowired
	private CurrencyService currencyService;

	@GetMapping("/{fromCurrency}/{toCurrency}")
	public ResponseEntity<ConversionDTO> getConversionRate(@PathVariable String fromCurrency,
														   @PathVariable String toCurrency) {
		ConversionDTO conversionDTO = converterService.getConversionRate(fromCurrency.toUpperCase(Locale.ROOT),toCurrency.toUpperCase(Locale.ROOT));
		return new ResponseEntity<>(conversionDTO, HttpStatus.OK);
	}
	@GetMapping("/{fromCurrency}/{toCurrency}/{amount}")
	public ResponseEntity<ConversionDTO> convert(@PathVariable String fromCurrency,
												 @PathVariable String toCurrency,
												 @PathVariable Double amount){
		ConversionDTO conversionDTO = converterService.convertFromTo(fromCurrency.toUpperCase(),toCurrency.toUpperCase(),amount);
		return new ResponseEntity<>(conversionDTO, HttpStatus.OK);	}


	@GetMapping("/currency")
	public List<Currency> getAllCurrencies(){
		return currencyService.getAllCurrencies();
	}
}
