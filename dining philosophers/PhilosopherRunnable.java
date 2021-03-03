public class PhilosopherRunnable implements Runnable {

    private DiningPhilosopher philosophers;
    private int philosopherNumber;
    private int iterations;
    private static final int MAX_ITERATIONS = 3;

    /*
     * A constructor that creates an object of PhilosopherRunnable
     * @param table the DiningPhilosopher object that this philosopher is seating at
     * @param seat the number of this philosopher's seat at the table
     */
    public PhilosopherRunnable(DiningPhilosopher table, int seat) {
        this.philosophers = table;
        this.philosopherNumber = seat;
        iterations = 0;  //set the number of iterations to zero at the start
    }

    /*
     * Specifies the amount of time this philosopher is thinking
     */
    public void thinking() {
        SleepUtilities.nap();
    }

    /*
     * Specifies the amount of time this philosopher is eating
     */
    public void eating() {
        SleepUtilities.nap();
    }

    /*
     * the method that runs when the thread begins
     */
    public void run() {
        while (iterations < MAX_ITERATIONS) {
            //every philosopher begins thinking initially
            System.out.println("Philosopher " + philosopherNumber + " is thinking.");
            thinking();  //think for a random amount of time

            System.out.println("Philosopher " + philosopherNumber + " is hungry.");
            philosophers.takeForks(philosopherNumber);  //once the philosopher is hungry, they attempt to pick up their forks

            System.out.println("Philosopher " + philosopherNumber + " is eating.");
            eating();  //eat for a random amount of time

            System.out.println("Philosopher " + philosopherNumber + " is done eating.");
            philosophers.returnForks(philosopherNumber);  //when the philosopher is done eating, the forks are returned to the table

            iterations++;  //increment the number of iterations
        }
    }
}
