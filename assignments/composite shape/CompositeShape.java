import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public class CompositeShape implements Shape {
	private ArrayList<Shape> list;  //An arrayList that holds shapes.
	
	/*
	 * Constructor that initializes the arrayList.
	 */
	public CompositeShape()
	{
		list = new ArrayList<>();
	}

	/*
	 * Adds the given shape to the arrayList.
	 * @param shape the shape you want to add to the arrayList
	 */
	public void add(Shape shape) {
		list.add(shape);
	}

	@Override
	public Rectangle getBounds() {
		int x = 0;
		int y = 0;
		int height = 0;
		int width = 0;
		for (Shape shape : list) {  
			x = x + shape.getBounds().x;  //Adds up all the x-coordinates of each shape in list.
			y = y + shape.getBounds().y;  //Adds up all the y-coordinates of each shape in list.
			height = height + shape.getBounds().height;  //Adds up all the heights of each shape in list.
			width = width + shape.getBounds().width;  //Adds up all the widths of each shape in list.
		}
		return new Rectangle(x, y, width, height);  //Uses the combined x, y, width, and height to create a rectangle big enough to sorround all the shapes.
	}

	@Override
	public Rectangle2D getBounds2D() {
		double x = 0;
		double y = 0;
		double height = 0;
		double width = 0;
		for (Shape shape : list) {
			x = x + shape.getBounds2D().getX();  //Adds up all the x-coordinates of each shape in list.
			y = y + shape.getBounds2D().getY();  //Adds up all the y-coordinates of each shape in list.
			height = height + shape.getBounds2D().getHeight();  //Adds up all the heights of each shape in list.
			width = width + shape.getBounds2D().getWidth();  //Adds up all the widths of each shape in list.
		}
		return new Rectangle2D.Float((float) x, (float) y, (float) width, (float) height);  //Uses the combined x, y, width, and height to create a 2D rectangle big enough to sorround all the shapes.
	}

	@Override
	public boolean contains(double x, double y) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.contains(x, y);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that contains the given x, y coordinates.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public boolean contains(Point2D p) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.contains(p);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that contains the 2D point.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.intersects(x, y, w, h);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that intersects the given coordinates.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.intersects(r);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that intersects the given 2D rectangle.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.contains(x, y, w, h);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that contains the given coordinates.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public boolean contains(Rectangle2D r) {
		boolean bool = false;
		for (Shape shape : list) {
			bool = shape.contains(r);  //Iterates through all the shapes in the arrayList and stops if it finds a shape that contains the given 2D rectangle.
			if (bool == true) {
				break;
			}
		}
		return bool;
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return new PathIterator() {
			private PathIterator currentPathIterator;
			private Iterator<Shape> shapesIterator;
			
			//Constructor for the anonymous class.
			{
				shapesIterator = list.iterator();
				nextShape();
			}
			
			public void nextShape() {
				if (shapesIterator.hasNext()) {
					currentPathIterator = shapesIterator.next().getPathIterator(at);
				} else {
					currentPathIterator = null;
				}
			}

			@Override
			public void next() {
                currentPathIterator.next();
			}

			@Override
			public boolean isDone() {
				if (currentPathIterator == null)
					return true;
				if (!currentPathIterator.isDone())
					return false;
				nextShape();
				return isDone();
			}

			@Override
			public int getWindingRule() {
				return WIND_NON_ZERO;
			}

			@Override
			public int currentSegment(double[] coords) {
                return currentPathIterator.currentSegment(coords);
			}

			@Override
			public int currentSegment(float[] coords) {
                return currentPathIterator.currentSegment(coords);
			}
		};
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return new PathIterator() {
			private PathIterator currentPathIterator;
			private Iterator<Shape> shapesIterator;
			
			//Constructor for the anonymous class.
			{
				shapesIterator = list.iterator();
				nextShape();
			}

			public void nextShape() {
				if (shapesIterator.hasNext()) {
					currentPathIterator = shapesIterator.next().getPathIterator(at, flatness);
				} else {
					currentPathIterator = null;
				}
			}

			@Override
			public void next() {
                currentPathIterator.next();
			}

			@Override
			public boolean isDone() {
				if (currentPathIterator == null)
					return true;
				if (!currentPathIterator.isDone())
					return false;
				nextShape();
				return isDone();
			}

			@Override
			public int getWindingRule() {
				return WIND_NON_ZERO;
			}

			@Override
			public int currentSegment(double[] coords) {
                return currentPathIterator.currentSegment(coords);
			}

			@Override
			public int currentSegment(float[] coords) {
                return currentPathIterator.currentSegment(coords);
			}
		};
	}
}