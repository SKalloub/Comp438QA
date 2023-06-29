package com.example.comp438assignment.client;

import com.example.comp438assignment.currencyConverter.client.ApiClient;
import com.example.comp438assignment.currencyConverter.payload.ExchangeResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;


@SpringBootTest
public class ApiClientTest {

	@Autowired
	private ApiClient apiClient;

	private static final String API_KEY = "874821593b90a05832e67b8e/pair/";
	private static final String API_URL = "https://v6.exchangerate-api.com/v6/";

	private String fromCurrency = "/USD";
	private String toCurrency = "/ILS";
	private String invalidToCurrency = "any";
	private String invalidFromCurrency = "any";
	private String invalidApiKey = "ThisIsAWrongKey/pair";
	private String amount = "/5";
	@BeforeAll
	public static void setup() {
	}


	@Test
	@EnabledIfSystemProperty(named = "test.environment", matches = "dev")
	public void testMakeApiCallToGetConversionRate() {
		String URL = API_URL+API_KEY+fromCurrency+toCurrency+amount;
		ExchangeResponseDTO responseDTO = apiClient.makeApiCallToGetConversionRate(URL);
		Assertions.assertNotNull(responseDTO);
	}

	@Test
	public void testMakeApiCallToGetConversionRateWithInvalidParams() {
		String URL = API_URL+API_KEY+invalidFromCurrency+invalidToCurrency+amount;
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class,
			() -> apiClient.makeApiCallToGetConversionRate(URL));
		Assertions.assertEquals(exception.getStatusCode(), HttpStatusCode.valueOf(404));
	}

	@Test
	public void testMakeApiCallToGetConversionRateWithIncompleteURL(){
		String URL = API_URL+API_KEY+invalidFromCurrency+amount;
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class,
			() -> apiClient.makeApiCallToGetConversionRate(URL));
		Assertions.assertEquals(exception.getStatusCode(), HttpStatusCode.valueOf(404));
	}

	@Test
	public void testMakeApiCallToGetConversionRateWithWrongApiKey(){
		String URL = API_URL+invalidApiKey+fromCurrency+toCurrency+amount;
		HttpClientErrorException exception = Assertions.assertThrows(HttpClientErrorException.class,
			() -> apiClient.makeApiCallToGetConversionRate(URL));
		Assertions.assertEquals(exception.getStatusCode(), HttpStatusCode.valueOf(403));

	}
}
