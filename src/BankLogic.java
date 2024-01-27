import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Handles customers and accounts and makes operations on them.
 * 
 * @author sparkhound
 */
public class BankLogic {

	private ArrayList<Customer> customers;

	/**
	 * The constructor.
	 */
	public BankLogic() {
		this.customers = new ArrayList<Customer>();
	}

	/**
	 * Adds a new customer to this bank.
	 * @param name    - the first name of the new customer
	 * @param surname - the last name of the new customer
	 * @param pNo     - the personal number of the new customer
	 * @return true if the creation was successful
	 */
	public boolean createCustomer(String name, String surname, String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer == null) {
			customer = new Customer(name, surname, pNo);
			customers.add(customer);
			return true;
		}
		return false;
	}

	/**
	 * Searches for the specified customer.
	 * @param pNo - the personal number of the customer
	 * @return the customer if found, otherwise null
	 */
	private Customer findCustomer(String pNo) {
		for (Customer i : customers) {
			if (i.getPNo() == pNo) {
				return i;
			}
		}
		return null;
	}

	/**
	 * Gets an array with information about specified customer.
	 * @param pNo - the personal number of the customer
	 * @return the customer info array if found, otherwise null
	 */
	public ArrayList<String> getCustomer(String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer == null) {
			return null;

		}
		return customer.customerAndAccountsInfo("interest rate");
	}

	/**
	 * Gets an array with information about all the customers of the bank.
	 * @return customerNamesPNums - array with customer info strings
	 */
	public ArrayList<String> getAllCustomers() {
		ArrayList<String> customerNamesPNums = new ArrayList<String>();
		for (Customer i : customers) {
			customerNamesPNums.add(i.makeCustomerInfoStr());
		}
		return customerNamesPNums;
	}

	/**
	 * Changes the names of a specified customer.
	 * @param name    - the first name of the customer
	 * @param surname - the last name of the customer
	 * @param pNo     - the personal number of the customer
	 * @return true or false depending on whether the customer was found and other
	 *         conditions
	 */
	public boolean changeCustomerName(String name, String surname, String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer != null) {
			if (name.isEmpty() && !surname.isEmpty()) {
				customer.setSurname(surname);
				return true;
			}
			if (!name.isEmpty() && surname.isEmpty()) {
				customer.setName(name);
				return true;
			}
			if (!surname.isEmpty() && !surname.isEmpty()) {
				customer.setName(name);
				customer.setSurname(surname);
				return true;
			}
			if (name.isEmpty() && surname.isEmpty()) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Creates a savings account of a specified customer.
	 * @param pNo - the personal number of the customer
	 * @return the account number if the customer was found, otherwise -1
	 */
	public int createSavingsAccount(String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer == null) {
			return -1;
		}
		SavingsAccount account = new SavingsAccount("Sparkonto");
		customer.addAccount(account);
		return account.getAccountId();
	}

	/**
	 * Creates a credit account of a specified customer.
	 * @param pNo - the personal number of the customer
	 * @return the account number if the customer was found, otherwise -1
	 */
	public int createCreditAccount(String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer == null) {
			return -1;
		}
		CreditAccount account = new CreditAccount("Kreditkonto");
		customer.addAccount(account);
		return account.getAccountId();
	}

	/**
	 * Gets a string with information about a specified account.
	 * @param pNo       - the personal number of the customer
	 * @param accountId - the account number
	 * @return the string with the account info
	 */
	public String getAccount(String pNo, int accountId) {
		Customer customer = findCustomer(pNo);
		Account account = findAccount(customer, accountId);
		if (account == null) {
			return null;
		}
		return account.accountWithInterestRate();
	}

	/**
	 * Finds a specified account.
	 * @param customer  - the owner of the account
	 * @param accountId - the account number
	 * @return the account if found, otherwise null
	 */
	private Account findAccount(Customer customer, int accountId) {
		if (customer == null) {
			return null;
		}
		return customer.getAccount(accountId);		
	}

	/**
	 * Transfers a specified amount to a specified account.
	 * @param pNo       - the personal number of the customer
	 * @param accountId - the account number
	 * @param amount    - the amount to transfer
	 * @return true if account was found and amount was big enough
	 */
	public boolean deposit(String pNo, int accountId, int amount) {
		Customer customer = findCustomer(pNo);
		Account account = findAccount(customer, accountId);
		if (account == null) {
			return false;
		}
		BigDecimal amountBD = BigDecimal.valueOf(amount);
		return account.deposit(amountBD);		
	}

	/**
	 * Withdraws a specified amount from a specified account.
	 * @param pNo       - the personal number of the customer
	 * @param accountId - the account number
	 * @param amount    - the amount to transfer
	 * @return true if account was found and other conditions were met
	 */
	public boolean withdraw(String pNo, int accountId, int amount) {
		Customer customer = findCustomer(pNo);
		Account account = findAccount(customer, accountId);
		if (account == null) {
			return false;
		}
		BigDecimal amountBD = BigDecimal.valueOf(amount);
		return account.withdraw(amountBD);
	}

	/**
	 * Closes a specified account.
	 * @param pNo       - the personal number of the customer
	 * @param accountId - the account number
	 * @return information about the closed account with the interest
	 */
	public String closeAccount(String pNo, int accountId) {
		Customer customer = findCustomer(pNo);
		Account account = findAccount(customer, accountId);
		if (account == null) {
			return null;
		}
		customer.removeAccount(accountId);
		return account.accountWithInterest();
	}

	/**
	 * Removes a customer from the bank.
	 * @param pNo - the personal number of the customer
	 * @return information about the removed customer with the interest
	 */
	public ArrayList<String> deleteCustomer(String pNo) {
		Customer customer = findCustomer(pNo);
		if (customer == null) {
			return null;
		}
		customers.remove(customer);
		return customer.customerAndAccountsInfo("interest");
	}

	/**
	 * Gets an array list with strings presenting the transactions made for the specified account owned by
	 * the specified customer.
	 * this account.
	 * @param pNo - the personal number of the customer
	 * @param accountId - the account number
	 * @return the array list with the transaction strings
	 */
	public ArrayList<String> getTransactions(String pNo, int accountId) {
		Customer customer = findCustomer(pNo);
		Account account = findAccount(customer, accountId);
		if (account == null) {
			return null;
		}
		return account.getTransactions();
	}

}
