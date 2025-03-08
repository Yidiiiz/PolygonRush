package game;
import java.util.ArrayList;

public class Map {
	public ArrayList<MapElement> mapArray;
	
	public Map() {
		mapArray = new ArrayList<MapElement>();
	}
	
	public void addElement(MapElement e) {
		mapArray.add(e);
	}
	
	
}
