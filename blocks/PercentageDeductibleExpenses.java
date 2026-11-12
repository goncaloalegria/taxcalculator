package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

import java.math.BigDecimal;

public class PercentageDeductibleExpenses implements DeductibleExpenses {

	private final BigDecimal rate;

	public PercentageDeductibleExpenses(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public Money calculate(Money incomeBasis) {
		return incomeBasis.percent(rate);
	}
}
