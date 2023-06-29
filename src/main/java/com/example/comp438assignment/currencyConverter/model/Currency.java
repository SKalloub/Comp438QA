package com.example.comp438assignment.currencyConverter.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "currency")
public class Currency {
	@Id
	@Column
	private String currencyId;

	@Column
	private String currencyName;

	@Column
	private String currencyShortcut;
}
