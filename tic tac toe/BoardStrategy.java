import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JButton;

public interface BoardStrategy {
	
	/*
	 * Overrides the paintComponent method of the JPanel superclass.
	 * Creates the tic-tac-toe board and draws X's and O's as needed.
	 */
	public void paintComponent(Graphics g);

	/*
	 * Creates a new square for each element in the model. Checks if the board is in
	 * a winning state.
	 */
	public void updateSquares();

	/*
	 * Determines whether the board is in a winning state
	 */
	public void winningState();

	/*
	 * Disables the frame this panel is in
	 */
	public void disableFrame();
	
	/*
	 * Sets the visibility of the frame to true
	 */
	public void visibility();

	/*
	 * Enables the frame this panel is in
	 */
	public void enableFrame();

	/*
	 * Gets the classe's unique button
	 * @return the classes button
	 */
	public JButton getInitialButton();

	/*
	 * Implements the update method of the Observer interface
	 */
	public void update(Observable o, Object arg);

}