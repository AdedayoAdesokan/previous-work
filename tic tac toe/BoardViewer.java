import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class BoardViewer extends JPanel implements Observer, BoardStrategy {
	public Board model;
	public ArrayList<Square> squares;
	public JFrame frame;
	public UndoButton button;
	private Color backgroundColor;
	private Color foregroundColor;
	private Color lineColor;
	private Color shapeColor;

	/*
	 * A constructor that creates a Board Viewer
	 * 
	 * @param model the Board that this panel observes
	 */
	public BoardViewer(Board aModel) {
		model = aModel;
		squares = new ArrayList<>(); // Creates an empty ArrayList of Squares

		button = new UndoButton(model); // Creates an undoButton
		model.addObserver(button); // Adds the button to the list of observers for the model
		button.eligibility(); // Checks the eligibility of the button

		frame = new JFrame(); // Creates a frame for the panel
		frame.add(this); // Adds this panel to the frame
		frame.add(button, BorderLayout.SOUTH); // Adds the button to the frame
		frame.setBounds(0, 0, 315, 400);
		frame.setVisible(false); // The frame isn't visible initially until the user chooses the theme they want
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setEnabled(false); // The frame is initially disabled until the user chooses the style they prefer
		backgroundColor = null; // Sets the background color to null
		foregroundColor = null; // Sets the foreground color to null
		lineColor = null; // Sets the line color to null
		shapeColor = null; // Sets the shape color to null
	}

	/*
	 * A constructor that creates a Board Viewer
	 * 
	 * @param model the Board that this panel observes
	 * 
	 * @param backgroundColor the color for the background of the panel
	 * 
	 * @param foregroundColor the color for the letters of the Undo button
	 * 
	 * @param lineColor the color of the lines of the board
	 * 
	 * @param shapeColor the color of the X's and O's on the board
	 */
	public BoardViewer(Board aModel, Color backgroundColor, Color foregroundColor, Color lineColor, Color shapeColor) {
		this(aModel);
		this.backgroundColor = backgroundColor; // Initializes the background color to the specified color
		this.foregroundColor = foregroundColor; // Initializes the foreground color to the specified color
		this.lineColor = lineColor; // Initializes the line color to the specified color
		this.shapeColor = shapeColor; // Initializes the shape color to the specified color

		this.setBackground(backgroundColor); // Sets the color of the background to the specified color
		button.setForeground(foregroundColor); // Sets the color of the letters of the button to the specified color
	}

	/*
	 * Overrides the paintComponent method of the JPanel superclass. Creates the
	 * tic-tac-toe board and draws X's and O's as needed.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setBackground(lineColor);
		g2.setColor(lineColor); // Sets the color of the lines of the board to the specified color

		Point startLine1 = new Point(100, 0); // Creates the lines that make up the tic-tac-toe board
		Point endLine1 = new Point(100, 300);
		Point startLine2 = new Point(200, 0);
		Point endLine2 = new Point(200, 300);
		Point startLine3 = new Point(0, 100);
		Point endLine3 = new Point(300, 100);
		Point startLine4 = new Point(0, 200);
		Point endLine4 = new Point(300, 200);
		Line2D.Double line1 = new Line2D.Double(startLine1, endLine1);
		Line2D.Double line2 = new Line2D.Double(startLine2, endLine2);
		Line2D.Double line3 = new Line2D.Double(startLine3, endLine3);
		Line2D.Double line4 = new Line2D.Double(startLine4, endLine4);
		g2.draw(line1);
		g2.draw(line2);
		g2.draw(line3);
		g2.draw(line4);

		g2.setBackground(shapeColor);
		g2.setColor(shapeColor); // Sets the color of the X's and O's to the given color
		for (int i = 0; i < model.size(); i++) { // Iterates through each integer in the model
			String square = model.getSquare(i); // Gets the name of the square at index i in the model
			int shape = model.getNumber(i); // Gets the integer at index i in the model

			// The first square
			if (square.equals("square1") && shape == 0) { // If the shape in the first square is equal to zero, an X is
															// drawn on the board
				Point xStart1 = new Point(10, 10);
				Point xEnd1 = new Point(90, 90);
				Point xStart2 = new Point(90, 10);
				Point xEnd2 = new Point(10, 90);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square1") && shape == 1) { // If the shape in the first square is equal to one, an
																	// O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(5, 5, 90, 90);
				g2.draw(circle);
			}
			// the second square
			else if (square.equals("square2") && shape == 0) { // If the shape in the second square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(110, 10);
				Point xEnd1 = new Point(190, 90);
				Point xStart2 = new Point(190, 10);
				Point xEnd2 = new Point(110, 90);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square2") && shape == 1) { // If the shape in the second square is equal to one,
																	// an O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(105, 5, 90, 90);
				g2.draw(circle);
			}
			// the third square
			else if (square.equals("square3") && shape == 0) { // If the shape in the third square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(210, 10);
				Point xEnd1 = new Point(290, 90);
				Point xStart2 = new Point(290, 10);
				Point xEnd2 = new Point(210, 90);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square3") && shape == 1) { // If the shape in the third square is equal to one, an
																	// O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(205, 5, 90, 90);
				g2.draw(circle);
			}
			// the forth square
			else if (square.equals("square4") && shape == 0) { // If the shape in the fourth square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(10, 110);
				Point xEnd1 = new Point(90, 190);
				Point xStart2 = new Point(90, 110);
				Point xEnd2 = new Point(10, 190);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square4") && shape == 1) { // If the shape in the fourth square is equal to one,
																	// an O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(5, 105, 90, 90);
				g2.draw(circle);
			}
			// the fifth square
			else if (square.equals("square5") && shape == 0) { // If the shape in the fifth square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(110, 110);
				Point xEnd1 = new Point(190, 190);
				Point xStart2 = new Point(190, 110);
				Point xEnd2 = new Point(110, 190);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square5") && shape == 1) { // If the shape in the fifth square is equal to one, an
																	// O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(105, 105, 90, 90);
				g2.draw(circle);
			}
			// the sixth square
			else if (square.equals("square6") && shape == 0) { // If the shape in the sixth square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(210, 110);
				Point xEnd1 = new Point(290, 190);
				Point xStart2 = new Point(290, 110);
				Point xEnd2 = new Point(210, 190);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square6") && shape == 1) { // If the shape in the sixth square is equal to one, an
																	// O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(205, 105, 90, 90);
				g2.draw(circle);
			}
			// the seventh square
			else if (square.equals("square7") && shape == 0) { // If the shape in the seventh square is equal to zero,
																// an X is drawn on the board
				Point xStart1 = new Point(10, 210);
				Point xEnd1 = new Point(90, 290);
				Point xStart2 = new Point(90, 210);
				Point xEnd2 = new Point(10, 290);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square7") && shape == 1) { // If the shape in the seventh square is equal to one,
																	// an O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(5, 205, 90, 90);
				g2.draw(circle);
			}
			// the eighth square
			else if (square.equals("square8") && shape == 0) { // If the shape in the eighth square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(110, 210);
				Point xEnd1 = new Point(190, 290);
				Point xStart2 = new Point(190, 210);
				Point xEnd2 = new Point(110, 290);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square8") && shape == 1) { // If the shape in the eighth square is equal to one,
																	// an O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(105, 205, 90, 90);
				g2.draw(circle);
			}
			// the ninth square
			else if (square.equals("square9") && shape == 0) { // If the shape in the ninth square is equal to zero, an
																// X is drawn on the board
				Point xStart1 = new Point(210, 210);
				Point xEnd1 = new Point(290, 290);
				Point xStart2 = new Point(290, 210);
				Point xEnd2 = new Point(210, 290);
				Line2D.Double xLine = new Line2D.Double(xStart1, xEnd1);
				Line2D.Double xLine2 = new Line2D.Double(xStart2, xEnd2);
				g2.draw(xLine);
				g2.draw(xLine2);
			} else if (square.equals("square9") && shape == 1) { // If the shape in the ninth square is equal to one, an
																	// O is drawn on the board
				Ellipse2D.Double circle = new Ellipse2D.Double(205, 205, 90, 90);
				g2.draw(circle);
			}
		}

		addMouseListener(new MouseAdapter() { // Adds a mouse listener that identifies which square is being pressed
			public void mousePressed(MouseEvent event) {
				double xCord = event.getPoint().x / 100; // Divides the coordinates by 100 because that's the set
															// width/height of each square
				double yCord = event.getPoint().y / 100;

				String s1 = "square1";
				if (xCord == 0 && yCord == 0) { // If the first square is pressed, update the model
					// System.out.println(s1);
					model.updateModel(1, s1);
				}

				String s2 = "square2";
				if (xCord == 1 && yCord == 0) { // If the second square is pressed, update the model
					// System.out.println(s2);
					model.updateModel(2, s2);
				}

				String s3 = "square3";
				if (xCord == 2 && yCord == 0) { // If the third square is pressed, update the model
					// System.out.println(s3);
					model.updateModel(3, s3);
				}

				String s4 = "square4";
				if (xCord == 0 && yCord == 1) { // If the fourth square is pressed, update the model
					// System.out.println(s4);
					model.updateModel(4, s4);
				}

				String s5 = "square5";
				if (xCord == 1 && yCord == 1) { // If the fifth square is pressed, update the model
					// System.out.println(s5);
					model.updateModel(5, s5);
				}

				String s6 = "square6";
				if (xCord == 2 && yCord == 1) { // If the sixth square is pressed, update the model
					// System.out.println(s6);
					model.updateModel(6, s6);
				}

				String s7 = "square7";
				if (xCord == 0 && yCord == 2) { // If the seventh square is pressed, update the model
					// System.out.println(s7);
					model.updateModel(7, s7);
				}

				String s8 = "square8";
				if (xCord == 1 && yCord == 2) { // If the eight square is pressed, update the model
					// System.out.println(s8);
					model.updateModel(8, s8);
				}

				String s9 = "square9";
				if (xCord == 2 && yCord == 2) { // If the ninth square is pressed, update the model
					// System.out.println(s9);
					model.updateModel(9, s9);
				}
			}
		});
	}

	/*
	 * Creates a new square for each element in the model. Checks if the board is in
	 * a winning state.
	 */
	public void updateSquares() {
		if (model.size() > 0) {
			for (int i = 0; i < model.size(); i++) { // Iterates through each integer in the model
				String square = model.getSquare(i); // Gets the name of the square at index i in the model
				int shape = model.getNumber(i); // Gets the integer at index i in the model

				Square spot = new Square(square, shape); // Creates a square with the appropriate name and shape
				squares.add(spot); // Adds the square to the ArrayList
			}
			winningState(); // Checks to see if the board is in a winning state
		}
	}

	/*
	 * Determines whether the board is in a winning state
	 */
	public void winningState() {
		if (frame.isEnabled()) {
			/*
			boolean state1 = false; // All same shape in top row
			boolean state2 = false; // All same shape in middle row
			boolean state3 = false; // All same shape in bottom row
			boolean state4 = false; // All same shape in left column
			boolean state5 = false; // All same shape in middle column
			boolean state6 = false; // All same shape in right column
			boolean state7 = false; // All same shape in main diagonal
			boolean state8 = false; // All same shape in other diagonal
			*/
			
			boolean x1 = false; // True if there's an X in square 1
			boolean x2 = false; // True if there's an X in square 2
			boolean x3 = false; // True if there's an X in square 3
			boolean x4 = false; // True if there's an X in square 4
			boolean x5 = false; // True if there's an X in square 5
			boolean x6 = false; // True if there's an X in square 6
			boolean x7 = false; // True if there's an X in square 7
			boolean x8 = false; // True if there's an X in square 8
			boolean x9 = false; // True if there's an X in square 9
			boolean o1 = false; // True if there's an O in square 1
			boolean o2 = false; // True if there's an O in square 2
			boolean o3 = false; // True if there's an O in square 3
			boolean o4 = false; // True if there's an O in square 4
			boolean o5 = false; // True if there's an O in square 5
			boolean o6 = false; // True if there's an O in square 6
			boolean o7 = false; // True if there's an O in square 7
			boolean o8 = false; // True if there's an O in square 8
			boolean o9 = false; // True if there's an O in square 9

			boolean xWinner = false; // Indicates that player 1 won the game
			boolean oWinner = false; // Indicates that player 2 won the game

			for (Square square : squares) { // Iterates through each square in squares
				if (square.getName().equals("square1") && square.getShapeNumber() == 0) { // Checks if there's an X in
																							// square 1
					x1 = true;
				} else if (square.getName().equals("square1") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 1
					o1 = true;
				} else if (square.getName().equals("square2") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 2
					x2 = true;
				} else if (square.getName().equals("square2") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 2
					o2 = true;
				} else if (square.getName().equals("square3") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 3
					x3 = true;
				} else if (square.getName().equals("square3") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 3
					o3 = true;
				} else if (square.getName().equals("square4") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 4
					x4 = true;
				} else if (square.getName().equals("square4") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 4
					o4 = true;
				} else if (square.getName().equals("square5") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 5
					x5 = true;
				} else if (square.getName().equals("square5") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 5
					o5 = true;
				} else if (square.getName().equals("square6") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 6
					x6 = true;
				} else if (square.getName().equals("square6") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 6
					o6 = true;
				} else if (square.getName().equals("square7") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 7
					x7 = true;
				} else if (square.getName().equals("square7") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 7
					o7 = true;
				} else if (square.getName().equals("square8") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 8
					x8 = true;
				} else if (square.getName().equals("square8") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 8
					o8 = true;
				} else if (square.getName().equals("square9") && square.getShapeNumber() == 0) { // Checks if there's an
																									// X in square 9
					x9 = true;
				} else if (square.getName().equals("square9") && square.getShapeNumber() == 1) { // Checks if there's an
																									// O in square 9
					o9 = true;
				}
			}

			if ((x1 && x2 && x3) || (o1 && o2 && o3)) { // Checks to see if the board is in state1
				//state1 = true;
				disableFrame();

				if (x1 && x2 && x3) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x4 && x5 && x6) || (o4 && o5 && o6)) { // Checks to see if the board is in state2
				//state2 = true;
				disableFrame();

				if (x4 && x5 && x6) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x7 && x8 && x9) || (o7 && o8 && o9)) { // Checks to see if the board is in state3
				//state3 = true;
				disableFrame();

				if (x7 && x8 && x9) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x1 && x4 && x7) || (o1 && o4 && o7)) { // Checks to see if the board is in state4
				//state4 = true;
				disableFrame();

				if (x1 && x4 && x7) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x2 && x5 && x8) || (o2 && o5 && o8)) { // Checks to see if the board is in state5
				//state5 = true;
				disableFrame();

				if (x2 && x5 && x8) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x3 && x6 && x9) || (o3 && o6 && o9)) { // Checks to see if the board is in state6
				//state6 = true;
				disableFrame();

				if (x3 && x6 && x9) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x1 && x5 && x9) || (o1 && o5 && o9)) { // Checks to see if the board is in state7
				//state7 = true;
				disableFrame();

				if (x1 && x5 && x9) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}
			} else if ((x3 && x5 && x7) || (o3 && o5 && o7)) { // Checks to see if the board is in state8
				//state8 = true;
				disableFrame();

				if (x3 && x5 && x7) {
					xWinner = true; // Indicates that player 1 won the game
				} else {
					oWinner = true; // Indicates that player 2 won the game
				}

			}

			if (model.size() == 9 && !xWinner && !oWinner) { // Checks to see if there's a tie
				disableFrame();
				Label tie = new Label("It's a tie!");
				Font smallFont = new Font("Courier", Font.BOLD, 25);
				tie.setFont(smallFont);

				JFrame tieFrame = new JFrame();
				tieFrame.add(tie);
				tieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				tieFrame.pack();
				tieFrame.setVisible(true);

				System.out.println("It's a tie!");
			} else if (xWinner) {
				Label line1 = new Label("   We have a winner!");
				Label line2 = new Label("Congragulations Player 1!");
				Font smallFont = new Font("Courier", Font.BOLD, 25);
				line1.setFont(smallFont);
				line2.setFont(smallFont);

				JFrame winningFrame = new JFrame();
				winningFrame.add(line1, BorderLayout.CENTER);
				winningFrame.add(line2, BorderLayout.SOUTH);
				winningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				winningFrame.pack();
				winningFrame.setVisible(true);

				System.out.println("We have a winner!");

			} else if (oWinner) {
				Label line1 = new Label("    We have a winner!");
				Label line2 = new Label("Congragulations Player 2!");
				Font smallFont = new Font("Courier", Font.BOLD, 25);
				line1.setFont(smallFont);
				line2.setFont(smallFont);

				JFrame winningFrame = new JFrame();
				winningFrame.add(line1, BorderLayout.CENTER);
				winningFrame.add(line2, BorderLayout.SOUTH);
				winningFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				winningFrame.pack();
				winningFrame.setVisible(true);

				System.out.println("We have a winner!");
			}
			squares.clear(); // Resets the ArrayList of squares
		}
	}

	/*
	 * Disables the frame this panel is in
	 */
	public void disableFrame() {
		frame.setEnabled(false);
	}

	/*
	 * Sets the visibility of the frame to true
	 */
	public void visibility() {
		frame.setVisible(true);
	}

	/*
	 * Enables the frame this panel is in
	 */
	public void enableFrame() {
		frame.setEnabled(true);
	}

	/*
	 * Gets the classe's unique button
	 * 
	 * @return the classes button
	 */
	public abstract JButton getInitialButton();

	/*
	 * When the model is updated, this panel is redrawn and the ArrayList squares is
	 * updated to see if the board is in a winning state
	 */
	public void update(Observable o, Object arg) {
		if (o == model) {
			repaint(); // Redraws the panel
			updateSquares(); // Updates the squares
		}
	}
}