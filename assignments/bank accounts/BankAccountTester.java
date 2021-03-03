public class BankAccountTester {
	public static void main(String[] args) {
		final int DEPOSIT_COUNT = 100;
		final int WITHDRAWAL_COUNT = 150;

		// Create a BankAccount object
		BankAccount account = new BankAccount();

		// Create multiple runnable objects
		Runnable d1 = new Deposit(account, DEPOSIT_COUNT, 10);
		Runnable d2 = new Deposit(account, DEPOSIT_COUNT, 50);
		Runnable w1 = new Withdrawal(account, WITHDRAWAL_COUNT, 5);
		Runnable w2 = new Withdrawal(account, WITHDRAWAL_COUNT, 10);
		Runnable w3 = new Withdrawal(account, WITHDRAWAL_COUNT, 20);

		// Create a thread for each runnable object
		Thread thread1 = new Thread(d1);
		Thread thread2 = new Thread(d2);
		Thread thread3 = new Thread(w1);
		Thread thread4 = new Thread(w2);
		Thread thread5 = new Thread(w3);

		// Start each thread
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
	}
}