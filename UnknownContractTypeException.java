package com.bartoszwalter.students.taxes;

public class UnknownContractTypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownContractTypeException(char code) {
		super("Unknown type of contract: " + code);
	}
}
