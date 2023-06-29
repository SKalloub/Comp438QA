package com.example.comp438assignment.handler;

import com.example.comp438assignment.currencyConverter.client.ApiClient;
import com.example.comp438assignment.currencyConverter.handler.ConverterHandler;
import com.example.comp438assignment.currencyConverter.model.Currency;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import com.example.comp438assignment.currencyConverter.payload.ExchangeResponseDTO;
import com.example.comp438assignment.currencyConverter.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConverterHandlerTest {

	@Autowired
	private ConverterHandler converterHandler;
	@MockBean
	private CurrencyRepository currencyRepository;
	@MockBean
	private  ApiClient apiClient;

	private static String FROM_CURRENCY = "USD";
	private static String TO_CURRENCY = "EUR";
	private static final String FROM_CURRENCY_ID = "1";
	private static final String TO_CURRENCY_ID = "2";
	private static final String FROM_CURRENCY_NAME = "United States Dollar";
	private static final String TO_CURRENCY_NAME = "Euro";
	private static Currency fromCurrency;
	private static Currency toCurrency;
	private static Double conversion_rate = 0.9;
	@BeforeAll
	public static void setup() {

		 fromCurrency = new Currency();
		fromCurrency.setCurrencyId(FROM_CURRENCY_ID);
		fromCurrency.setCurrencyShortcut(FROM_CURRENCY);
		fromCurrency.setCurrencyName(FROM_CURRENCY_NAME);
		 toCurrency = new Currency();
		toCurrency.setCurrencyId(TO_CURRENCY_ID);
		toCurrency.setCurrencyName(TO_CURRENCY_NAME);
		toCurrency.setCurrencyShortcut(TO_CURRENCY);
	}

	@Test
	public void testCalculateConversionRate(){
		ExchangeResponseDTO exchangeResponseDTO = new ExchangeResponseDTO();
		exchangeResponseDTO.setConversion_rate(conversion_rate);
		when(currencyRepository.findByCurrencyShortcut(FROM_CURRENCY)).thenReturn(fromCurrency);
		when(currencyRepository.findByCurrencyShortcut(TO_CURRENCY)).thenReturn(toCurrency);
		when(apiClient.makeApiCallToGetConversionRate(any(String.class))).thenReturn(exchangeResponseDTO);
		ConversionDTO conversionDTO = converterHandler.calculateConversionRate(FROM_CURRENCY,TO_CURRENCY);
		Assertions.assertNotNull(conversionDTO);
		Assertions.assertEquals(conversionDTO.getConversion_rate(),conversion_rate);
	}


	@Test
	public void testCalculateConversionRateWhenResponseIsNull(){
		when(currencyRepository.findByCurrencyShortcut(FROM_CURRENCY)).thenReturn(null);
		when(currencyRepository.findByCurrencyShortcut(TO_CURRENCY)).thenReturn(null);
		ConversionDTO conversionDTO = converterHandler.calculateConversionRate(FROM_CURRENCY,TO_CURRENCY);
		Assertions.assertNull(conversionDTO);
	}
}
