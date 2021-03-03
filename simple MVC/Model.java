import java.util.*;
import javax.swing.event.*;

public class Model {
	private ArrayList<String> inputs;  //An array list that holds the inputs.
	private ArrayList<ChangeListener> listeners;  //An array list that holds the ChangeListeners.
	
	/*
	 * This class serves as the model.
	 */
	public Model()
	{
		inputs = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	/*
	 * Adds a change listener to the Model.
	 * @param listener the change listener to add
	 */
	public void addChangeListener(ChangeListener listener)
	{
		listeners.add(listener);
	}
	
	/*
	 * Adds the input to the Model.
	 * @param input the input you want to add
	 */
	public void addInput(String input)
	{
		inputs.add(input);
		ChangeEvent event = new ChangeEvent(this); //Notify all observers of the change to the Model.
		for (ChangeListener listener : listeners)
		{
			listener.stateChanged(event);
		}
	}
	
	/*
	 * Gets the last String in the array list inputs.
	 * @return the last String in inputs.
	 */
	public String getLatestInput()
	{
		String input = inputs.get(inputs.size()-1);
		return input;
	}
}