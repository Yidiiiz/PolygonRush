package game;
import java.awt.Color;

public class LevelTwo implements Level {
	private static int levelNumber = 2;
	private Map map;
	private int width;
	private int height;
	private int winPoint = 4000;
	
	public LevelTwo(int width, int height) {
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
		addObstacle(new Triangle(30, new Point(width, height - 100 - 30), Color.black));
		addObstacle(new Triangle(30, new Point(width + 160, height - 100 - 30), Color.black));
		addObstacle(new Triangle(30, new Point(width + 320 , height - 100 - 30), Color.black));
		addObstacle(new Triangle(30, new Point(width + 480, height - 100 - 30), Color.black));
		addObstacle(new Triangle(30, new Point(width + 640 , height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 800, height - 100 - 75), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 800, height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 960, height - 100 - 75), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 960, height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 1120, height - 100 - 75), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 1120, height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 1280, height - 100 - 75), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 1280, height - 100 - 30), Color.black));
		addObstacle(new Platform(30, new Point(width + 1470, height - 100 - 90), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 1470, height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 1500, height - 100 - 30), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 1530, height - 100 - 30), Color.black));
		addObstacle(new Spike(30, new Point(width + 1560, height - 100 - 30), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 1590, height - 100 - 30), Color.black));
  		addObstacle(new Spike(30, new Point(width + 1620, height - 100 - 30), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 1630, height - 100 - 90), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 1660, height - 100 - 30), Color.black));
  		addObstacle(new Spike(30, new Point(width + 1690, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 1720, height - 100 - 30), Color.black));
  		addObstacle(new Spike(30, new Point(width + 1750, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 1780, height - 100 - 30), Color.black));
  		addObstacle(new Platform(30, new Point(width + 1790, height - 100 - 90), 0, Color.black));
  		addObstacle(new Spike(30, new Point(width + 1810, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 1840, height - 100 - 30), Color.black));
  		addObstacle(new Platform(30, new Point(width + 1950, height - 100 - 90), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2110, height - 100 - 110), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2270, height - 100 - 130), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2430, height - 100 - 140), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2590, height - 100 - 160), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2750, height - 100 - 175), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 2910, height - 100 - 90), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 3070, height - 100 - 90), 0, Color.black));
  		addObstacle(new Platform(30, new Point(width + 3230, height - 100 - 90), 0, Color.black));
  		addObstacle(new Spike(30, new Point(width + 2430, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2430, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2460, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2460, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2490, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2490, height - 100 - 45), Color.black));
	  	addObstacle(new Spike(30, new Point(width + 2520, height - 100 - 30), 0, Color.black));
	  	addObstacle(new Triangle(30, new Point(width + 2520, height - 100 - 45), Color.black));
	  	addObstacle(new Spike(30, new Point(width + 2550, height - 100 - 30), 0, Color.black));
	  	addObstacle(new Triangle(30, new Point(width + 2550, height - 100 - 45), Color.black));
	  	addObstacle(new Spike(30, new Point(width + 2580, height - 100 - 30), 0, Color.black));
	  	addObstacle(new Platform(30, new Point(width + 3230, height - 100 - 90), 0, Color.black));
	  	addObstacle(new Spike(30, new Point(width + 2430, height - 100 - 30), 0, Color.black));
	  	addObstacle(new Triangle(30, new Point(width + 2430, height - 100 - 45), Color.black));
	  	addObstacle(new Spike(30, new Point(width + 2460, height - 100 - 30), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 2460, height - 100 - 45), Color.black));
		addObstacle(new Spike(30, new Point(width + 2490, height - 100 - 30), 0, Color.black));
		addObstacle(new Triangle(30, new Point(width + 2490, height - 100 - 45), Color.black));
		addObstacle(new Spike(30, new Point(width + 2520, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2520, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2550, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2550, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2580, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2580, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2610, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2610, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2640, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2640, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2670, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2670, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2700, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2700, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2730, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2730, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2760, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2760, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2790, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2790, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2820, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2820, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2850, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2850, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2880, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2880, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2910, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2910, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2940, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2940, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 2970, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 2970, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3000, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3000, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3030, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3030, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3060, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3060, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3090, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3090, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3120, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3120, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3150, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3150, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3180, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3180, height - 100 - 45), Color.black));
  		addObstacle(new Spike(30, new Point(width + 3210, height - 100 - 30), 0, Color.black));
  		addObstacle(new Triangle(30, new Point(width + 3210, height - 100 - 45), Color.black));
  		
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