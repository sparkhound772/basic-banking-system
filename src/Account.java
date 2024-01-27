import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * An abstract bank account class with various attributes relating to money and transactions.
 * 
 * @author sparkhound
 */
abstract class Account {

	private String accountType;
	private BigDecimal balance;
	private int accountId;
	private ArrayList<String> transactions;
	private static int lastAssignedNum = 1001;

	/**
	 * The constructor
	 * @param accountType - a string representation of this account
	 */
	public Account(String accountType) {
		this.balance = new BigDecimal("0");
		this.accountType = accountType;
		this.transactions = new ArrayList<String>();
		this.accountId = lastAssignedNum;
		lastAssignedNum++;
	}

	/**
	 * To be implemented with the relevant interest rates for the class that
	 * inherits.
	 */
	protected abstract String accountWithInterestRate();

	/**
	 * To be implemented with the relevant interest rates for the class that
	 * inherits.
	 */
	protected abstract String accountWithInterest();

	/**
	 * Generates a string with account info.
	 * @return - the account info string
	 */
	protected String generateInfoString() {
		return this.accountId + " " + toCurrencyStr(this.balance) + " " + this.accountType + " ";
	}

	/**
	 * Calculates interest from interest rate and balance.
	 * @param interestRate - a percentage
	 * @return a string with the interest
	 */
	protected String calculateInterest(BigDecimal interestRate) {
		BigDecimal divideBy = BigDecimal.valueOf(100);
		BigDecimal interest = getBalance().multiply(interestRate).divide(divideBy);
		return toCurrencyStr(interest);
	}

	/**
	 * Formats a string with numbers to the local currency.
	 * @param format - the string to format
	 * @return a string with currency format
	 */
	private String toCurrencyStr(BigDecimal format) {
		return NumberFormat.getCurrencyInstance(new Locale("sv", "SE")).format(format);
	}

	/**
	 * Formats a BigDecimal to percent format.
	 * @param interestRate - the interest rate to format
	 * @return a string with percentage format
	 */
	protected String toPercentStr(BigDecimal interestRate) {
		NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv", "SE"));
		percentFormat.setMaximumFractionDigits(3);
		String percentStr = percentFormat.format(interestRate.movePointLeft(2));
		return percentStr;
	}

	/**
	 * Adds an int amount to the BigDecimal account balance.
	 * @param amount - the amount to deposit
	 * @return true if a deposit was made, otherwise false
	 */
	boolean deposit(BigDecimal amount) {
		// if amount is zero or negative
		if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
			return false;
		}
		this.balance = this.balance.add(amount);
		recordTransaction(amount);
		return true;
	}

	/**
	 * Subtracts an amount from this accounts balance.
	 * To be implemented by the class that inherits based on it's specific conditions.
	 * @param amount - the amount to withdraw
	 */
	protected abstract boolean withdraw(BigDecimal amount);

	/**
	 * Adds a string with information about a transaction for this account, to the
	 * transactions array list.
	 * @param amount
	 */
	protected void recordTransaction(BigDecimal amount) {
		this.transactions.add(dateOfEvent() + " " + toCurrencyStr(amount) + " Saldo: " + toCurrencyStr(balance));
	}

	/**
	 * Captures the time and date in a specified format.
	 * @return the string with the time and date
	 */
	private String dateOfEvent() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * Sets the account balance.
	 * @param balance - the balance to set
	 */
	protected void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * Gets the account balance.
	 * @return balance - the balance of this account
	 */
	protected BigDecimal getBalance() {
		return balance;
	}

	/**
	 * Gets the identifying number of this account.
	 * @return accountId - the identifying number of this account
	 */
	int getAccountId() {
		return accountId;
	}

	/**
	 * Gets the array list with the strings presenting the transactions made for
	 * this account.
	 * @return the transactions array list
	 */
	ArrayList<String> getTransactions() {
		return this.transactions;
	}

}
