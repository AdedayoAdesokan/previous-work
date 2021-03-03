public class Square{
	private String name;
	private int shape;
	
	/*
	 * A constructor that creates a Square with a given name and shape
	 * @param name the name of the square
	 * @param shape a 0 or 1 indicating an X or an O
	 */
	public Square(String name, int shape) {
		this.name = name;
		this.shape = shape;
	}
	
	/*
	 * Gets the name of the Square
	 * @return the name of the Square
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Gets the shape of the Square as a number
	 * @return the number associated with the Square's shape
	 */
	public int getShapeNumber() {
		return shape;
	}
	
	/*
	 * Gets the name of the Square's shape
	 * @return an X or O depending in the number
	 */
	public String getShape() {
		String shape = null;
		if (this.shape == 0) {
			shape = "x";
		}
		else if (this.shape == 1) {
			shape = "o";
		}
		return shape;
	}
}