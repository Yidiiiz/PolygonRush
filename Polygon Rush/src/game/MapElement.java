package game;

import java.awt.Color;

// MapElement interface
public class MapElement extends Polygon {
	// Whether player will be reset when colliding with this block
	private boolean resetPlayer = false;

	// Color of this element
	private Color color;

	// Constructor for MapElement, creates polygon and initializes variables
	public MapElement(Point[] inShape, Point inPosition, double inRotation, boolean resetPlayer, Color color) {
		super(inShape, inPosition, inRotation);
		this.resetPlayer = resetPlayer;
		this.color = color;
	}
	
	// Getter for resetPlayer
	public boolean getResetPlayer() {
		return resetPlayer;
	}
	
	// Getter for color
	public Color getColor() {
		return color;
	}
}
