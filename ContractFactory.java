package com.bartoszwalter.students.taxes;

// the only place that maps the letter typed by the user to a contract, a new contract only needs a new case here
public class ContractFactory {

	public static Contract fromCode(char code) {
		switch (code) {
			case 'E':
				return new EmploymentContract();
			case 'C':
				return new CivilContract();
			default:
				throw new UnknownContractTypeException(code);
		}
	}
}
