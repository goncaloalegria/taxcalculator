package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		try {
			run();
		} catch (UnknownContractTypeException ex) {
			System.out.println("Unknown type of contract!");
		} catch (Exception ex) {
			System.out.println("Incorrect");
			System.err.println(ex);
		}
	}

	private static void run() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Money income = readIncome(reader);
		Contract contract = readContract(reader);

		TaxResult result = contract.calculate(income);
		contract.print(result, new TaxPrinter());
	}

	private static Money readIncome(BufferedReader reader) throws IOException {
		System.out.print("Enter income: ");
		return Money.of(reader.readLine());
	}

	private static Contract readContract(BufferedReader reader) throws IOException {
		System.out.print("Contract Type: (E)mployment, (C)ivil: ");
		return ContractFactory.fromCode(reader.readLine().charAt(0));
	}
}
