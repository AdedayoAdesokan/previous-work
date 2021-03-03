import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends Observable {
	private ArrayList<Integer> data; // An ArrayList that holds the zeros and ones that represent X's and O's
										// respectively
	private ArrayList<String> squares; // An ArrayList that holds the name of each of the 9 squares
	private ArrayList<BoardStrategy> styles;
	private ArrayList<JButton> buttons;

	/*
	 * A constructor that initializes the ArrayLists
	 */
	public Board() {
		data = new ArrayList<>();
		squares = new ArrayList<>();
		styles = new ArrayList<>();
		buttons = new ArrayList<>();
	}

	/*
	 * Adds the given number and square to the proper ArrayLists
	 * 
	 * @param number a number that becomes a 0 or a 1 depending on the last element
	 * added to data
	 * 
	 * @param square the name of the square being added to squares
	 */
	public void updateModel(int number, String square) {
		if (data.isEmpty()) { // Adds a zero to the empty ArrayList data and adds the name of the square to
								// the ArrayList squares to signify that the square is occupied
			number = 0;
			data.add(number);
			squares.add(square);
			setChanged();
			notifyObservers();
		} else if (data.get(data.size() - 1) == 0 && !squares.contains(square)) { // If the latest element added was a
																					// 0, add a 1 as long as that square
																					// isn't already occupied
			number = 1;
			data.add(number);
			squares.add(square);
			setChanged();
			notifyObservers();
		} else if (data.get(data.size() - 1) == 1 && !squares.contains(square)) { // If the latest element added was a
																					// 1, add a 0 as long as that square
																					// isn't already occupied
			number = 0;
			data.add(number);
			squares.add(square);
			setChanged();
			notifyObservers();
		}
	}

	/*
	 * Removes the latest element from the ArrayLists
	 */
	public void remove() {
		data.remove(data.size() - 1);
		squares.remove(squares.size() - 1);
		setChanged();
		notifyObservers();
	}

	/*
	 * Gets the integer at the given index
	 * 
	 * @param index the index of the integer you're looking for
	 * 
	 * @return the integer at that index
	 */
	public int getNumber(int index) {
		return data.get(index);
	}

	/*
	 * Gets the name of the square at the given index
	 * 
	 * @param index the index of the square you're looking for
	 * 
	 * @return the name of the square at that index
	 */
	public String getSquare(int index) {
		return squares.get(index);
	}

	/*
	 * Gets the size of the ArrayList data
	 * 
	 * @return the size of data
	 */
	public int size() {
		return data.size();
	}

	/*
	 * Adds the given style to the ArrayList styles
	 * 
	 * @param strategy the style of the board
	 */
	public void addStyle(BoardStrategy strategy) {
		styles.add(strategy);
	}

	/*
	 * Creates a button for each style of the board
	 */
	private void createButtons() {
		for (BoardStrategy style : styles) {
			JButton button = style.getInitialButton();
			buttons.add(button);
		}
	}

	/*
	 * Gets the index of the style associated with the given button
	 * 
	 * @param button the button you want the index of
	 */
	public int getRelatedStrategyIndex(JButton button) {
		if (buttons.contains(button)) {
			return buttons.indexOf(button);
		} else
			return -1;
	}

	/*
	 * Creates the initial screen for the game.
	 */
	public void initialScreen() {
		JFrame initialFrame = new JFrame(); // the initial screen that shows the different buttons for each of the
											// styles
		Label title = new Label("Please select a style for the board");
		Font smallFont = new Font("Courier", Font.BOLD, 25);
		title.setFont(smallFont);

		createButtons(); // Creates a button for each style of the board

		for (int i = 0; i < buttons.size(); i++) { // Adds action listeners to the board
			int index = getRelatedStrategyIndex(buttons.get(i));
			buttons.get(i).addActionListener(event -> styles.get(index).visibility()); // When the button is pressed,
																						// the
																						// associated board becomes
																						// visible
			buttons.get(i).addActionListener(event -> initialFrame.setVisible(false)); // When the button is pressed,
																						// the initial screen is no
																						// longer visible
			buttons.get(i).addActionListener(event -> styles.get(index).enableFrame()); // When the button is pressed,
																						// the associated board is
																						// enabled
		}

		JPanel buttonPanel = new JPanel(); // A panel that holds all the buttons

		initialFrame.add(title, BorderLayout.NORTH); // Adds the title to the top of the frame
		for (JButton button : buttons) {
			buttonPanel.add(button); // Add each button to the panel
		}
		initialFrame.add(buttonPanel, BorderLayout.SOUTH); // Adds the panel to the bottom of the frame
		initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialFrame.pack();
		initialFrame.setVisible(true);
	}
}