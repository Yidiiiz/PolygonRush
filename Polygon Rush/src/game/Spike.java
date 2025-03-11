package game;
import java.awt.*;

public class Spike extends MapElement {
	// Constructor for spike, creates the shape of the spike and initializes the position, rotation, and color
	public Spike(int size, Point inPosition, int rotation, Color color) {
		super(getElement(size), inPosition, rotation, true, color);
	}
	
	// Draws the spike shape based off of the size provided
	public static Point[] getElement(int size) {
		return new Point[] {new Point(size/2, 0), new Point(size, size/2), new Point(size/2, size), new Point(0, size/2)};
	}
}