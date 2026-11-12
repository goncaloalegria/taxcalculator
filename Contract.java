package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.taxes.blocks.AdvanceTax;
import com.bartoszwalter.students.taxes.blocks.DeductibleExpenses;
import com.bartoszwalter.students.taxes.blocks.HealthInsurance;
import com.bartoszwalter.students.taxes.blocks.SocialSecurity;

// every contract uses the same blocks and the same calculation, a subclass only chooses the blocks and their values
public abstract class Contract {

	private final SocialSecurity socialSecurity;
	private final HealthInsurance healthInsurance;
	private final DeductibleExpenses deductibleExpenses;
	private final AdvanceTax advanceTax;

	protected Contract(SocialSecurity socialSecurity, HealthInsurance healthInsurance, DeductibleExpenses deductibleExpenses, AdvanceTax advanceTax) {
		this.socialSecurity = socialSecurity;
		this.healthInsurance = healthInsurance;
		this.deductibleExpenses = deductibleExpenses;
		this.advanceTax = advanceTax;
	}

	public abstract void print(TaxResult result, TaxPrinter printer);

	public TaxResult calculate(Money income) {
		TaxResult result = new TaxResult();
		result.income = income;

		calculateSocialSecurity(result);
		calculateHealthInsurance(result);
		calculateAdvanceTax(result);
		calculateNetIncome(result);

		return result;
	}

	private void calculateSocialSecurity(TaxResult result) {
		result.socialSecurity = socialSecurity.socialSecurityTax(result.income);
		result.healthSocialSecurity = socialSecurity.healthSocialSecurityTax(result.income);
		result.sicknessSocialSecurity = socialSecurity.sicknessSocialSecurityTax(result.income);
		result.incomeBasis = result.income.subtract(result.socialSecurity).subtract(result.healthSocialSecurity).subtract(result.sicknessSocialSecurity);
	}

	private void calculateHealthInsurance(TaxResult result) {
		result.healthTax = healthInsurance.healthTax(result.incomeBasis);
		result.healthTaxDeductible = healthInsurance.healthTaxDeductible(result.incomeBasis);
	}

	private void calculateAdvanceTax(TaxResult result) {
		result.taxDeductibleExpenses = deductibleExpenses.calculate(result.incomeBasis);
		result.taxFreeIncome = advanceTax.getTaxFreeIncome();

		result.taxedIncome = result.incomeBasis.subtract(result.taxDeductibleExpenses);
		result.taxedIncomeRounded = result.taxedIncome.roundToUnits();
		result.advanceTax = advanceTax.calculate(result.taxedIncomeRounded);
		result.reducedTax = advanceTax.reducedTax(result.advanceTax);
		result.advanceTaxPaid = advanceTax.advanceTaxPaid(result.advanceTax, result.healthTaxDeductible);
		result.advanceTaxPaidRounded = result.advanceTaxPaid.roundToUnits();
	}

	private void calculateNetIncome(TaxResult result) {
		Money socialSecurityTotal = result.socialSecurity.add(result.healthSocialSecurity).add(result.sicknessSocialSecurity);
		Money totalDeductions = socialSecurityTotal.add(result.healthTax).add(result.advanceTaxPaidRounded);
		result.netIncome = result.income.subtract(totalDeductions);
	}
}
