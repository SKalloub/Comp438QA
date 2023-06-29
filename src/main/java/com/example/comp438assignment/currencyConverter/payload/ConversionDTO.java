package com.example.comp438assignment.currencyConverter.payload;

import com.example.comp438assignment.currencyConverter.model.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConversionDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String currencyFrom;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String currencyTo;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double amount;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double conversion_rate;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Double conversionResult;
}
