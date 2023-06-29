package com.example.comp438assignment.currencyConverter.service.impl;

import com.example.comp438assignment.currencyConverter.handler.CurrencyHandler;
import com.example.comp438assignment.currencyConverter.model.Currency;
import com.example.comp438assignment.currencyConverter.repository.CurrencyRepository;
import com.example.comp438assignment.currencyConverter.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private CurrencyHandler currencyHandler;

	@Override
	public List<Currency> getAllCurrencies() {
		return currencyRepository.findAll();
	}
}
