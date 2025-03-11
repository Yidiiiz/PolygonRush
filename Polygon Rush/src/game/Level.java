package game;

public interface Level {
	// Function for adding an obstacle
	public void addObstacle(MapElement element);
	
	// Function for resetting the map
	public void resetMap();
	
	// Getter for map
	public Map getMap();
}
