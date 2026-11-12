package com.bartoszwalter.students.taxes;

import java.text.DecimalFormat;

// prints que estavam no main, os textos ficaram iguais
public class TaxPrinter {

	private final DecimalFormat twoDecimals = new DecimalFormat("#.00");
	private final DecimalFormat noDecimals = new DecimalFormat("#");

	public void print(TaxResult result) {
		switch (result.contractType) {
			case EMPLOYMENT:
				printEmployment(result);
				break;
			case CIVIL:
				printCivil(result);
				break;
		}
	}

	private void printEmployment(TaxResult result) {
		System.out.println("EMPLOYMENT");
		System.out.println("Income " + result.income);
		System.out.println("Social security tax " + twoDecimals.format(result.socialSecurity));
		System.out.println("Health social security tax    " + twoDecimals.format(result.healthSocialSecurity));
		System.out.println("Sickness social security tax  " + twoDecimals.format(result.sicknessSocialSecurity));
		System.out.println("Income basis for health social security: " + result.incomeBasis);
		System.out.println("Health social security tax: 9% = " + twoDecimals.format(result.healthTax) + " 7,75% = " + twoDecimals.format(result.healthTaxDeductible));
		System.out.println("Tax deductible expenses " + result.taxDeductibleExpenses);
		System.out.println("income " + result.taxedIncome + " rounded " + noDecimals.format(result.taxedIncomeRounded));
		System.out.println("Advance tax 18 % = " + result.advanceTax);
		System.out.println("Tax free income = " + result.taxFreeIncome);
		System.out.println("Reduced tax = " + twoDecimals.format(result.reducedTax));
		System.out.println("Advance tax paid = " + twoDecimals.format(result.advanceTaxPaid) + " rounded = " + noDecimals.format(result.advanceTaxPaidRounded));
		System.out.println();
		System.out.println("Net income = " + twoDecimals.format(result.netIncome));
	}

	private void printCivil(TaxResult result) {
		System.out.println("income " + result.income);
		System.out.println("Social security tax = " + twoDecimals.format(result.socialSecurity));
		System.out.println("Health social security tax = " + twoDecimals.format(result.healthSocialSecurity));
		System.out.println("Sickness social security tax = " + twoDecimals.format(result.sicknessSocialSecurity));
		System.out.println("Income for calculating health security tax: " + result.incomeBasis);
		System.out.println("Health security tax: 9% = " + twoDecimals.format(result.healthTax) + " 7,75% = " + twoDecimals.format(result.healthTaxDeductible));
		System.out.println("Tax deductible expenses = " + result.taxDeductibleExpenses);
		System.out.println("income to be taxed = " + result.taxedIncome + " rounded = " + noDecimals.format(result.taxedIncomeRounded));
		System.out.println("Advance tax 18 % = " + result.advanceTax);
		System.out.println("Already paid tax = " + twoDecimals.format(result.reducedTax));
		System.out.println("Advance tax  = " + twoDecimals.format(result.advanceTaxPaid) + " rounded = " + noDecimals.format(result.advanceTaxPaidRounded));
		System.out.println();
		System.out.println("Net income = " + twoDecimals.format(result.netIncome));
	}
}
