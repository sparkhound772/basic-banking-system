import java.math.BigDecimal;

/**
 * A credit account with a specified credit limit, and different interest rates
 * depending on whether the account balance is positive or negative.
 * 
 * @author sparkhound
 *
 */
class CreditAccount extends Account {

	private final static BigDecimal interestRate = new BigDecimal("0.5");
	private final static BigDecimal creditInterestRate = new BigDecimal("7.0");
	BigDecimal creditLimit;

	/**
	 * The constructor.
	 * @param accountType - a string representation of this account
	 */
	public CreditAccount(String accountType) {
		super(accountType);
		this.creditLimit = new BigDecimal("5000");
	}

	/**
	 * Generates a string with account info and the interest rate or credit interest
	 * rate if this account has debt.
	 * @return - the account info string
	 */
	@Override
	protected String accountWithInterestRate() {
		if (hasDebt()) {
			return super.generateInfoString() + super.toPercentStr(creditInterestRate);
		}
		return super.generateInfoString() + super.toPercentStr(interestRate);
	}

	/**
	 * Generates a string with account info and the calculated interest or credit
	 * interest if this account has debt.
	 * @return - the account info string
	 */
	@Override
	protected String accountWithInterest() {
		if (hasDebt()) {
			return super.generateInfoString() + super.calculateInterest(creditInterestRate);
		}
		return super.generateInfoString() + super.calculateInterest(interestRate);
	}

	/**
	 * Checks whether this account has debt.
	 * @return true if there's debt, otherwise false
	 */
	private boolean hasDebt() {
		if (this.getBalance().compareTo(BigDecimal.valueOf(0)) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * Subtracts an amount from this account if the conditions are met.
	 * @param amount - the amount to withdraw
	 * @return true if withdrawal was made, otherwise false
	 */
	@Override
	protected boolean withdraw(BigDecimal amount) {
		// if amount is zero or negative
		if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
			return false;
		}
		BigDecimal newBalance = super.getBalance().subtract(amount);
		// if the new balance will be less than the negative credit limit
		if (newBalance.compareTo(creditLimit.negate()) < 0) {
			return false;
		}
		super.setBalance(newBalance);
		super.recordTransaction(amount.negate());
		return true;
	}

}
