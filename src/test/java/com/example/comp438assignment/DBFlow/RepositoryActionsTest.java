package com.example.comp438assignment.DBFlow;


import com.example.comp438assignment.currencyConverter.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryActionsTest {

	@Autowired
	private CurrencyRepository currencyRepository;

	private Long ID = 1l;
	private String SHORTCUT = "USD";
	@Test
	public void testGetAllCurrencies() {
		Assertions.assertFalse(currencyRepository.findAll().isEmpty());
	}

	@Test
	public void testGetCurrencyByShortcut() {
		Assertions.assertNotNull(currencyRepository.findByCurrencyShortcut(SHORTCUT));
	}
}
