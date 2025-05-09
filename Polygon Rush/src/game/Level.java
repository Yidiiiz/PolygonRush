package game;

// Interface for Levels
public interface Level {
	// Function for adding an obstacle
	public void addObstacle(MapElement element);
	
	// Function for resetting the map
	public Map resetMap();
	
	// Getter for map
	public Map getMap();
	
	// Getter for level number
	public int getLevelNumber();
	
	// Getter for finish marker
	public int getFinish();
}
