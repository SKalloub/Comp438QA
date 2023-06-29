package com.example.comp438assignment.currencyConverter.client;

import com.example.comp438assignment.currencyConverter.payload.ExchangeResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
	private final RestTemplate restTemplate;

	public ApiClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ExchangeResponseDTO makeApiCallToGetConversionRate(String apiUrl)
 {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(headers);
	 ResponseEntity<ExchangeResponseDTO> response = null;
			 response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, ExchangeResponseDTO.class);

			if (response==null)
				return null;
		return response.getBody();
	}
}
