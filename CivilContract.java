package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.blocks.AdvanceTax;
import com.bartoszwalter.students.taxes.blocks.HealthInsurance;
import com.bartoszwalter.students.taxes.blocks.PercentageDeductibleExpenses;
import com.bartoszwalter.students.taxes.blocks.SocialSecurity;

import java.math.BigDecimal;

public class CivilContract extends Contract {

	// social security, % of the gross income
	private static final BigDecimal SOCIAL_SECURITY_RATE = new BigDecimal("9.76");
	private static final BigDecimal HEALTH_SOCIAL_SECURITY_RATE = new BigDecimal("1.5");
	private static final BigDecimal SICKNESS_SOCIAL_SECURITY_RATE = new BigDecimal("2.45");
	// health insurance, % of the income basis (income minus social security)
	private static final BigDecimal HEALTH_TAX_RATE = new BigDecimal("9");
	private static final BigDecimal HEALTH_TAX_DEDUCTIBLE_RATE = new BigDecimal("7.75");
	// advance tax
	private static final BigDecimal ADVANCE_TAX_RATE = new BigDecimal("18"); // % of the taxed income
	private static final BigDecimal DEDUCTIBLE_EXPENSES_RATE = new BigDecimal("20"); // % of the income basis
	private static final Money TAX_FREE_INCOME = Money.ZERO;

	public CivilContract() {
		super(new SocialSecurity(SOCIAL_SECURITY_RATE, HEALTH_SOCIAL_SECURITY_RATE, SICKNESS_SOCIAL_SECURITY_RATE),
				new HealthInsurance(HEALTH_TAX_RATE, HEALTH_TAX_DEDUCTIBLE_RATE),
				new PercentageDeductibleExpenses(DEDUCTIBLE_EXPENSES_RATE),
				new AdvanceTax(ADVANCE_TAX_RATE, TAX_FREE_INCOME));
	}

	@Override
	public void print(TaxResult result, TaxPrinter printer) {

		printer.printCivil(result);
	}
}
