import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Hw2P2 {
	public static void main(String[] args) throws FileNotFoundException {
		DataModel model = new DataModel();  //Creates a data model.
		TextView tv = new TextView(model);  //Creates a text view.
		GraphView gv = new GraphView(model);  //Creates a graph view.
		model.addObserver(tv);  //Adds the text view as an observer to the model.
		model.addObserver(gv);  //Adds the graph view as an observer to the model.
		
		File inputs = new File(args[0]);  //Gets the file given in args[0].
		Scanner scnr = new Scanner(inputs);
		int count = 0;
		while (scnr.hasNextInt()) {  //Loops through the entire file of integers
			if (count == model.getLength())  //Resizes the model and text view if the number of items being added to the model exceeds the length of the array of integers in the model.
			{
				model.resizeData();
				tv.resizeList();
			}
			
			int value = scnr.nextInt();
			model.addInt(value, count);  //Adds the integer to the model.
			JTextField field = new JTextField();  //Creates a field for each element added to the model.
			field.setText(Integer.toString(value));  //Sets the text in the field to the corresponding value in the model.
			tv.addToList(field, count);  //Adds the field to the text view.
			tv.addField(field);  //Calls the add method of textView's superclass.
			
			field.getDocument().addDocumentListener(new DocumentListener() {  //Adds a document listener to track the editing of the field.

				@Override
				public void insertUpdate(DocumentEvent e) {  //When a number is modified in the field, update the model.
					try
					{
						String stringInput = field.getText();
						int input = Integer.parseInt(stringInput);
						model.updateInt(input, tv.index(field));
					}
					catch (NumberFormatException exception)
					{
					}
					
				}

				@Override
				public void removeUpdate(DocumentEvent e) {  //When a number is modified in the field, update the model.
					try
					{
						String stringInput = field.getText();
						int input = Integer.parseInt(stringInput);
						model.updateInt(input, tv.index(field));
					}
					catch (NumberFormatException exception)
					{
					}
				}

				@Override
				public void changedUpdate(DocumentEvent e) {  //When a number is modified in the field, update the model.
					try
					{
						String stringInput = field.getText();
						int input = Integer.parseInt(stringInput);
						model.updateInt(input, tv.index(field));
					}
					catch (NumberFormatException exception)
					{
					}
				}
				
			});			
			count++;
		}
		
		JFrame textFrame = new JFrame();  //A frame for the text view.
		textFrame.setContentPane(tv);
		textFrame.addWindowListener(new WindowAdapter() {  //Adds a window listener to the frame to save the data when the frame is closed.
			public void windowClosing(WindowEvent event) {
				try
				{
				   PrintWriter writer = new PrintWriter(new FileWriter(inputs));
				   for (int i = 0; i < tv.fieldCount(); i++)
				   {
					   int value = model.getInt(i);
					   writer.println(value);
				   }
				   writer.close();
				}
				catch (IOException e)
				{
				   e.printStackTrace();
				}
			}
		});
		textFrame.pack();
		textFrame.setVisible(true);
		
		JFrame graphFrame = new JFrame();  //A frame for the graph view.
		graphFrame.setContentPane(gv);
		graphFrame.addWindowListener(new WindowAdapter() {   //Adds a window listener to the frame to save the data when the frame is closed.
			public void windowClosing(WindowEvent event) {
				try
				{
				   PrintWriter writer = new PrintWriter(new FileWriter(inputs));
				   for (int i = 0; i < tv.fieldCount(); i++)
				   {
					   int value = model.getInt(i);
					   writer.println(value);
				   }
				   writer.close();
				}
				catch (IOException e)
				{
				   e.printStackTrace();
				}
			}
		});
		graphFrame.setVisible(true);
		
		Rectangle2D rect = textFrame.getBounds();  //Gets the rectangle that bounds the text view frame.
		if ((int)rect.getWidth() * 2 > model.maxValue())  //Sets the width of the bounds of the graph view frame to twice the width of the text view frame if the largest integer in the model is not larger than double the width of the text view.
		{
			graphFrame.setBounds((int)(rect.getX()+ rect.getWidth()), (int)rect.getY(), (int)rect.getWidth() * 2, (int)rect.getHeight());

		}
		else  //Sets the width of the bounds of the graph view frame to the largest integer in the model if that integer is larger than double the width of the text view.
		{
			graphFrame.setBounds((int)(rect.getX()+ rect.getWidth()), (int)rect.getY(), model.maxValue() + 50, (int)rect.getHeight());

		}
		scnr.close();  //Closes the scanner.
	}
}