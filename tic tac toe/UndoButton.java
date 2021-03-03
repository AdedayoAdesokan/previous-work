import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import javax.swing.JButton;

public class UndoButton extends JButton implements Observer {
	private Board model; // The Board this button observes
	private int modelSize; // The size of the model before the button is pressed
	private int count; // Counts the number of times the button has been pressed
	private Stack<Integer> counter; // A stack that holds each count

	/*
	 * A constructor that creates an UndoButton with a set name
	 * 
	 * @param aModel the Board that this button observes
	 */
	public UndoButton(Board aModel) {
		super("Undo");
		model = aModel;
		modelSize = model.size();
		counter = new Stack<>();
		count = 0;

		this.addActionListener(new ActionListener() { // Adds an ActionListener for the button
			public void actionPerformed(ActionEvent e) {
				int previousModelSize = modelSize; // Holds the size of the board before the button was pressed
				updateModelSize(); // Updates the modelSize integer
				newTurn(previousModelSize); // Checks if it's a new turn, it's a new turn if the number of elements in
											// the model is larger than it was before the button was pressed
											// System.out.println("before = " + model.size());
				model.remove(); // Removes the last item in the model
								// System.out.println("after = " + model.size());
				eligibility(); // Checks the eligibility of the button
								// System.out.println("modelSize = " + modelSize);
								// System.out.println("model.size() = " + model.size());
				count++; // Increments count
				counter.push(count); // Adds count to the stack
										// System.out.println("count = " + count);
			}
		});
	}

	/*
	 * Determines whether the button should be enabled or not
	 */
	public void eligibility() {
		if (model.size() == 0 || model.size() == 9) { // When the board is empty or full the button is disabled
			this.setEnabled(false);
		} else if (modelSize > model.size()) { // Checks to see if the button was just pressed, if so disable the button
			this.setEnabled(false);
		} else if (!counter.isEmpty() && counter.peek() % 3 == 0) { // Disables the button if it has been pressed three
																	// times in a single turn
			this.setEnabled(false);
			resetCounter(); // Resets the stack
		} else {
			this.setEnabled(true);
		}
	}

	/*
	 * Sets modelSize to be equal to the number of elements in the model
	 */
	public void updateModelSize() {
		modelSize = model.size();
	}

	/*
	 * Empties the stack and resets count so it's equal to zero
	 */
	public void resetCounter() {
		counter.clear();
		count = 0;
	}

	/*
	 * Determines if it's a new turn
	 * 
	 * @param position
	 */
	public boolean newTurn(int position) {
		if (modelSize > position) { // Compares the the number of elements in the board to determine if it's a new
									// turn
			resetCounter(); // Resets the stack
			return true;
		}
		return false;
	}

	/*
	 * When the model is updated, check the eligibility of the button
	 */
	public void update(Observable o, Object arg) {
		eligibility();
	}
}