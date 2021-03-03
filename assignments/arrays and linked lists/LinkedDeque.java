import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Deque implementation using a linked list.
public class LinkedDeque<Item> implements Iterable<Item> {
    private int n;  //The size of the deque
    private Node first;  //Pointer to the head of the deque
    private Node last;  // Pointer to the tail of the deque

    // Helper doubly-linked list class.
    private class Node {
        private Item item;  //Represents the value of the item within the node
        private Node next;  //Pointer to the next node
        private Node prev;  //Pointer to the previous node
    }

    // Construct an empty deque with an initial size of zero.
    public LinkedDeque() {
        n = 0;
    }

    /*
     * Returns true if the deque is empty
     * @return true if size of deque > 0
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /*
     * Returns the number of items within the deque
     * @return the size of thr deque
     */
    public int size() {
        return n;
    }

    /*
     * Adds an item at the head of the deque
     * @param Item the item being added to the front of the deque
     * @throws NullPointerException if the item being added is null
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else if (first == null) {  //Checks to see if you're adding to an empty deque
            Node newFirst = new Node();  //Creates a new Node with value of desired item
            newFirst.item = item;
            last = first = newFirst;  //Sets the last node and first node to the new node because there's only 1 node within the deque
            n++; //Increments the size of the deque by 1
        } else {  //If the deque is not empty
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = first;  //Sets the new node's next value as the first node
            first.prev = newFirst;  //Sets the first node's previous node as the new node
            first = newFirst;  //Makes first point to the new node at the head of the deque
            n++;  //Increments the size of the deque by 1
        }
    }

    /*
     * Adds an item to the end of the deque
     * @param item the item being added to the end of the deque
     * @throws NullPointerException if the item being added is null
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        } else if (last == null) {  //Checks to see if you're adding to an empty deque
            Node newLast = new Node();  //Creates a new Node with value of desired item
            newLast.item = item;
            first = last = newLast;  //Sets the first node and last node to the new node because there's only 1 node within the deque
            n++;  //Increments the size of the deque by 1
        } else {  //If the deque is not empty
            Node newLast = new Node();
            newLast.item = item;
            newLast.prev = last;  //Sets the new node's previous node as the last node
            last.next = newLast;  //Sets the last node's next node as the new node
            last = newLast;  //Makes last point to the new node at the end of the deque
            n++;  //Increments the size of the deque by 1
        }
    }

    /*
     * Removes and returns the item at the head of the deque
     * @return the item at the head of the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Item removedItem = first.item;  //Gets the value of the item at the head
        first = first.next;  //Points first to the next node
        n--;  //Decrements the size of the deque by 1
        return removedItem;
    }

    /*
     * Removes and returns the item at the end of the deque
     * @return the item at the end of the deque
     * @throws NoSuchElementException if the deque is empty
     */
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        Item removedItem = last.item;  //Gets the value of the item at the end
        last = last.prev;  //Points last to the previous node
        n--;  //Decrements the size of the deque by 1
        return removedItem;
    }

    /*
     * An iterator over items in the queue in order from front to end
     * @Return a new DequeIterator
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // An iterator, doesn't implement remove() since it's optional.
    private class DequeIterator implements Iterator<Item> {
        Node current;  //Pointer to current node in the iterator

        DequeIterator() {
            current = first;  //Sets current to the first node in the deque
        }

        /*
         * Returns true if the iterator has more items to iterate
         * @return true if current is not in the last position of the deque
         */
        public boolean hasNext() {
            return current != last;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        /*
         * Returns the item in current and advances current to the next node
         * @Return the item in the current node
         * @throws NoSuchElementException if current is in the last position of the deque
         */
        public Item next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            Item returnedItem = current.item;  //Gets the item at current
            current = current.next;  //Advances current to the next item
            return returnedItem;
        }
    }

    // A string representation of the deque.
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item + " ");
        }
        return s.toString().substring(0, s.length() - 1);
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        LinkedDeque<Character> deque = new LinkedDeque<Character>();
        String quote = "There is grandeur in this view of life, with its "
                + "several powers, having been originally breathed into a few "
                + "forms or into one; and that, whilst this planet has gone "
                + "cycling on according to the fixed law of gravity, from so "
                + "simple a beginning endless forms most beautiful and most "
                + "wonderful have been, and are being, evolved. ~ "
                + "Charles Darwin, The Origin of Species";
        int r = StdRandom.uniform(0, quote.length());
        for (int i = quote.substring(0, r).length() - 1; i >= 0; i--) {
            deque.addFirst(quote.charAt(i));
        }
        for (int i = 0; i < quote.substring(r).length(); i++) {
            deque.addLast(quote.charAt(r + i));
        }
        StdOut.println(deque.isEmpty());
        StdOut.printf("(%d characters) ", deque.size());
        for (char c : deque) {
            StdOut.print(c);
        }
        StdOut.println();
        double s = StdRandom.uniform();
        for (int i = 0; i < quote.length(); i++) {
            if (StdRandom.bernoulli(s)) {
                deque.removeFirst();
            } else {
                deque.removeLast();
            }
        }
        StdOut.println(deque.isEmpty());
    }
}
