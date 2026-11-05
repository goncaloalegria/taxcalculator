package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

public class TaxCalculator {

	// social security taxes
	private static final double SOCIAL_SECURITY_RATE = 9.76; // 9,76% of basis
	private static final double HEALTH_SOCIAL_SECURITY_RATE = 1.5; // 1,5% of basis
	private static final double SICKNESS_SOCIAL_SECURITY_RATE = 2.45; // 2,45% of basis
	// health-related taxes
	private static final double HEALTH_TAX_RATE = 9; // of basis up to 9%
	private static final double HEALTH_TAX_DEDUCTIBLE_RATE = 7.75; // of basis up to  7,75 %
	private static final double ADVANCE_TAX_RATE = 18; // advance tax 18%

	private final DecimalFormat noDecimals = new DecimalFormat("#");

	public TaxResult calculate(double income, ContractType contractType) {
		TaxResult result = new TaxResult();
		result.income = income;
		result.contractType = contractType;

		calculateSocialSecurity(result);
		calculateHealthTax(result);
		calculateAdvanceTax(result);
		calculateNetIncome(result);

		return result;
	}

	private void calculateSocialSecurity(TaxResult result) {
		result.socialSecurity = percentOf(result.income, SOCIAL_SECURITY_RATE);
		result.healthSocialSecurity = percentOf(result.income, HEALTH_SOCIAL_SECURITY_RATE);
		result.sicknessSocialSecurity = percentOf(result.income, SICKNESS_SOCIAL_SECURITY_RATE);
		result.incomeBasis = result.income - result.socialSecurity - result.healthSocialSecurity - result.sicknessSocialSecurity; // era o d_income
	}

	private void calculateHealthTax(TaxResult result) {
		result.healthTax = percentOf(result.incomeBasis, HEALTH_TAX_RATE); // era o soc_health1
		result.healthTaxDeductible = percentOf(result.incomeBasis, HEALTH_TAX_DEDUCTIBLE_RATE); // era o soc_health2
	}

	private void calculateAdvanceTax(TaxResult result) {
		result.taxDeductibleExpenses = result.contractType.taxDeductibleExpenses(result.incomeBasis);
		result.taxFreeIncome = result.contractType.taxFreeIncome();

		result.taxedIncome = result.incomeBasis - result.taxDeductibleExpenses;
		result.taxedIncomeRounded = roundToUnits(result.taxedIncome);
		result.advanceTax = percentOf(result.taxedIncomeRounded, ADVANCE_TAX_RATE);
		result.reducedTax = result.advanceTax - result.taxFreeIncome; // era o taxPaid
		result.advanceTaxPaid = result.advanceTax - result.healthTaxDeductible - result.taxFreeIncome;
		result.advanceTaxPaidRounded = roundToUnits(result.advanceTaxPaid);
	}

	private void calculateNetIncome(TaxResult result) {
		double socialSecurityTotal = result.socialSecurity + result.healthSocialSecurity + result.sicknessSocialSecurity;
		result.netIncome = result.income - (socialSecurityTotal + result.healthTax + result.advanceTaxPaidRounded);
	}

	private double percentOf(double value, double rate) {
		return (value * rate) / 100;
	}

	// igual ao Double.parseDouble(df.format(...)) que estava repetido no main
	private double roundToUnits(double value) {
		return Double.parseDouble(noDecimals.format(value));
	}
}
