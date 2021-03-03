import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * This program implements an animation that moves a car shape.
 */
public class AnimationTester {
	private static final int CAR_WIDTH = 100;

	public static void main(String[] args) {
		// Creates an array list of Moveable Shapes
		ArrayList<MoveableShape> shapes = new ArrayList<>();

		// Creates multiple shapes and adds them to the array list
		MoveableShape shape1 = new CarShape(0, 5, CAR_WIDTH, 5);
		MoveableShape shape2 = new CarShape(50, 0, CAR_WIDTH, 11);
		MoveableShape shape3 = new CarShape(10, 0, CAR_WIDTH, 9);
		MoveableShape shape4 = new CarShape(0, 0, CAR_WIDTH, 20);
		MoveableShape shape5 = new CarShape(0, 0, CAR_WIDTH, 3);
		shapes.add(shape1);
		shapes.add(shape2);
		shapes.add(shape3);
		shapes.add(shape4);
		shapes.add(shape5);

		// Creates a frame and sets its layout
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(shapes.size(), 1)); // Uses a grid layout with the same number of rows as the
															// number of shapes in the array list
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT); // Orients the shapes from left to right
																			// (which is really top to bottom because
																			// there's only 1 column)

		// Iterates through the list of shapes and makes them move
		for (MoveableShape shape : shapes) {
			frame.add(shape.getLabel()); // Adds the shape's label to the frame
			Thread thread = new Thread(shape); // Creates a thread for each shape
			thread.start(); // Starts each thread
		}

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}