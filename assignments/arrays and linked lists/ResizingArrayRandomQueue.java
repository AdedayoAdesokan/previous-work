import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

// Random queue implementation using a resizing array.
public class ResizingArrayRandomQueue<Item> implements Iterable<Item> {
    private Item[] q;  //Array to store the items of queue
    private int N;  //The size of the queue

    // Construct an empty queue with a capacity of 2 and an initial size of 0
    public ResizingArrayRandomQueue() {
        q = (Item[]) new Object[2];
        N = 0;
    }

    /*
     * Returns true if the number of items in the queue is equal to zero
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /*
     * Returns the number of items in the queue
     * @Return the number of non-null items in the queue
     */
    public int size() {
        return N;
    }

    /*
     * Adds item to the queue. If the queue is at full capacity, it's capacity is doubled.
     * @param item the item you want to add to the queue
     * @throw NullPointerException if item is null
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (q.length == N) {  //Checks to see if the queue is at full capacity
            int newSize = q.length * 2;  //Doubles the size of the original queue
            this.resize(newSize);  //Resizes the queue
        }
        q[N] = item;  //Inserts item at index N
        N++;  //Increments the size of the queue by 1
    }

    /*
     * Removes and returns a random item from the queue. If the queue is at quarter capacity, resize it to half its current capacity
     * @return a random item from the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Random rng = new Random(); //Creates a new Random object called rng(random number generator)
        int r = rng.nextInt(N);  //Gets a random number between zero and the size of the queue-1
        Item randomItem = q[r];  //Gets a random item from the queue
        q[r] = q[N - 1];  //Sets the item at the index of the random item to the last item in the queue
        q[N - 1] = null;  //Sets the last item in the queue to null
        if (N == q.length / 4) {  //Checks if the queue is at quarter capacity
            int newSize = q.length / 2;  //Decreases the size of the queue by half
            this.resize(newSize);  //Resizes the queue
        }
        N--;  //Decrements the size of the queue by 1
        return randomItem;  //Returns the random item
    }

    /*
     * Returns a random item from the queue, but doesn't remove it
     * @return a random item from the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public Item sample() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Random rng = new Random(); //Creates a new Random object called rng(random number generator)
        int r = rng.nextInt(N);  //Gets a random number between zero and the size of the queue-1
        return q[r];  //Returns the random item
    }

    /*
     * An independent iterator over items in the queue in random order.
     * @return a new RandomQueueIterator
     */
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class RandomQueueIterator implements Iterator<Item> {
        Item[] items;  //Array to store the items of q
        int current;  //Index of the current item in items

        /*
         * Creates items with the same capacity as q. Copies the items of q into items. Shuﬄes items
         */
        RandomQueueIterator() {
            items = (Item[]) new Object[q.length];  //Creates items with the same capacity as q
            for (int i = 0; i < q.length; i++) {  //Copies the non-null items of q into items
                if (q[i] != null) {
                    items[i] = q[i];
                }
            }
            Random rng = new Random();  //Creates a new Random object called rng(random number generator)
            for (int i = 0; i < N; i++) {  //Increments i the same amount of times as the non-null items in q.
                int randomNumber1 = rng.nextInt(N);  //Gets a random number between zero and the size of the queue-1
                while (items[randomNumber1] == null) {
                    randomNumber1 = rng.nextInt(N);  //Ensures that the random number is not null
                }
                int randomNumber2 = rng.nextInt(N);  //Gets a second random number between zero and the size of the queue-1
                while (items[randomNumber2] == null) {
                    randomNumber2 = rng.nextInt(N);  //Ensures that the random number is not null
                }
                if (items[randomNumber1] != null && items[randomNumber2] != null) {  //Ensures that the random numbers aren't null
                    Item temp = items[randomNumber1];  //Creates a temporary Item to store the item at the first random number
                    items[randomNumber1] = items[randomNumber2];  //Sets the item at the 1st random number to the item at the 2nd
                    items[randomNumber2] = temp;  //Sets the item at the 2nd random number to the previous item at 1st
                }
            }
            current = 0;  //Ensures that current points to the first item in items.
        }

        /*
         * Returns true if the iterator has more items to iterate
         * @return true if current is not equal to the size of q
         */
        public boolean hasNext() {
            return current != N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /*
         * Returns the item in items at index current and advance current by one
         * @return the item in items at index current
         * @throws NoSuchElementException if current is equal to the size of q
         */
        public Item next() {
            if (current == N) {
                throw new NoSuchElementException();
            }
            Item currentItem = items[current];  //Gets the item at index current
            current++;  //Advances current by 1
            return currentItem;
        }

    }

    // A string representation of the queue.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Helper method for resizing the underlying array.
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            if (q[i] != null) {
                temp[i] = q[i];
            }
        }
        q = temp;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        ResizingArrayRandomQueue<Integer> q =
                new ResizingArrayRandomQueue<Integer>();
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readInt());
        }
        int sum1 = 0;
        for (int x : q) {
            sum1 += x;
        }
        int sum2 = sum1;
        for (int x : q) {
            sum2 -= x;
        }
        int sum3 = 0;
        while (q.size() > 0) {
            sum3 += q.dequeue();
        }
        StdOut.println(sum1);
        StdOut.println(sum2);
        StdOut.println(sum3);
        StdOut.println(q.isEmpty());
    }
}
