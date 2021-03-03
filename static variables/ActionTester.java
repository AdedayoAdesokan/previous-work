import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionTester
{
   static int count = 1;	// create a static variable
	public static void main(String[] args)
   {
      JFrame frame = new JFrame();  // create a frame

      final int FIELD_WIDTH = 20;  							// set the width as a constant
      JTextField textField = new JTextField(FIELD_WIDTH);  	// create a text field
      textField.setText("Click a button!");  				// set the text in the field

      JButton helloButton = new JButton("Say Hello");  // create a button
            
      helloButton.addActionListener(event ->	// add an action listener to the hello button
         textField.setText("Hello " + count));	// when an event occurs, set the field's text
    
      JButton goodbyeButton = new JButton("Say Goodbye");	// create another button

      goodbyeButton.addActionListener(event ->		// add an action listener to the goodbye button  
         textField.setText("Goodbye " + count));	// when an event occurs, set the field's text
      goodbyeButton.setEnabled(false);				// set the goodbye button to false

      frame.setLayout(new FlowLayout());	// set the layout of the frame

      frame.add(helloButton);		// add the hello button to the frame
      frame.add(goodbyeButton);		// add the goodbye button to the frame
      frame.add(textField);			// add the textfield to the frame

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// exit the application when closed
      frame.pack();				
      frame.setVisible(true); 	
      
      while (helloButton.isEnabled())	
      {
    	  helloButton.addActionListener(event ->  	
          helloButton.setEnabled(false));			// disable the button when clicked
    	  helloButton.addActionListener(event ->
          goodbyeButton.setEnabled(true));			// enable the goodbye button
    	  if (!helloButton.isEnabled())				
          {
    		  goodbyeButton.addActionListener(event ->	
              helloButton.setEnabled(true));			// enable the hello button
              goodbyeButton.addActionListener(event ->
              goodbyeButton.setEnabled(false));			// disable the button when clicked
              helloButton.addActionListener(event ->
              count = count + 1);						// increment the counter
          }
      } 
   }
}
