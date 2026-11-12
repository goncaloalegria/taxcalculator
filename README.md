# taxcalculator
TaxCalculator - a kata for a Clean Code exercise

Authors:
Gonçalo Alegria (ER-2317) 
Rodrigo Nascimento (ER-2319)

Each change has a description of what was done and a justification of why it was done. The Clean Code rule applied is in brackets.

## Part 1 - Fixing the code so it compiles and calculates correctly

1st Change - deleted a variable that was declared twice in the static fields.
Why: the code did not compile with a duplicated declaration.

2nd Change - renamed advanceTaxPaidadvanceTax to advanceTaxPaid (in the declaration and in calculateAdvanceTax()).
Why: the code was using advanceTaxPaid, which did not exist, so it did not compile. The name was a copying mistake.

3rd Change - changed soc_sick_secur to soc_sick_security in calculateIncome().
Why: soc_sick_secur did not exist, the variable had the wrong name and the code did not compile.

4th Change - in the C branch, income was declared again and used to calculate itself; changed it to d_income like in the E branch.
Why: declaring income again hid the static field and used a variable before it had a value, so it did not compile.

5th Change - in the C branch, replaced a useless advanceTax variable with taxPaid.
Why: the next line used taxPaid, which was only declared in the E branch, so the C branch did not compile. The advanceTax variable was never used.

6th Change - d_income (income minus social security) was calculated but never used; now it is the basis for the health tax, the taxed income and the C deductible expenses (20%).
Why: the health tax and the income tax must be calculated on the income after social security, not on the gross income. The message "Income basis for health social security" already said this, but it printed the gross income, so the results were wrong.

## Part 2 - Clean Code refactoring (the output stays the same as after the 6th change)

7th Change - separated the calculations from the outputs: one part only does the math and returns a TaxResult, TaxPrinter only prints (Do one thing).
Why: before, every line mixed a calculation with a print, so it was impossible to change how the result is shown without risking breaking the math, and the math could not be reused or tested without the console.

8th Change - the E and C branches had the same math copied; now there is only one calculation (Needless repetition).
Why: with copied code, a fix in one branch had to be remembered in the other. The 4th and 5th changes were exactly bugs that existed only in one of the copies.

9th Change - replaced the magic numbers (9.76, 1.5, 2.45, 9, 7.75, 18, 20, 111.25, 46.33) with named constants (Replace magic numbers with named constants).
Why: a number alone does not say what it is; a name like ADVANCE_TAX_RATE explains itself, can be searched for and is changed in only one place.

10th Change - removed the static global variables that were changed during the calculations; the C branch was changing taxFreeIncome and taxDeductibleExpenses (Have no side effects).
Why: after running C, the global values stayed changed, so a later calculation could use the wrong values. The result depended on what ran before.

11th Change - renamed to descriptive names: d_income -> incomeBasis, soc_health1 -> healthTax, soc_health2 -> healthTaxDeductible, taxPaid -> reducedTax, df00 -> twoDecimals, df -> noDecimals, calculateIncome -> calculateSocialSecurity, calculateOtherTaxes -> calculateHealthTax (Choose descriptive and unambiguous names, Avoid encodings).
Why: the old names lied or said nothing: calculateIncome calculated social security, taxPaid was printed as "Reduced tax", and prefixes like d_ and soc_ are encodings that carry no meaning.

12th Change - each class in its own file: Main (input), TaxResult (values), TaxPrinter (outputs), UnknownContractTypeException, and the calculation classes.
Why: one file with everything had many responsibilities; with one class per file each one has a single reason to change and is easier to find and read.

13th Change - the char 'E'/'C' passed to the calculation became a type that knows its own deductible expenses and tax free income, so the calculation does not need if/else (Don't use flag arguments, Prefer polymorphism to if/else).
Why: a flag argument means the method does more than one thing, and every new contract type would add another if/else in the middle of the math.

14th Change - the try-catch stays alone in main() and the work is in run(); an unknown contract type now throws UnknownContractTypeException.
Why: according to Clean Code, a try-catch is a separate task and should be extracted. An exception is clearer than checking a special value and cannot be ignored.

15th Change - the calculation was split into small methods: calculateSocialSecurity, calculateHealthInsurance, calculateAdvanceTax, calculateNetIncome, in the order they are called (Small, Place functions in the downward direction).
Why: the single method was longer than one screen. With small methods with descriptive names, the main method reads like a list of steps, from the general to the details.

16th Change - instance methods instead of static ones (Prefer non-static methods to static methods).
Why: static methods cannot be replaced or extended. With objects, the 20th change (one class per contract) became possible.

## Part 3 - Fixed precision and a new architecture (the results change by at most 0.01)

17th Change - replaced double with fixed precision: the new Money class keeps money in a BigDecimal, always with 2 decimal places. Values are created from a String (Money.of("46.33")), not from a double (Prefer dedicated value objects to primitive type).
Why: double is floating point, so it has no fixed decimal position and many decimal values have no exact binary representation (like 1/3 in decimal is 0.333...). Small errors appeared and accumulated: with 1000 and contract C the output was "income to be taxed = 690.3199999999999" instead of 690.32. Money has a fixed precision of 2 decimal places, like real money. Creating it from a String avoids bringing the double error into the BigDecimal.

18th Change - rounding changed from HALF_EVEN to HALF_UP.
Why: the old rounding came by default from DecimalFormat and rounded .5 to the even number (4202.5 -> 4202, 4203.5 -> 4204). In tax calculations .5 is normally rounded up. Now the rounding mode is explicit in one place (Money) instead of hidden in a formatter.

19th Change - contracts are now built from blocks, in the blocks/ folder: SocialSecurity, HealthInsurance, DeductibleExpenses (with the versions FixedDeductibleExpenses and PercentageDeductibleExpenses) and AdvanceTax.
Why: every contract is made of the same parts, only the values change. Each block does one thing, receives its values from the contract that uses it, and can be used and tested separately. If a part of the calculation changes, only that block changes.

20th Change - created the abstract superclass Contract with the calculation (the same for every contract) and the subclasses EmploymentContract and CivilContract, which only choose the blocks and their values. The old ContractType enum and TaxCalculator class were deleted (Prefer polymorphism to if/else, Base class should know nothing about their derivatives).
Why: the architecture is now ready for new contracts and for changes in a single contract. The values are repeated in the two subclasses on purpose: this way one contract can change (for example a different rate) without touching the other.

21st Change - ContractFactory creates the contract from the letter (E or C).
Why: the switch on the letter is in only one place, as Clean Code suggests for switch statements (convert it to a factory). To add a new contract you only need a new subclass of Contract with its blocks and values, one case in ContractFactory and one print method in TaxPrinter, without changing the existing contracts.

22nd Change - TaxPrinter no longer has a switch; each contract calls its own print. All money values are printed with 2 decimal places.
Why: the switch on the contract type is replaced by polymorphism. Before, some values were printed as 4314.5 or 2876.330457 and zero as ".00", now the output is consistent.

## Tests

Parts 2 and 3 were tested with 10 incomes for E and C, an unknown contract type and an invalid income. In Part 2 the output is identical to the output after the 6th change. In Part 3 the results change by at most 0.01, because every value is now rounded to 2 decimal places like real money.

