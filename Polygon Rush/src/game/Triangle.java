package game;

import java.awt.*;

public class Triangle extends MapElement {
	// Constructor for platform, creates the shape of the platform and initializes
	public Triangle(int size, Point inPosition, Color color) {
		super(getElement(size), inPosition, 0, true, color);
	}

	// Draws the platform shape based off of the size provided
	public static Point[] getElement(int size) {
		return new Point[] { new Point(size / 2, 0), new Point(0, size), new Point(size, size) };
	}
}