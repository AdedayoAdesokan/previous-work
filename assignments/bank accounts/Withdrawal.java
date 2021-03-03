public class Withdrawal implements Runnable {
	private BankAccount account;
	private double amount;
	private int withdrawalCount;

	/*
	 * Creates a runnable Withdrawal object
	 * 
	 * @param account the account being deposited into
	 * 
	 * @param count the number of times the amount gets deposited
	 */
	public Withdrawal(BankAccount account, int count) {
		this.account = account;
		this.withdrawalCount = count;
		amount = 0;
	}

	/*
	 * Creates a runnable Withdrawal object with a given amount
	 */
	public Withdrawal(BankAccount account, int count, double amount) {
		this(account, count);
		this.amount = amount;
	}

	/*
	 * Withdrawals the amount from the account (count number of times)
	 */
	public void run() {
		for (int i = 0; i <= withdrawalCount; i++) {
			if (account.getBalance() > amount) {
				account.withdrawal(amount);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Updates the amount being deposited to the account
	 * 
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
}