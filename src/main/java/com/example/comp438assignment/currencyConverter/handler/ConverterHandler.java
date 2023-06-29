package com.example.comp438assignment.currencyConverter.handler;

import com.example.comp438assignment.currencyConverter.client.ApiClient;
import com.example.comp438assignment.currencyConverter.model.Currency;
import com.example.comp438assignment.currencyConverter.payload.ExchangeResponseDTO;
import com.example.comp438assignment.currencyConverter.repository.CurrencyRepository;
import com.example.comp438assignment.currencyConverter.payload.ConversionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.comp438assignment.currencyConverter.constants.ConverterConstants.API_KEY;
import static com.example.comp438assignment.currencyConverter.constants.ConverterConstants.API_URL;

@Component
public class ConverterHandler {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private ApiClient apiClient;

	public ConversionDTO calculateConversionRate(String aInFromCurrency, String aInToCurrency) {
		Currency aOutFromCurrency, aOutToCurrency;
		aOutFromCurrency= currencyRepository.findByCurrencyShortcut(aInFromCurrency);
		aOutToCurrency = currencyRepository.findByCurrencyShortcut(aInToCurrency);


		if (aOutFromCurrency == null || aOutToCurrency ==null)
			return null;
		Double conversionRate =  getConversionRateFromExternalApi(aInFromCurrency,aInToCurrency);
		ConversionDTO conversionDTO = new ConversionDTO();
		conversionDTO.setConversion_rate(conversionRate);
		conversionDTO.setCurrencyFrom(aInFromCurrency);
		conversionDTO.setCurrencyTo(aInToCurrency);
		return conversionDTO;
}

	private Double getConversionRateFromExternalApi(String aInFromCurrency, String aInToCurrency) {
		String Url = constructUrl(aInFromCurrency,aInToCurrency);
		ExchangeResponseDTO exchangeResponseDTO= apiClient.makeApiCallToGetConversionRate(Url);
		Double conversionRate = exchangeResponseDTO.getConversion_rate();
		return conversionRate;
	}

	private String constructUrl(String aInFromCurrency, String aInToCurrency) {
		return API_URL+API_KEY+"/"+aInFromCurrency+"/"+aInToCurrency;

	}
}
