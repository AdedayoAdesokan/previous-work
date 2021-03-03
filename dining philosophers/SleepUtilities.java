public class SleepUtilities {
    private static final int MAX_TIME = 3;

    /*
     * makes the thread nap for a random amount of time
     */
    public static void nap() {
        nap(MAX_TIME);
    }

    /*
     * makes the thread nap for a random amount of time
     * @param length the maximum amount of time the thread can nap for
     */
    public static void nap(int length) {
        int duration = (int) (MAX_TIME * Math.random());
        try {
            Thread.sleep(duration * 1000);
        } catch (InterruptedException exception) {
            System.out.println(exception.toString());
        }
    }
}
