package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

import java.math.BigDecimal;

public class AdvanceTax {

	private final BigDecimal advanceTaxRate;
	private final Money taxFreeIncome;

	public AdvanceTax(BigDecimal advanceTaxRate, Money taxFreeIncome) {
		this.advanceTaxRate = advanceTaxRate;
		this.taxFreeIncome = taxFreeIncome;
	}

	public Money getTaxFreeIncome() {
		return taxFreeIncome;
	}

	public Money calculate(Money taxedIncomeRounded) {
		return taxedIncomeRounded.percent(advanceTaxRate);
	}

	public Money reducedTax(Money advanceTax) {
		return advanceTax.subtract(taxFreeIncome);
	}

	public Money advanceTaxPaid(Money advanceTax, Money healthTaxDeductible) {
		return advanceTax.subtract(healthTaxDeductible).subtract(taxFreeIncome);
	}
}
