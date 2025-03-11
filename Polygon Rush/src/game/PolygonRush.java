package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Main class, runs the game
class PolygonRush extends Game implements KeyListener {
	// Serial version use id
	private static final long serialVersionUID = 1;
	
	// Variables for player, floor, and map
	private Player player;
	private Polygon floor;
	private Map map;

	// Speed which map scrolls
	private static int mapSpeed = 5;

	// Selected Level and music
	private int selectedLevel = 1;
	private Music music;

	// Is currently in menu or not
	private boolean isInMenu = true;
	
	// Color array and index
	private int selectedColorIndex = 0;
	private Color[] playerColors = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW };

	// Create an inner class for attempts
	private AttemptTracker attemptTracker = new AttemptTracker();

	// Constructor for game, creates the game and initializes objects
	public PolygonRush() {
		// Creating the game
		super("PolygonRush!", 1200, 600);
		this.setFocusable(true);
		this.requestFocus();

		// Creates music, map, and loads the current level
		music = new Music();
		map = new Map();
		loadLevel(selectedLevel);

		// Creates a key listener to detect key presses
		addKeyListener(this);

		// Creates and initializes the player
		Point[] playerShape = new Point[] { new Point(0, 0), new Point(0, 30), new Point(30, 30), new Point(30, 0) };
		Point playerPosition = new Point(100, height - 100 - 30);
		player = new Player(playerShape, playerPosition, 0);
		
		// Creates and initializes the floor
   		Point[] floorShape = new Point[] { new Point(0, 0), new Point(0, 100), new Point(width, 100), new Point(width, 0) };
   		Point floorPosition = new Point(0, height - 100);
	    floor = new Polygon(floorShape, floorPosition, 0);
   		
   		// Starts the game (the paint function)
   		repaint();
	}

	private void addObstacle(MapElement element) {
		map.addElement(element);
	}

	private void showMenu(Graphics brush) {
		brush.setColor(Color.WHITE);
		brush.fillRect(0, 0, width, height);

		// Draw menu text
		brush.setColor(Color.BLACK);
		brush.setFont(new Font("Arial", Font.BOLD, 30));
		brush.drawString("Polygon Rush!", (width / 2) - 100, 50);
		brush.drawString("Its not the usual way ¯\\_( '_' )_/¯", width / 2 - 150, 100);
		brush.drawString("Press ENTER to Start", width / 2 - 150, 500);

		// Draw color options
		brush.setColor(playerColors[selectedColorIndex]);
		brush.fillRect(width / 2 - 50, height / 2 - 30, 100, 100); // Display selected color
		brush.setColor(Color.BLACK);
		brush.drawString("Press C to Change Color", width / 2 - 150, height / 2 + 125);
		brush.drawString("Level: " + selectedLevel, (width / 2) - 75, 175);
		brush.drawString("Press L to Change Level and Music", width / 2 - 150, 225);
	}

	// Paint method, runs each frame and draws all objects and UI
	public void paint(Graphics brush) {
		// Show the menu
		if (isInMenu) {
			showMenu(brush);
		} else {
			// Creating the background of the game
	    	brush.setColor(new Color(50, 20, 20));
	    	brush.fillRect(0,0,width,height);
	    	     	    	
	    	// Draw floor
	    	brush.setColor(Color.lightGray);
	    	int[][] xy = getXY(floor.getPoints());
	    	brush.fillPolygon(xy[0], xy[1], xy[0].length);
	    	brush.setColor(Color.darkGray);
	    	brush.drawPolygon(xy[0], xy[1], xy[0].length);
	    	
	    	// Draw player
			brush.setColor(player.getColor());
	    	xy = getXY(player.getPoints());
	    	brush.fillPolygon(xy[0], xy[1], xy[0].length);
	    	player.move(floor, map, mapSpeed);
	    	brush.setColor(Color.darkGray);
	    	brush.drawPolygon(xy[0], xy[1], xy[0].length);

	    	brush.setColor(Color.black);
			attemptTracker.drawAttempts(brush);
			

	    	// On player death, reset player and map
	    	if (!player.getIsAlive()) {
	    		player.setIsAlive(true);
	    		player.setNewCollide(null);
	    		attemptTracker.incrementAttempts();
				loadLevel(selectedLevel);
	    	}
	    	// Draws all map elements
	    	brush.setColor(Color.darkGray);
	    	for (MapElement e : map.getMap()) {
	    		Polygon p = (Polygon) e;
	    		p.getPosition().x -= mapSpeed;
	    		
	    		// Draws current map element
	    		if (p.getPosition().x < width || p.getPosition().x + 30 > 0) {
	    			xy = getXY(p.getPoints());
		        	brush.setColor(Color.darkGray);

		        	brush.fillPolygon(xy[0], xy[1], xy[0].length);
	    		}
	    	}
		}
	}
	
	// Main method
	public static void main(String[] args) {
		new PolygonRush();
	}

	// Starts game
	private void startGame(int colorIndex) {
		isInMenu = false;
		player.setColor(playerColors[colorIndex]);
		loadLevel(selectedLevel);
	}

	// Inner class to keep track of Attempts
	class AttemptTracker {
		private int attempts;

		public AttemptTracker() {
			this.attempts = 1;
		}

		public void incrementAttempts() {
			attempts++;
		}

		public int getAttempts() {
			return attempts;
		}

		public void drawAttempts(Graphics brush) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("Arial", Font.BOLD, 50));
			brush.drawString("Attempts: " + attempts, 10, 40);
		}
	}

	// Function to get both x and y arrays in an array when drawing an element
	public int[][] getXY(Point[] p) {
		int[] xPoints = new int[p.length];
		int[] yPoints = new int[p.length];

		for (int i = 0; i < p.length; i++) {
			xPoints[i] = (int) p[i].getX();
			yPoints[i] = (int) p[i].getY();
		}

		int[][] returnMe = new int[][] { xPoints, yPoints };
		return returnMe;
	}

	private void loadLevel(int level) {
		if (player != null) {
			// Clear the current map
			map.clearMap();
			Level currentLevel = new Level(level);
			
			// Reset player
			player.reset();
			
			// Add elements for the new level
			map.getMap().addAll(currentLevel.getLevelMapElements());
		}
		
		// Stop any existing music
		if (level == 1) {
			// Play music for level 1
			music.playLevelOneMusic();
		} else if (level == 2) {
			// Play music for level 2
			music.playLevelTwoMusic();
		} else if (level == 3) {
			// Play music for level 3
			music.playLevelThreeMusic();
		}

	}

	// Inner class for Level
	public class Level {
		private int levelNumber;
		private ArrayList<MapElement> levelMapElements;

		public Level(int levelNumber) {
			this.levelNumber = levelNumber;
			levelMapElements = new ArrayList<>();
			loadLevelElements();
		}

		// Load level specific map elements
		private void loadLevelElements() {
			if (levelNumber == 1) {
				
				addObstacle(new Platform(30, new Point(width - 500, height - 100 - 30), 0, Color.black));
				addObstacle(new Triangle(30, new Point(width - 100, height - 100 - 30), Color.black));
				addObstacle(new Triangle(30, new Point(width - 125, height - 100 - 30), Color.black));
				addObstacle(new Platform(30, new Point(width + 200, height - 100 - 75), 0, Color.black));
				addObstacle(new Triangle(30, new Point(width + 500, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 525, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 550, height - 100 - 60), Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 60), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 90), 0, Color.black));
				addObstacle(new Triangle(30, new Point(width + 700, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 800, height - 100 - 90), Color.black));
				addObstacle(new Triangle(30, new Point(width + 900, height - 100 - 120), Color.black));
				addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 200), 0, Color.black));
				addObstacle(new Triangle(30, new Point(width + 1400, height - 100 - 75), Color.black));
				addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 60), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 90), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 120), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1500, height - 100 - 150), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 2000, height - 100 - 30), 0, Color.black));
			} else if (levelNumber == 2) {
				
				addObstacle(new Platform(30, new Point(width, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 200, height - 100 - 75), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 60), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 600, height - 100 - 90), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 30), 0, Color.black));
				addObstacle(new Platform(30, new Point(width + 1200, height - 100 - 200), 0, Color.black));
			} else if (levelNumber == 3) {
				
				addObstacle(new Triangle(30, new Point(width + 500, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 525, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 550, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 700, height - 100 - 60), Color.black));
				addObstacle(new Triangle(30, new Point(width + 800, height - 100 - 90), Color.black));
				addObstacle(new Triangle(30, new Point(width + 900, height - 100 - 120), Color.black));
			}

		}

		public ArrayList<MapElement> getLevelMapElements() {
			return levelMapElements;
		}

		public int getLevelNumber() {
			return levelNumber;
		}
	}

	// Get keyPressed for Color, Level, and Start/Enter
	public void keyPressed(KeyEvent e) {
		if (isInMenu) {
			if (e.getKeyCode() == KeyEvent.VK_C) {
				// Lambda expression to handle the color change
				Runnable changeColorAction = () -> {
					selectedColorIndex = (selectedColorIndex + 1) % playerColors.length;
				};

				changeColorAction.run();

			} else if (e.getKeyCode() == KeyEvent.VK_L) {
				// Anonymous class for loading new level (more complex action)
				Runnable loadLevelAction = new Runnable() {
					public void run() {
						selectedLevel = (selectedLevel % 3) + 1;
						loadLevel(selectedLevel);
					}
				};
				loadLevelAction.run();

			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				// Start the game using a lambda expression
				Runnable startGameAction = () -> startGame(selectedColorIndex);
				startGameAction.run();
			}
		} else {
			// Use anonymous class for other actions (jump or other keys)
			if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				Runnable jumpStart = new Runnable() {
					public void run() {
						player.jump(true);
					}
				};
				jumpStart.run();
			}
		}
	}

	// On key typed event (required by keyListener)
	public void keyTyped(KeyEvent e) {

	}

	// On key released event (required by keyListener)
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			Runnable jumpStop = new Runnable() {
				public void run() {
					player.jump(false);
				}
			};
			jumpStop.run();
		}
	}
}