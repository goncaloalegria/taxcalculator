package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

import java.math.BigDecimal;

public class HealthInsurance {

	private final BigDecimal healthTaxRate;
	private final BigDecimal healthTaxDeductibleRate;

	public HealthInsurance(BigDecimal healthTaxRate, BigDecimal healthTaxDeductibleRate) {
		this.healthTaxRate = healthTaxRate;
		this.healthTaxDeductibleRate = healthTaxDeductibleRate;
	}

	public Money healthTax(Money incomeBasis) {
		return incomeBasis.percent(healthTaxRate);
	}

	public Money healthTaxDeductible(Money incomeBasis) {
		return incomeBasis.percent(healthTaxDeductibleRate);
	}
}
