import java.awt.*;
import java.awt.geom.*;
import javax.swing.JLabel;

/**
 * A car that can be moved around.
 */
public class CarShape implements MoveableShape, Runnable {
	private int x;
	private int y;
	private int width;
	private JLabel label;
	private int sleepTime;
	private static final int ICON_WIDTH = 1500;
	private static final int ICON_HEIGHT = 80;

	/**
	 * Constructs a car item. 
	 * @param x the left of the bounding rectangle
	 * @param y the top of the bounding rectangle
	 * @param width the width of the bounding rectangle
	 * @param sleepTime the amount of delay on the shape
	 */
	public CarShape(int x, int y, int width, int sleepTime) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.sleepTime = sleepTime;
		
		//Create a label for this shape that holds the icon
		ShapeIcon icon = new ShapeIcon(this, ICON_WIDTH, ICON_HEIGHT);
		label = new JLabel(icon);
	}

	/*
	 * Moves the shape forward by 1
	 */
	public void move() {
		x++;
	}

	/*
	 * Draws the shape
	 */
	public void draw(Graphics2D g2) {
		Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, width / 6);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 6, width / 6);
		Ellipse2D.Double rearTire = new Ellipse2D.Double(x + width * 2 / 3, y + width / 3, width / 6, width / 6);

		// The bottom of the front windshield
		Point2D.Double r1 = new Point2D.Double(x + width / 6, y + width / 6);
		// The front of the roof
		Point2D.Double r2 = new Point2D.Double(x + width / 3, y);
		// The rear of the roof
		Point2D.Double r3 = new Point2D.Double(x + width * 2 / 3, y);
		// The bottom of the rear windshield
		Point2D.Double r4 = new Point2D.Double(x + width * 5 / 6, y + width / 6);
		Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
		Line2D.Double roofTop = new Line2D.Double(r2, r3);
		Line2D.Double rearWindshield = new Line2D.Double(r3, r4);

		g2.draw(body);
		g2.draw(frontTire);
		g2.draw(rearTire);
		g2.draw(frontWindshield);
		g2.draw(roofTop);
		g2.draw(rearWindshield);
	}

	/*
	 * Gets the shape's label
	 * @return the shape's label
	 */
	public JLabel getLabel() {
		return this.label;
	}

	/*
	 * Moves the shape
	 */
	public void run() {
		int gap = 5;  //A small space so the shape doesn't stop right at the edge of the frame
		int stoppingPoint = ICON_WIDTH - this.width - x - gap;  //Stops the shape before the frame ends
		for (int i = 0; i < stoppingPoint; i++) {  
			this.move();  //Moves the shape forward by 1
			label.repaint();  //Repaints the shape
			try {
				Thread.sleep(sleepTime);  //Determines how fast the shape moves
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}