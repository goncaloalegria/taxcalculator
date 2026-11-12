package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

// some contracts deduct a fixed amount and others a percentage of the income basis, so this block has two implementations
public interface DeductibleExpenses {

	Money calculate(Money incomeBasis);
}
