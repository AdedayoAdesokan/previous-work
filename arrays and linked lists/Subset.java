import edu.princeton.cs.algs4.StdIn;

// Takes a command-line integer k; reads in a sequence of strings from
// standard input; and prints out exactly k of them, uniformly at random.
// Note that each item from the sequence is printed out at most once.
public class Subset {
    public static void main(String[] args) {
        int printNumber = Integer.parseInt(args[0]);  //Reads the command-line integer
        ResizingArrayRandomQueue<String> q = new ResizingArrayRandomQueue<>();  //Creates an object q of typeResizingArrayRandomQueue
        while (!StdIn.isEmpty()) {  //Reads strings from standard input and inserts them into q
            String input = StdIn.readString();
            q.enqueue(input);
        }
        for (int i = 0; i < printNumber; i++) {  //Dequeues and prints items from q, where the number of items printed is equal to printNumber(the command-line argument)
            System.out.println(q.dequeue());
        }
    }
}
