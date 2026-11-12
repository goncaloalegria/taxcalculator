package com.bartoszwalter.students.taxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

// money with a fixed precision of 2 decimal places, used instead of double because double has no fixed
// decimal position and accumulates small errors (e.g. 690.3199999999999 instead of 690.32)
public final class Money {

	private static final int SCALE = 2;
	private static final RoundingMode ROUNDING = RoundingMode.HALF_UP;
	private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

	public static final Money ZERO = Money.of("0"); // must stay below ROUNDING, otherwise ROUNDING is still null when ZERO is created

	private final BigDecimal amount;

	private Money(BigDecimal amount) {

		this.amount = amount.setScale(SCALE, ROUNDING);
	}

	// takes a String and not a double, so the binary error of double never gets into the BigDecimal
	public static Money of(String amount) {

		return new Money(new BigDecimal(amount.trim()));
	}

	public Money add(Money other) {

		return new Money(amount.add(other.amount));
	}

	public Money subtract(Money other) {

		return new Money(amount.subtract(other.amount));
	}

	public Money percent(BigDecimal rate) {

		return new Money(amount.multiply(rate).divide(ONE_HUNDRED));
	}

	public Money roundToUnits() {

		return new Money(amount.setScale(0, ROUNDING));
	}

	public String toUnitsString() {

		return amount.setScale(0, ROUNDING).toPlainString();
	}

	@Override
	public String toString() {

		return amount.toPlainString();
	}
}
