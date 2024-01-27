import java.util.ArrayList;

/**
 * A bank customer with various attributes and ways to store and handle accounts.
 * 
 * @author sparkhound
 */
class Customer {

	private String name, surname;
	private String pNo;
	private ArrayList<Account> accounts;

	/**
	 * The constructor
	 * @param name    - the first name of this customer
	 * @param surname - the last name of this customer
	 * @param pNo     - the personal number of this customer
	 */
	public Customer(String name, String surname, String pNo) {
		this.name = name;
		this.surname = surname;
		this.pNo = pNo;
		this.accounts = new ArrayList<Account>();
	}

	/**
	 * Creates an array with a customer info string at index 0, followed by accounts
	 * info (one string per index, accessed polymophically through superclass
	 * Account)
	 * @param calculateInterest - a string which decides whether to calculate
	 *                          interest
	 * @return customerInfoAndAccounts - the array with customer/account info to
	 *         return
	 */
	ArrayList<String> customerAndAccountsInfo(String interestOrInterestRate) {
		ArrayList<String> customerInfoAndAccounts = new ArrayList<String>();
		customerInfoAndAccounts.add(makeCustomerInfoStr());
		for (Account i : accounts) {			
			if (interestOrInterestRate == "interest") {
				customerInfoAndAccounts.add(i.accountWithInterest());
			} else {
				customerInfoAndAccounts.add(i.accountWithInterestRate());
			}
		}
		return customerInfoAndAccounts;
	}

	/**
	 * Creates a string with information about personal number, name and last name.
	 * @return this customer info string
	 */
	String makeCustomerInfoStr() {
		return pNo + " " + name + " " + surname;
	}

	/**
	 * Sets the first name of this customer.
	 * @param firstName - the name to set
	 */
	void setName(String firstName) {
		this.name = firstName;
	}

	/**
	 * Sets the last name of this customer.
	 * @param lastName - the last name to set
	 */
	void setSurname(String lastName) {
		this.surname = lastName;
	}

	/**
	 * Gets the personal number of this customer.
	 * @return pNo - the number string
	 */
	String getPNo() {
		return pNo;
	}

	/**
	 * Gets the specified account of a customer.
	 * @param accountId - the personal number of this customer
	 * @return the account
	 */
	Account getAccount(int accountId) {
		for (Account i : accounts) {
			if (i.getAccountId() == accountId) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Adds an account to the accounts array.
	 * @param account - the added account
	 */
	void addAccount(Account account) {
		this.accounts.add(account);
	}

	/**
	 * Removes the account with the specified number from the accounts array.
	 * @param accountId - the personal number of the customer
	 */
	void removeAccount(int accountId) {
		accounts.remove(getAccount(accountId));
	}

}
