package com.bartoszwalter.students.taxes;

// guarda os valores das contas para o TaxPrinter imprimir
public class TaxResult {

	ContractType contractType;
	double income;
	double socialSecurity;
	double healthSocialSecurity;
	double sicknessSocialSecurity;
	double incomeBasis;
	double healthTax;
	double healthTaxDeductible;
	double taxDeductibleExpenses;
	double taxFreeIncome;
	double taxedIncome;
	double taxedIncomeRounded;
	double advanceTax;
	double reducedTax;
	double advanceTaxPaid;
	double advanceTaxPaidRounded;
	double netIncome;
}
