package game;
import java.awt.*;

public class Platform extends Polygon implements MapElement{
	public boolean resetPlayer = false;	
	public Color color;
	
	public Platform(int size, Point inPosition, int rotation, Color color) {
		super(getElement(size), inPosition, rotation);
		this.color = color;
	}
	
	public static Point[] getElement(int size) {
		return new Point[] {new Point(0, 0), new Point(0, size), new Point(size, size), new Point(size, 0)};
	}
}
