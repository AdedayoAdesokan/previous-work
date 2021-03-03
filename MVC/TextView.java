import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class TextView extends Box implements Observer {

	private JTextField[] list;  //a collection of JTextField components
	private DataModel model;  //a reference to the data model
	
	/*
	 * Constructor that initializes model and creates list with the same size as the model. 
	 */
	public TextView(DataModel aModel) {
		super(BoxLayout.Y_AXIS);
		model = aModel;
		list = new JTextField[model.getLength()];
	}
	
	/*
	 * Gets the index of a given JTextField.
	 * @param field the field you want to find the index of
	 * @return the index of the field
	 */
	public int index(JTextField field)
	{
		int index = 0;
		for (JTextField currentField: list)
		{
			if (currentField == field)
			{
				break;
			}
			index++;
		}
		return index;
	}
	
	/*
	 * Calls the add method of the Box superclass.
	 * @param field the field you wish to add
	 */
	public void addField(JTextField field)
	{
		add(field);
	}
	
	/*
	 * Adds a field to list at a specific index.
	 * @param field the field you want to add to the array
	 * @param index the index in the array you want to add field to
	 */
	public void addToList(JTextField field, int index)
	{
		list[index] = field;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o == model)
		{
			int count = 0;
			for (JTextField field : list)
			{
				if (field != null)  //Makes sure that the field isn't null
				{
					int modelValue = model.getInt(count);
					int fieldValue = Integer.parseInt(field.getText());
					if (modelValue != fieldValue)  //Checks to see that the data in the field is actually different from the data in the model
					{
						field.setText(Integer.toString(modelValue));  //Updates the data in the field to match the data in the model.
					}
					count++;
				}	
			}
		}
	}
	
	/*
	 * Changes the length of list to match the length of the model.
	 */
	public void resizeList()
	{
		JTextField[] newList = new JTextField[model.getLength()];
		System.arraycopy(list, 0, newList, 0, list.length);
		list = newList;
	}
	
	/*
	 * Gets the number of non-null fields in list.
	 * @return the number of non-null fields in list
	 */
	public int fieldCount()
	{
		int count = 0;
		for (JTextField field : list)
		{
			if (field != null)
			{
				count++;
			}
		}
		return count;
	}
}