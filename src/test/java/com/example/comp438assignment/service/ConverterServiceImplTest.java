package com.example.comp438assignment.service;

import com.example.comp438assignment.currencyConverter.handler.ConverterHandler;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import com.example.comp438assignment.currencyConverter.service.ConverterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ConverterServiceImplTest {
	@Autowired
	private ConverterService converterService;

	@MockBean
	private ConverterHandler converterHandler;

	private String FROM_CURRENCY = "USD";
	private String TO_CURRENCY = "EUR";

	private Double amount = 5.0;

	private Double negativeAmount = -5.0;

	private Double ZERO = 0.0;

	private Double conversionRate = 0.9;

	@Test
	public void testGetConversionRate() {
		ConversionDTO conversionDTO = new ConversionDTO();
		conversionDTO.setConversion_rate(conversionRate);
		when(converterHandler.calculateConversionRate(FROM_CURRENCY, TO_CURRENCY)).thenReturn(conversionDTO);
		ConversionDTO outConversionDTO = converterService.getConversionRate(FROM_CURRENCY, TO_CURRENCY);
		Assertions.assertEquals(conversionRate, outConversionDTO.getConversion_rate());
	}

	@Test
	public void testConvertAmountFromTo() {
		ConversionDTO conversionDTO = new ConversionDTO();
		conversionDTO.setConversion_rate(conversionRate);
		when(converterHandler.calculateConversionRate(FROM_CURRENCY, TO_CURRENCY)).thenReturn(conversionDTO);
		ConversionDTO outConversionDTO = converterService.convertFromTo(FROM_CURRENCY, TO_CURRENCY, amount);
		Assertions.assertEquals(outConversionDTO.getAmount(), amount * conversionRate);
	}

	@Test
	public void testConvertNegativeAmountFromTo() {
		ConversionDTO conversionDTO = new ConversionDTO();
		conversionDTO.setConversion_rate(conversionRate);
		when(converterHandler.calculateConversionRate(FROM_CURRENCY, TO_CURRENCY)).thenReturn(conversionDTO);
		Assertions.assertThrows(IllegalArgumentException.class, () ->
			converterService.convertFromTo(FROM_CURRENCY, TO_CURRENCY, negativeAmount));
	}

	@Test
	public void testConvertZeroAmountFromTo() {
		ConversionDTO conversionDTO = new ConversionDTO();
		conversionDTO.setConversion_rate(conversionRate);
		when(converterHandler.calculateConversionRate(FROM_CURRENCY, TO_CURRENCY)).thenReturn(conversionDTO);
		Assertions.assertThrows(IllegalArgumentException.class, () ->
			converterService.convertFromTo(FROM_CURRENCY, TO_CURRENCY, ZERO));
	}
}
