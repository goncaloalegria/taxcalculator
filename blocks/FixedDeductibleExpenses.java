package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

public class FixedDeductibleExpenses implements DeductibleExpenses {

	private final Money amount;

	public FixedDeductibleExpenses(Money amount) {
		this.amount = amount;
	}

	@Override
	public Money calculate(Money incomeBasis) {
		return amount;
	}
}
