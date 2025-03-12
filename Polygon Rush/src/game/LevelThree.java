package game;
import java.awt.Color;

public class LevelThree implements Level {
	private static int levelNumber = 3;
	private Map map;
	private int width;
	private int height;
	private int finish = 4000;
	
	public LevelThree(int width, int height) {
		map = new Map();
		this.width = width;
		this.height = height;
	}
	
	// Adds an obstacle to the map
	public void addObstacle(MapElement element) {
		map.addElement(element);
	}
	
	// Resets Map to original state and returns
	public Map resetMap() {
		map = new Map();
		addObstacle(new Triangle(30, new Point(width + 500, height - 100 - 60), Color.black));
		addObstacle(new Triangle(30, new Point(width + 525, height - 100 - 60), Color.black));
		addObstacle(new Triangle(30, new Point(width + 550, height - 100 - 60), Color.black));
		addObstacle(new Triangle(30, new Point(width + 700, height - 100 - 60), Color.black));
		addObstacle(new Triangle(30, new Point(width + 800, height - 100 - 90), Color.black));
		addObstacle(new Triangle(30, new Point(width + 900, height - 100 - 120), Color.black));
		
		return map;
	}
	
	// Getter for map
	public Map getMap() {
		resetMap();
		return map;
	}
	
	// Getter for level number
	public int getLevelNumber() {
		return levelNumber;
	}
	
	// Getter for finish marker
	public int getFinish() {
		return finish;
	}
}