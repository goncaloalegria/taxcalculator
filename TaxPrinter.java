package com.bartoszwalter.students.taxes;

// the labels are kept exactly as in the original program and they are different for each contract,
// that is why there is one print method per contract
public class TaxPrinter {

	void printEmployment(TaxResult result) {
		System.out.println("EMPLOYMENT");
		System.out.println("Income " + result.income);
		System.out.println("Social security tax " + result.socialSecurity);
		System.out.println("Health social security tax    " + result.healthSocialSecurity);
		System.out.println("Sickness social security tax  " + result.sicknessSocialSecurity);
		System.out.println("Income basis for health social security: " + result.incomeBasis);
		System.out.println("Health social security tax: 9% = " + result.healthTax + " 7,75% = " + result.healthTaxDeductible);
		System.out.println("Tax deductible expenses " + result.taxDeductibleExpenses);
		System.out.println("income " + result.taxedIncome + " rounded " + result.taxedIncomeRounded.toUnitsString());
		System.out.println("Advance tax 18 % = " + result.advanceTax);
		System.out.println("Tax free income = " + result.taxFreeIncome);
		System.out.println("Reduced tax = " + result.reducedTax);
		System.out.println("Advance tax paid = " + result.advanceTaxPaid + " rounded = " + result.advanceTaxPaidRounded.toUnitsString());
		System.out.println();
		System.out.println("Net income = " + result.netIncome);
	}

	void printCivil(TaxResult result) {
		System.out.println("income " + result.income);
		System.out.println("Social security tax = " + result.socialSecurity);
		System.out.println("Health social security tax = " + result.healthSocialSecurity);
		System.out.println("Sickness social security tax = " + result.sicknessSocialSecurity);
		System.out.println("Income for calculating health security tax: " + result.incomeBasis);
		System.out.println("Health security tax: 9% = " + result.healthTax + " 7,75% = " + result.healthTaxDeductible);
		System.out.println("Tax deductible expenses = " + result.taxDeductibleExpenses);
		System.out.println("income to be taxed = " + result.taxedIncome + " rounded = " + result.taxedIncomeRounded.toUnitsString());
		System.out.println("Advance tax 18 % = " + result.advanceTax);
		System.out.println("Already paid tax = " + result.reducedTax);
		System.out.println("Advance tax  = " + result.advanceTaxPaid + " rounded = " + result.advanceTaxPaidRounded.toUnitsString());
		System.out.println();
		System.out.println("Net income = " + result.netIncome);
	}
}
