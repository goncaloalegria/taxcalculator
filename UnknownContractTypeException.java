package com.bartoszwalter.students.taxes;

public class UnknownContractTypeException extends RuntimeException {

	public UnknownContractTypeException(char code) {
		super("Unknown type of contract: " + code);
	}
}
