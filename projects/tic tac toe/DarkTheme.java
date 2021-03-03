import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

public class DarkTheme extends BoardViewer {
	private static final Color LIGHT_BLUE = new Color(51, 153, 255);

	/*
	 * A constructor that creates a dark theme of the board
	 * 
	 * @param aModel the Board that this panel observes
	 */
	public DarkTheme(Board aModel) {
		super(aModel, Color.BLACK, Color.BLUE, LIGHT_BLUE, LIGHT_BLUE);
	}

	/*
	 * Get this board's specific button.
	 * 
	 * @return this board's button.
	 */
	public JButton getInitialButton() {
		JButton darkTheme = new JButton("Dark Theme");
		darkTheme.setBackground(Color.BLACK);
		darkTheme.setForeground(LIGHT_BLUE);
		return darkTheme;
	}
}