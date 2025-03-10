package game;

import java.awt.*;

// Platform class, creates a platform for the player
public class Platform extends MapElement {
	// Constructor for platform, creates the shape of the platform and initializes
	// the position, rotation, and color
	public Platform(int size, Point inPosition, int rotation, Color color) {
		super(getElement(size), inPosition, rotation, false, color);
	}

	// Draws the platform shape based off of the size provided
	public static Point[] getElement(int size) {
		return new Point[] { new Point(0, 0), new Point(0, size), new Point(size, size), new Point(size, 0) };
	}
}
