public class BankAccount {
	private double balance;

	/*
	 * Creates a Bank Account with a balance of zero.
	 */
	public BankAccount() {
		balance = 0;
	}

	/*
	 * Adds the given amount to the balance
	 * 
	 * @param amount the amount you want to add to the balance
	 */
	public synchronized void deposit(double amount) {
		if (amount > 0) {
			balance = balance + amount;
			System.out.println("New balance: $" + balance);
			System.out.println("You deposited: $" + amount);
			System.out.println();
		} else
			System.out.println("Please ensure that you're depositing more than 0 dollars");
	}

	/*
	 * Subtracts the given amount from the balance
	 * 
	 * @param amount the amount you want to subtract from the balance
	 */
	public synchronized void withdrawal(double amount) {
		if (amount <= balance) {
			balance = balance - amount;
			System.out.println("New balance: $" + balance);
			System.out.println("You withdrew: $" + amount);
			System.out.println();
		} else
			System.out.println("Please ensure you have more money in your account than you are withdrawling");
	}

	/*
	 * Gets the account's balance
	 * 
	 * @return balance's account
	 */
	public synchronized double getBalance() {
		return balance;
	}
}