import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosopher implements DiningServer {
    public static final int PHIL_COUNT = 5;  //the total number of philosophers

    //indicates the state that a philosopher is in
    private enum State {THINKING, HUNGRY, EATING}

    ;
    private State[] states;  //an array for the states of all the philosophers
    private Condition[] self;  //an array for all of the condition variables
    private Lock lock;  //a lock to handle synchronization

    /*
     * Constructor that creates a DiningPhilosopher object
     */
    public DiningPhilosopher() {
        states = new State[PHIL_COUNT];  //create the array of states and set the size to 5
        for (int i = 0; i < PHIL_COUNT; i++) {
            states[i] = State.THINKING;  //initialize every philosopher as thinking
        }

        lock = new ReentrantLock();  //create the Re-entrant lock
        self = new Condition[PHIL_COUNT];  //create the array of condition variables and set the size to 5
        for (int i = 0; i < PHIL_COUNT; i++) {
            self[i] = lock.newCondition();  //initialize each condition variable
        }
    }

    /*
     * Gets the left neighbor of a given philosopher
     * @param philosopherNumber the given philosopher's number
     * @return the number associated with the philosopher to the left of the given philosopher
     */
    public int leftNeighbor(int philosopherNumber) {
        if (philosopherNumber == 0) {  //the neighbor to the left of 0 is 4
            return PHIL_COUNT - 1;
        } else {
            return philosopherNumber - 1;
        }
    }

    /*
     * Gets the right neighbor of a given philosopher
     * @param philosopherNumber the given philosopher's number
     * @return the number associated with the philosopher to the right of the given philosopher
     */
    public int rightNeighbor(int philosopherNumber) {
        if (philosopherNumber == PHIL_COUNT - 1) {  //the neighbor to the right of 4 is 0
            return 0;
        } else {
            return philosopherNumber + 1;
        }
    }

    /*
     * Tests to see if a philosopher can enter the eating state
     * @param philosopherNumber the given philosopher's number
     */
    public void test(int philosopherNumber) {
        //a philosopher can only eat when they are hungry and the philosophers to the left and right are not eating
        if ((states[leftNeighbor(philosopherNumber)] != State.EATING) && (states[rightNeighbor(philosopherNumber)] != State.EATING) && (states[philosopherNumber] == State.HUNGRY)) {
            states[philosopherNumber] = State.EATING;  //set the philosopher's state to eating
            self[philosopherNumber].signalAll();  //signal the philosopher in case it is blocked
        }
    }

    /*
     * when a philosopher wishes to eat, this method is called
     */
    public void takeForks(int philosopherNumber) {
        lock.lock();  //lock the Re-entrant lock
        try {
            states[philosopherNumber] = State.HUNGRY;  //set the philosopher's state to hungry
            test(philosopherNumber);  //check if the philosopher can eat
            if (states[philosopherNumber] != State.EATING) {  //if the philosopher is not eating
                self[philosopherNumber].await();  //the philosopher has to wait and release the lock
            }
        } catch (InterruptedException exception) {
            System.out.println(exception.toString());
        } finally {
            lock.unlock();  //unlock the Re-entrant lock
        }
    }

    /*
     * when a philosopher finishes eating, this method is called
     */
    public void returnForks(int philosopherNumber) {
        lock.lock();  //lock the Re-entrant lock
        try {
            states[philosopherNumber] = State.THINKING;  //set the philosopher's state to thinking
            test(leftNeighbor(philosopherNumber));  //check to see if the philosopher to the left can eat
            test(rightNeighbor(philosopherNumber));  //check to see if the philosopher to the right can eat
        } finally {
            lock.unlock();  //unlock the Re-entrant lock
        }
    }
}
