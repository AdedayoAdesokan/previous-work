import java.util.Iterator;
import java.util.Observable;

public class DataModel extends Observable {

	private int[] data;  //an array of integers 
	
	/*
	 * Constructor that initializes data to hold 10 items.
	 */
	public DataModel() {
		data = new int[10];  
	}
	
	/*
	 * Adds an integer to the array of integers.
	 * @param number the number you want to add to data
	 * @param index the position you want the number to be in
	 */
	public void addInt(int number, int index) {
		data[index] = number;

	}
	
	/*
	 * When a controller modifies the data, update the model and notify the observers.
	 * @param number the updated value
	 * @param index the index in the array you want to update
	 */
	public void updateInt(int number, int index)
	{
		data[index] = number;
		setChanged();
		notifyObservers();
	}

	/*
	 * A getter method that returns the value at a specific index.
	 * @param index the index of the value you want to get
	 * @return the desired value
	 */
	public int getInt(int index) {
		return data[index];
	}

	/*
	 * A getter method that returns the index of a given value.
	 * @param value the number you want to find the index of
	 * @return the index of value.
	 */
	public int getIndex(int value) {
		int count = 0;
		for (int number : data) {
			if (number == value) {
				break;
			}
			count++;

		}
		return count;
	}
	
	/*
	 * Gets the length of the array of integers.
	 * @return the length of data
	 */
	public int getLength()
	{
		return data.length;
	}

	/*
	 * Doubles the size of the array of integers.
	 */
	public void resizeData()
	{
		int[] newData = new int[2 * this.getLength()];
		System.arraycopy(data, 0, newData, 0, this.getLength());
		data = newData;
	}
	
	/*
	 * Gets the maximum value in data.
	 * @return the maximum value
	 */
	public int maxValue()
	{
		int max = data[0];
		for (int value : data)
		{
			if (value > max)
			{
				max = value;
			}
		}
		return max; 
	}
}