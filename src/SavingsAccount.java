import java.math.BigDecimal;

/**
 * A savings account which allows only for a positive account balance, and which
 * generates interest based on a specified interest rate.
 * 
 * @author sparkhound
 */
class SavingsAccount extends Account {

	private int withdrawals;
	private final static BigDecimal interestRate = new BigDecimal("1.2");
	private final static BigDecimal withdrawalInterest = new BigDecimal("2.0");

	/**
	 * The constructor.
	 * @param accountType - a string representation of this account
	 */
	public SavingsAccount(String accountType) {
		super(accountType);
	}

	/**
	 * Generates a string with account info and the interest rate.
	 * @return - the account info string
	 */
	@Override
	protected String accountWithInterestRate() {
		return super.generateInfoString() + super.toPercentStr(interestRate);
	}

	/**
	 * Generates a string with account info and the calculated interest.
	 * @return - the account info string
	 */
	@Override
	protected String accountWithInterest() {
		return super.generateInfoString() + super.calculateInterest(interestRate);
	}

	/**
	 * Subtracts an amount from the account balance if conditions are met. If more
	 * than one withdrawal has been made, withdrawal interest is paid.
	 * @param amount - the amount to withdraw
	 * @return true if a withdrawal was made, otherwise false
	 */
	@Override
	protected boolean withdraw(BigDecimal amount) {
		// if amount is zero or negative
		if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
			return false;
		}
		if (withdrawals >= 1) {
			BigDecimal withdrawalFee = amount.multiply(withdrawalInterest.movePointLeft(2));
			amount = amount.add(withdrawalFee);
		}
		// if balance is less than amount
		if (super.getBalance().compareTo(amount) < 0) {
			return false;
		}
		BigDecimal newBalance = super.getBalance().subtract(amount);
		super.setBalance(newBalance);
		super.recordTransaction(amount.negate());
		withdrawals++;
		return true;
	}

}
