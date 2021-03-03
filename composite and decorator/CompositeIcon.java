import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Icon;

public class CompositeIcon implements Icon {
	private ArrayList<Icon> list;
	
	/*
	 * Constructor initializes the arrayList.
	 */
	public CompositeIcon()
	{
		list = new ArrayList<>();
	}
	
	/*
	 * Adds the given icon to the arrayList.
	 * @param icon the icon you want to add to the list
	 */
	public void addIcon(Icon icon)
	{
		list.add(icon);
	}
	
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		int xCord = x;
		for (Icon icon : list)
		{
			icon.paintIcon(c, g, xCord, y);  //calls the paintIcon method on each icon in the arrayList.
			xCord = xCord + icon.getIconWidth();  //Adjusts the x-coordinate so that the icons are drawn in a horizontal row.
		}
	}

	/*
	 * Gets the combined width of all the icons in the arrayList.
	 * @return the width of all the icons
	 */
	public int getIconWidth() {
		int width = 0;
		for (Icon icon : list)
		{
			width = width + icon.getIconWidth();
		}
		return width;
	}

	/*
	 * Gets the combined height of all the icons in the arrayList.
	 * @return the height of all the icons
	 */
	public int getIconHeight() {
		int height = 0;
		for (Icon icon : list)
		{
			height = height + icon.getIconHeight();
		}
		return height;
	}
}