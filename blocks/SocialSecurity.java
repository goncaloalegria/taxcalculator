package com.bartoszwalter.students.taxes.blocks;

import com.bartoszwalter.students.taxes.Money;

import java.math.BigDecimal;

public class SocialSecurity {

	private final BigDecimal socialSecurityRate;
	private final BigDecimal healthSocialSecurityRate;
	private final BigDecimal sicknessSocialSecurityRate;

	public SocialSecurity(BigDecimal socialSecurityRate, BigDecimal healthSocialSecurityRate, BigDecimal sicknessSocialSecurityRate) {
		this.socialSecurityRate = socialSecurityRate;
		this.healthSocialSecurityRate = healthSocialSecurityRate;
		this.sicknessSocialSecurityRate = sicknessSocialSecurityRate;
	}

	public Money socialSecurityTax(Money income) {
		return income.percent(socialSecurityRate);
	}

	public Money healthSocialSecurityTax(Money income) {
		return income.percent(healthSocialSecurityRate);
	}

	public Money sicknessSocialSecurityTax(Money income) {
		return income.percent(sicknessSocialSecurityRate);
	}
}
