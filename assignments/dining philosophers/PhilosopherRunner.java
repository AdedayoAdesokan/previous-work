public class PhilosopherRunner {
    public static void main(String[] args) {
        DiningPhilosopher table = new DiningPhilosopher();  //create a new DiningPhilosopher object, the shared variable among the threads
        PhilosopherRunnable[] philosophers = new PhilosopherRunnable[DiningPhilosopher.PHIL_COUNT];  //create an array of philosopher

        for (int seat = 0; seat < DiningPhilosopher.PHIL_COUNT; seat++) {
            philosophers[seat] = new PhilosopherRunnable(table, seat);  //create a new PhilosopherRunnable object and set it's number
        }

        for (int seat = 0; seat < DiningPhilosopher.PHIL_COUNT; seat++) {
            new Thread(philosophers[seat]).start();  //create and start a thread for each philosopher
        }
    }
}
