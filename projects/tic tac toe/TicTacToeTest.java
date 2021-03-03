import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class TicTacToeTest {
	private static final Color LIGHT_BLUE = new Color(51, 153, 255);

	public static void main(String[] args) {
		Board model = new Board(); // Creates a Board (model)
		NormalTheme nt = new NormalTheme(model); // Creates a normal theme of the board(viewer and controller)
		model.addObserver(nt); // Adds the theme to the model's list of observers
		model.addStyle(nt); // Adds the theme to the model's list of styles

		DarkTheme dt = new DarkTheme(model); // Creates a dark theme of the board(viewer and controller)
		model.addObserver(dt); // Adds the theme to the model's list of observers
		model.addStyle(dt); // Adds the theme to the model's list of styles

		RainbowTheme rt = new RainbowTheme(model); // Creates a rainbow theme of the board(viewer and controller)
		model.addObserver(rt); // Adds the theme to the model's list of observers
		model.addStyle(rt); // Adds the theme to the model's list of styles

		model.initialScreen(); // Creates the initial screen that holds the buttons that allow the user to
								// select a style of the board
	}
}