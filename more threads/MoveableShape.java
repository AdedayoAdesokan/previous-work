import java.awt.*;

import javax.swing.JLabel;

/**
 * A shape that can be moved around.
 */
public interface MoveableShape extends Runnable {
	/**
	 * Draws the shape.
	 * 
	 * @param g2 the graphics context
	 */
	public void draw(Graphics2D g2);

	/**
	 * Moves the shape. It is up to the shape to move itself, for example by
	 * tracking the time since its last movement, its position, and velocity.
	 */
	public void move();
	
	/*
	 * Gets the shape's label
	 */
	public JLabel getLabel();
}