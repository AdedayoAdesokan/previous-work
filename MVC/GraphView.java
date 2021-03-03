import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class GraphView extends JPanel implements Observer {

	private static final int HEIGHT = 20;  //The set height of each bar
	private DataModel model;  //A reference to the data model

	/*
	 * Constructor that initializes model.
	 */
	public GraphView(DataModel aModel)
	{
		model = aModel;
	}
	
	/*
	 * Creates a bar for each piece of data in the model.
	 * Adds a mouse listener that gets the point of a click and updates the data in the model to reflect the point.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int yCord = 0;  //the initial value for the y-coordinate
		for (int i = 0; i < model.getLength(); i++)
		{
			int width = model.getInt(i);
			Rectangle2D rectangle = new Rectangle2D.Double(0, yCord, width, HEIGHT);  //creates the bar with a width that reflects the data in the model.
			g2.draw(rectangle);
			yCord = yCord + 20;  //moves the position of the next bar down
		}
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				int index = event.getPoint().y / HEIGHT;  //gets the y-coordinate of the click and divides it by the set height for each bar in order to determine which bar was updated.
				int value = event.getPoint().x;  //gets the updated value for the bar.
				model.updateInt(value, index);  //updates the data in the model to reflect the new width of the bar.
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == model)
		{
			repaint();  //Redraws the bars when the model has been updated.
		}
	}
}