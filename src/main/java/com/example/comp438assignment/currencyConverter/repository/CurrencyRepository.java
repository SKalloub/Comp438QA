package com.example.comp438assignment.currencyConverter.repository;

import com.example.comp438assignment.currencyConverter.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CurrencyRepository extends JpaRepository<Currency,Long> {
	Currency findByCurrencyShortcut(String currencyShortcut);
}
