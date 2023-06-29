package com.example.comp438assignment.controller;

import com.example.comp438assignment.currencyConverter.controller.ConverterController;
import com.example.comp438assignment.currencyConverter.model.Currency;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import com.example.comp438assignment.currencyConverter.service.ConverterService;
import com.example.comp438assignment.currencyConverter.service.CurrencyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConverterControllerTest {

	@MockBean
	private ConverterService converterService;

	@MockBean
	private CurrencyService currencyService;

	@Autowired
	private ConverterController converterController;

	private String FROM_CURRENCY = "USD";
	private String TO_CURRENCY = "EUR";
	private Double amount = 5.0;

	private Double amountNegative = -5.0;

	@Test
	public void testGetConversionRate(){
		when(converterService.getConversionRate(any(String.class),any(String.class))).thenReturn(new ConversionDTO());

		ResponseEntity<ConversionDTO> response = converterController.getConversionRate(FROM_CURRENCY,TO_CURRENCY);
		Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
	}

	@Test
	public void testConvert(){
		when(converterService.convertFromTo(
			any(String.class),any(String.class),any(Double.class))).
			thenReturn(new ConversionDTO());
		ResponseEntity<ConversionDTO> response = converterController.convert(FROM_CURRENCY,TO_CURRENCY,amount);
		Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));

	}
	@Test
	public void testConvertNegative(){

	}
	@Test
	public void getAllCurrencies(){
		when(currencyService.getAllCurrencies()).thenReturn(new ArrayList<>());
		List<Currency> currencyList = converterController.getAllCurrencies();
		Assertions.assertNotNull(currencyList);

	}
}
