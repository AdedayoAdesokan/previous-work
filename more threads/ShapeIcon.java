import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * An icon that contains a moveable shape.
 */
public class ShapeIcon implements Icon {
	private int width;
	private int height;
	private MoveableShape shape;
	
	/*
	 * Creates a ShapeIcon 
	 * @param shape the MoveableShape this icon draws
	 * @param width the width of this icon
	 * @param height the height of this icon
	 */
	public ShapeIcon(MoveableShape shape, int width, int height) {
		this.shape = shape;
		this.width = width;
		this.height = height;
	}

	/*
	 * Gets the icon's width
	 * @return the width of the icon
	 */
	public int getIconWidth() {
		return width;
	}

	/*
	 * Gets the icon's height
	 * @return the height of the icon
	 */
	public int getIconHeight() {
		return height;
	}

	/*
	 * Paints the icon
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		shape.draw(g2);
	}
}