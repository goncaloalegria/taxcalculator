package com.bartoszwalter.students.taxes;

// cada contrato sabe as suas despesas dedutiveis e o rendimento isento, assim o calculate nao precisa de if/else
public enum ContractType {

	EMPLOYMENT('E') {
		@Override
		public double taxDeductibleExpenses(double incomeBasis) {
			return EMPLOYMENT_DEDUCTIBLE_EXPENSES;
		}

		@Override
		public double taxFreeIncome() {
			return EMPLOYMENT_TAX_FREE_INCOME;
		}
	},

	CIVIL('C') {
		@Override
		public double taxDeductibleExpenses(double incomeBasis) {
			return (incomeBasis * CIVIL_DEDUCTIBLE_EXPENSES_RATE) / 100;
		}

		@Override
		public double taxFreeIncome() {
			return 0;
		}
	};

	private static final double EMPLOYMENT_DEDUCTIBLE_EXPENSES = 111.25;
	private static final double EMPLOYMENT_TAX_FREE_INCOME = 46.33; // tax-free income monthly 46,33 PLN
	private static final double CIVIL_DEDUCTIBLE_EXPENSES_RATE = 20; // 20% of basis

	private final char code;

	ContractType(char code) {
		this.code = code;
	}

	public abstract double taxDeductibleExpenses(double incomeBasis);

	public abstract double taxFreeIncome();

	public static ContractType fromCode(char code) {
		for (ContractType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		throw new UnknownContractTypeException(code);
	}
}
