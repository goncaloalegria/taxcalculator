package com.bartoszwalter.students.taxes;

// plain data structure: filled by Contract.calculate() and read by TaxPrinter
public class TaxResult {

	Money income;
	Money socialSecurity;
	Money healthSocialSecurity;
	Money sicknessSocialSecurity;
	Money incomeBasis;
	Money healthTax;
	Money healthTaxDeductible;
	Money taxDeductibleExpenses;
	Money taxFreeIncome;
	Money taxedIncome;
	Money taxedIncomeRounded;
	Money advanceTax;
	Money reducedTax;
	Money advanceTaxPaid;
	Money advanceTaxPaidRounded;
	Money netIncome;
}
