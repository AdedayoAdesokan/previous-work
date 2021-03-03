import java.util.Iterator;

public class SequenceTester<E> {
	private E[] list;	// a generic array

	public SequenceTester(int size) {
		list = (E[]) new Object[size];	// initialize list and cast the generic type
	}

	/*
	 * Gets an iterator of the list
	 * @return an iterator of the list
	 */
	public Iterator<E> getIterator() {
		return new Iterator<E>() {

			private int current = 0;	// set current to the first element in the array

			@Override
			public boolean hasNext() {
				return current < list.length;	// true if current is not the last element in the array
			}

			@Override
			public E next() {				
				return list[current++];			// gets the next element in the list
			}
		};
	}
	
	/*
	 * Adds the object to the list at the given index
	 * @param object the object being added to the list
	 * @param index the index in the list the object will be inserted at
	 */
	public void add(E object, int index)
	{
		if (index < list.length)
		{
			list[index] = object;
		}
	}

	public static void main(String[] args) {
		SequenceTester<String> list1 = new SequenceTester<String>(20);   // create a sequenceTester of type string
		for (int i = 0; i < 21; i++)
		{
			String number = Integer.toString(i);	// convert i to a string
			list1.add(number, 20-i);					// add the number to the sequence in reverse
		}
		Iterator<String> listIterator = list1.getIterator();	// get the iterator from the sequence
		while (listIterator.hasNext())
		{
			System.out.println(listIterator.next());			// print each element in the sequence
		}
	}
}

