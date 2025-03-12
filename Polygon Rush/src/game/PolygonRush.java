package game;
import java.awt.*;
import java.awt.event.*;

// Main class, runs the game
class PolygonRush extends Game implements KeyListener {
	// Serial version use id
	private static final long serialVersionUID = 1;
	
	// Variables for player, floor, and map
	private Player player;
	private Polygon floor;
	private Map map;
	private Level currentLevel;

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
	
	// Create an inner class for progress
	private ProgressTracker progressTracker = new ProgressTracker();

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

	private void showMenu(Graphics brush) {
        brush.setColor(Color.WHITE);
        brush.fillRect(0, 0, width, height);

        // Draw menu text
        brush.setColor(Color.BLACK);
        brush.setFont(new Font("Arial", Font.BOLD, 50));
        brush.drawString("Polygon Rush!", (width / 2) - 200, 50);
        brush.setFont(new Font("Arial", Font.BOLD, 30));
        
        brush.drawString("Press ENTER to Start", width / 2 - 190, 500);

        // Draw color options
        brush.setColor(playerColors[selectedColorIndex]);
        brush.fillRect(width / 2 - 90, height / 2 - 30, 100, 100); // Display selected color
        brush.setColor(Color.BLACK);
        brush.drawString("Press C to Change Color", width / 2 - 210, height / 2 + 125);
        brush.drawString("Level: " + selectedLevel, (width / 2) - 100, 175);
        brush.drawString("Press L to Change Level and Music", width / 2 - 275, 225);
    }

	// Paint method, runs each frame and draws all objects and UI
	public void paint(Graphics brush) {
		// Show the menu
		if (isInMenu) {
			showMenu(brush);
		} else {
			// Creating the background of the game
	    	brush.setColor(Color.white);
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
			progressTracker.drawProgress(brush);
			

	    	// On player death, reset player and map
	    	if (!player.getIsAlive()) {
	    		player.setIsAlive(true);
	    		player.setNewCollide(null);
	    		attemptTracker.incrementAttempts();
	    		progressTracker.reset();
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
	    	
	    	progressTracker.increment();
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

		// Constructor for AttemptTracker, initialize attempts
		public AttemptTracker() {
			this.attempts = 1;
		}

		// Increment attempts
		public void incrementAttempts() {
			attempts++;
		}

		// Getter for attempts
		public int getAttempts() {
			return attempts;
		}
		
		// Text for total Attempts (current)
		public void drawAttempts(Graphics brush) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("ComicSans", Font.BOLD, 20));
			brush.drawString("Attempts: " + attempts, 10, 40);
		}
	}
	
	// Inner class to keep track of Progress
	class ProgressTracker {
		// Variables to track progress
		private int maxProgress;
		private int currentProgress;
		
		// Constructor for ProgressTracker, initializes variables
		public ProgressTracker() {
			maxProgress = 0;
			currentProgress = 0;
		}
		
		// Updates max progress if new greatest max progress
		private void updateProgress(int progress) {
			if (progress > maxProgress) {
				maxProgress = progress;
			}
		}
		
		// Increments progress (every frame during game)
		public void increment() {
			currentProgress += mapSpeed;
			updateProgress(currentProgress);
			System.out.println(currentProgress);
		}
		
		// Resets the level
		public void reset() {
			currentProgress = 0;
		}
		
		// Getter for current progress
		public int getProgress() {
			return currentProgress;
		}
		
		// Getter for max progress
		public int getMaxProgress() {
			return maxProgress;
		}
		
		// Text for total progress (current)
		public void drawProgress(Graphics brush) {
			brush.setColor(Color.BLACK);
			brush.setFont(new Font("ComicSans", Font.BOLD, 20));
			brush.drawString((int) ((double) currentProgress/(double) currentLevel.getFinish()*100) + "%", width-50, 40);
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

		return new int[][] { xPoints, yPoints };
	}

	private void loadLevel(int level) {
		// Stop any existing music
		if (level == 1) {
			// Play music for level 1
			music.playLevelOneMusic();
			
			// If new level, then replace currentLevel
			if (currentLevel == null || currentLevel.getLevelNumber() != level) {
				currentLevel = (Level) new LevelOne(width, height);
				map = currentLevel.getMap();
			}
		} else if (level == 2) {
			// Play music for level 2
			music.playLevelTwoMusic();
			
			// If new level, then replace currentLevel
			if (currentLevel == null || currentLevel.getLevelNumber() != level) {
				currentLevel = (Level) new LevelTwo(width, height);
				map = currentLevel.getMap();
			}
		} else if (level == 3) {
			// Play music for level 3
			music.playLevelThreeMusic();
			
			// If new level, then replace currentLevel
			if (currentLevel == null || currentLevel.getLevelNumber() != level) {
				currentLevel = (Level) new LevelThree(width, height);
				map = currentLevel.getMap();
			}
		}
		
		// If player exists
		if (player != null) {
			// Reset the current map
			map = currentLevel.resetMap();
			
			// Reset player
			player.reset();
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
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				isInMenu = true;
				loadLevel(selectedLevel);
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