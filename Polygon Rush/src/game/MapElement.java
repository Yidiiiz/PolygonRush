package game;

import java.awt.Color;

// MapElement interface
public class MapElement extends Polygon {
	// Whether player will be reset when colliding with this block
	public boolean resetPlayer = false;
	
	// Color of this element
	public Color color;
	
	// Constructor for MapElement, creates polygon and initializes variables
	public MapElement(Point[] inShape, Point inPosition, double inRotation, boolean resetPlayer, Color color) {
		super(inShape, inPosition, inRotation);
		this.resetPlayer = resetPlayer;
		this.color = color;
	}
}
