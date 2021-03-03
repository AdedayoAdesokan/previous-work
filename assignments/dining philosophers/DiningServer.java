public interface DiningServer {
    /*
     * Called by a philosopher when it wishes to eat
     */
    void takeForks(int philosopherNumber);

    /*
     * Called by a philosopher when it finishes eating
     */
    void returnForks(int philosopherNumber);
}
