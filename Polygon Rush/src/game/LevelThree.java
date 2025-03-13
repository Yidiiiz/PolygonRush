package game;
import java.awt.Color;

public class LevelThree implements Level {
	private static int levelNumber = 3;
	private Map map;
	private int width;
	private int height;
	private int finish = 4500;
	
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
		addObstacle(new Platform(30, new Point(width-400 + 10, height - 160), 0, Color.black));
		addObstacle(new Platform(30, new Point(840, height - 160), 0, Color.black));
		
		for (int i = 0; i < 8; i++) {
			addObstacle(new Spike(30, new Point(750 + (30*i), height - 130), 0, Color.black));
   		}
		
		addObstacle(new Platform(30, new Point(1260, height - 160), 0, Color.black));
		addObstacle(new Platform(30, new Point(1290, height - 160), 0, Color.black));
		
		for (int i = 0; i < 8; i++) {
			addObstacle(new Spike(30, new Point(1200 + (30*i), height - 130), 0, Color.black));
   		}
		
		for (int i = 0; i < 8; i++) {
			addObstacle(new Platform(30, new Point(1700+150*i, height - 100 - 30*(i+2)), 0, Color.black));
   		}
   		for (int i = 0; i < 44; i++) {
   			addObstacle(new Spike(30, new Point(1600+30*i, height - 100 - 30), 0, Color.black));
   		}
   		
   		for (int i = 0; i < 5; i++) {
   			addObstacle(new Spike(30, new Point(3090+30*i, height - 100 - 61), 0, Color.black));
   		}
   		
   		for (int i = 0; i < 3; i++) {
   			addObstacle(new Spike(30, new Point(3300+30*i, height - 100 - 30), 0, Color.black));
   		}
   		
   		for (int i = 0; i < 5; i++) {
   			addObstacle(new Spike(30, new Point(3470+30*i, height - 100 - 61), 0, Color.black));
   		}
   		
   		for (int i = 0; i < 3; i++) {
   			addObstacle(new Spike(30, new Point(3680+30*i, height - 100 - 30), 0, Color.black));
   		}
   		
   		for (int i = 0; i < 5; i++) {
   			addObstacle(new Spike(30, new Point(3850+30*i, height - 100 - 61), 0, Color.black));
   		}
		
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