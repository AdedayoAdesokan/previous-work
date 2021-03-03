import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;

public class BoxedIcon implements Icon {
	private Icon item;

	/*
	 * Constructor initializes item.
	 */
	public BoxedIcon(Icon icon) {
		item = icon;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		Rectangle2D.Double box = new Rectangle2D.Double(x, y, item.getIconWidth() + 20, item.getIconHeight() + 20);	 //The rectangle that surrounds the icon.
		g2.draw(box);
		item.paintIcon(c, g, x + 10, y + 10);  //Draws the icon with a 10 pixel gap from the box.
	}

	@Override
	/*
	 * Gets the width of the box surrounding the icon + 1 to ensure that the full box is shown.
	 * @return the width of the box surrounding the icon + 1
	 */
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return item.getIconWidth() + 21;
	}
	
	@Override
	/*
	 * Gets the height of the box that surrounds the icon + 1 to ensure that the full box is shown.
	 * @return the height of the box that surrounds the icon + 1
	 */
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return item.getIconHeight() + 21;
	}

}
