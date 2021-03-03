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

public class RainbowTheme extends BoardViewer {
	private static final Color LIGHT_GREEN = new Color(0, 255, 51);

	/*
	 * A constructor that creates a rainbow theme of the board
	 * 
	 * @param aModel the Board that this panel observes
	 */
	public RainbowTheme(Board aModel) {
		super(aModel, LIGHT_GREEN, Color.RED, Color.YELLOW, Color.RED);
	}
	
	/*
	 * Get this board's specific button.
	 * 
	 * @return this board's button.
	 */
	public JButton getInitialButton() {
		JButton rainbowTheme = new JButton("Rainbow Theme");
		rainbowTheme.setBackground(Color.YELLOW);
		rainbowTheme.setForeground(Color.GREEN);
		return rainbowTheme;
	}
}