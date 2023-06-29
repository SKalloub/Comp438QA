package com.example.comp438assignment.currencyConverter.service.impl;

import com.example.comp438assignment.currencyConverter.handler.ConverterHandler;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import com.example.comp438assignment.currencyConverter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterServiceImpl implements ConverterService {
	@Autowired
	private ConverterHandler converterHandler;
	@Override
	public ConversionDTO getConversionRate(String aInFromCurrency, String aInToCurrency) {
		ConversionDTO conversionDTO =  converterHandler.calculateConversionRate(aInFromCurrency, aInToCurrency);
		return conversionDTO;
	}

	@Override
	public ConversionDTO convertFromTo(String fromCurrency, String toCurrency, Double amount) {
		if (amount<=0.0) {
			throw new IllegalArgumentException("Amount shall be greater than zero!");
		}
		ConversionDTO conversionDTO = converterHandler.calculateConversionRate(fromCurrency,toCurrency);
		conversionDTO.setAmount(amount*conversionDTO.getConversion_rate());
		return conversionDTO;
	}


}
