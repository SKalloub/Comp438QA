package com.example.comp438assignment.currencyConverter.service;

import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;

public interface ConverterService {

	ConversionDTO getConversionRate(String aInFromCurrency, String aInToCurrency);

	ConversionDTO convertFromTo(String fromCurrency, String toCurrency, Double amount);
}
