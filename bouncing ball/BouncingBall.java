import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class BouncingBall implements MoveableShape {
	
	private int x;
	private int y;
	private int width;
	
	/*
	 * Constructs a ball that bounces randomly
	 * @param x the left of the bounding rectangle
	 * @param y the top of the the bounding rectangle
	 * @param width the width of the bounding rectangle
	 */
	public BouncingBall(int x, int y, int width)
	{
		this.x = x;
	    this.y = y;
	    this.width = width;
	}
	
	/*
	 * Moves the ball a random distance and height
	 */
	public void move()
	{
	    Random rng = new Random();        // creates a new random object rng(random number generator)
	    int height = rng.nextInt(100);    // the height the ball will bounce
	    int distance = rng.nextInt(100);  // the distance the ball will move
		x = x + distance;
	    if (y > height*5)                 // checks to see if the ball reached the maximum height, at which point the ball changes direction
	    {
	    	y = y - height;
	    }
	    else
	    {
			y = y + height;
	    }
	}
	
	/*
	 * Draws the ball
	 */
	public void draw(Graphics2D g2)
	{
		Ellipse2D.Double ball = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 6, width / 6);
		g2.draw(ball);
	}
}
