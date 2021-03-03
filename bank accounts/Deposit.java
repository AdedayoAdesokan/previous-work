public class Deposit implements Runnable {
	private BankAccount account;
	private double amount;
	private int depositCount;

	/*
	 * Creates a runnable Deposit object
	 * 
	 * @param account the account being deposited into
	 * 
	 * @param count the number of times the amount gets deposited
	 */
	public Deposit(BankAccount account, int count) {
		this.account = account;
		this.depositCount = count;
		amount = 0;
	}

	/*
	 * Creates a runnable Deposit object with a given amount
	 */
	public Deposit(BankAccount account, int count, double amount) {
		this(account, count);
		this.amount = amount;
	}

	/*
	 * Deposits the amount into the account (count number of times)
	 */
	public void run() {
		for (int i = 0; i <= depositCount; i++) {
			account.deposit(amount);

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
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