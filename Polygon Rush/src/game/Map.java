package game;
import java.util.ArrayList;

// Map class, stores all map elements for the map
public class Map {
	// Array List for all map elements
	public ArrayList<MapElement> mapArray;
	
	// Constructor for map, initializes mapArray
	public Map() {
		mapArray = new ArrayList<MapElement>();
	}
	
	// Adds a map element to the map
	public void addElement(MapElement e) {
		mapArray.add(e);
	}
}
