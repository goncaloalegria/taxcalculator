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
		double income = readIncome(reader);
		ContractType contractType = readContractType(reader);

		TaxResult result = new TaxCalculator().calculate(income, contractType);
		new TaxPrinter().print(result);
	}

	private static double readIncome(BufferedReader reader) throws IOException {
		System.out.print("Enter income: ");
		return Double.parseDouble(reader.readLine());
	}

	private static ContractType readContractType(BufferedReader reader) throws IOException {
		System.out.print("Contract Type: (E)mployment, (C)ivil: ");
		return ContractType.fromCode(reader.readLine().charAt(0));
	}
}
