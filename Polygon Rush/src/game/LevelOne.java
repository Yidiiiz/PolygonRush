package game;
import java.awt.Color;

public class LevelOne implements Level {
	private static int levelNumber = 1;
	private Map map;
	private int width;
	private int height;
	private int winPoint = 4000;
	
	public LevelOne(int width, int height) {
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
		addObstacle(new Platform(30, new Point(width - 500, height - 100 - 30), 0, Color.black));
        addObstacle(new Spike(30, new Point(width - 100, height - 100 - 30), 0, Color.black));
        addObstacle(new Spike(30, new Point(width - 125, height - 100 - 30), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 275, height - 100 - 75), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 450, height - 100 - 75), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 500, height - 100 - 60), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 525, height - 100 - 60), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 550, height - 100 - 60), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 600, height - 100 - 30), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 600, height - 100 - 60), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 600, height - 100 - 90), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 700, height - 100 - 60), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 800, height - 100 - 90), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 900, height - 100 - 120), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 30), 0, Color.black));
        addObstacle(new Triangle(30, new Point(width + 1200, height - 100 - 60), Color.black));
        addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 200), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1400, height - 100 - 75), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 30), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 60), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 90), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 120), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 150), 0, Color.black));
        addObstacle(new Triangle(30, new Point(width + 1500, height - 100 - 180), Color.black));
        addObstacle(new Spike(30, new Point(width + 1700, height - 100 - 90), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 1725, height - 100 - 90), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 1750, height - 100 - 90), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 2000, height - 100 - 30), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 2075, height - 100 - 75), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 2150, height - 100 - 90), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 2225, height - 100 - 135), 0, Color.black));
        addObstacle(new Platform(30, new Point(width + 2300, height - 100 - 150), 0, Color.black));
        addObstacle(new Spike(30, new Point(width + 2350, height - 100 - 175), 0, Color.black));
        
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
}