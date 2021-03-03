import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.*;

public class MVCTester {
	public static void main(String[] args) {
		// This serves as the model that holds the data.
		final Model model = new Model();

		// This part serves as the viewer.
		final JTextArea textArea = new JTextArea(10, 5); // This text area will contain the model
		// When the Model changes, update the text area
		ChangeListener listener = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				textArea.append(model.getLatestInput() + "\n");
			}
		};
		model.addChangeListener(listener);

		// This part serves as the data being added to the model.
		// When the Model changes, update the text field (Clear the text field).
		final JTextField textField = new JTextField();
		ChangeListener listenerx = new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				textField.setText("");
			}
		};
		model.addChangeListener(listenerx);
		
		// This part serves as the controller.
		// Make a button for adding the information in the text field to the Model
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String input = textField.getText();
				model.addInput(input);
			}
		});

		// Add the text area, text field, and button to the frame
		JFrame frame = new JFrame();
		frame.add(addButton, BorderLayout.NORTH);
		frame.add(textArea);
		frame.add(textField, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}