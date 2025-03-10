package game;
import java.awt.*;
import java.awt.event.*;


// Main class, runs the game
class PolygonRush extends Game implements KeyListener {
	// Serial version use id
	private static final long serialVersionUID = 1;

	// Speed which map scrolls
	private static int mapSpeed = 5;
	
	// Is jump currently pressed
	public boolean jumpPressed = false;
	
	// Time counter (given at start)
	static int counter = 0;
	
	// Variables for player, floor, and map
	public Player player;
	public Polygon floor;
	public Map m;
	
	// Constructor for game, creates the game and initializes objects
	public PolygonRush() {
		// Creating the game
		super("YourGameName!",1200,600);
	    this.setFocusable(true);
	    this.requestFocus();
	    
	    // Creates a key listener to detect key presses
	    addKeyListener(this);

	    // Creates and initializes the player
	    Point[] playerShape = new Point[] {new Point(0, 0), new Point(0, 30), new Point(30, 30), new Point(30, 0)};
	    Point playerPosition = new Point(100, height - 100 - 30);
   		player = new Player(playerShape, playerPosition, 0);

   		// Creates and initializes the floor
   		Point[] floorShape = new Point[] {new Point(0, 0), new Point(0, 100), new Point(width, 100), new Point(width, 0)};
   		Point floorPosition = new Point(0, height - 100);
	    floor = new Polygon(floorShape, floorPosition, 0);
   		
	    // Creates and initializes the map
   		m = new Map();
//   		m.addElement(new Platform(30, new Point(width, height - 100 - 30), 0, Color.black));
//   		m.addElement(new Platform(30, new Point(width+120, height - 100 - 30*2), 0, Color.black));
//   		m.addElement(new Platform(30, new Point(width+120*2, height - 100 - 30*3), 0, Color.black));
   		for (int i = 0; i < 9; i++) {
   	   		m.addElement(new Platform(30, new Point(width+120*i, height - 100 - 30*(i+1)), 0, Color.black));
   		}
   		for (int i = 0; i < 24; i++) {
   	   		m.addElement(new Spike(30, new Point(width+30*i, height - 100 - 30), 0, Color.black));
   		}

   		
   		// Starts the game (the paint function)
   		repaint();
	}

	// Paint method, runs each frame and draws all objects and UI
	public void paint(Graphics brush) {
		// Creating the background of the game
    	brush.setColor(Color.white);
    	brush.fillRect(0,0,width,height);
    	     	    	
    	// Draw floor
    	brush.setColor(Color.black);
    	int[][] xy = getXY(floor.getPoints());
    	brush.fillPolygon(xy[0], xy[1], xy[0].length);
    	
    	// Draw player
    	brush.setColor(Color.darkGray);
    	xy = getXY(player.getPoints());
    	brush.fillPolygon(xy[0], xy[1], xy[0].length);
    	player.move(floor, m, mapSpeed);

    	// On player death, reset player and map
    	brush.setColor(Color.black);
    	if (!player.isAlive) {
    		player.isAlive = true;
    		player.newCollide = null;
    		
    		double moveAmount = width - m.mapArray.get(0).position.x;
    		for (MapElement e : m.mapArray) {
        		Polygon p = (Polygon) e;
        		p.position.x += moveAmount;
        	}
    	}

    	// Draws all map elements
    	for (MapElement e : m.mapArray) {
    		Polygon p = (Polygon) e;
    		p.position.x -= mapSpeed;
    		
    		// If map element is out of view to the left, it respawn's on the right (probably will not keep)
    		if (p.position.x <= -30) {
    			p.position.x = width;
    		}
    		
    		// Draws current map element
        	xy = getXY(p.getPoints());
        	brush.fillPolygon(xy[0], xy[1], xy[0].length);
    	}
    	
    	//  GIVEN CODE, WAS ALWAYS HERE (probably won't keep)
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	counter++;
    	brush.setColor(Color.black);
    	brush.drawString("Counter is " + counter,10,10);
	}
	
	// Function to get both x and y arrays in an array when drawing an element
	public int[][] getXY(Point[] p) {
		int[] xPoints = new int[p.length];
		int[] yPoints = new int[p.length];

    	for (int i = 0; i < p.length; i++) {
    		xPoints[i] = (int) p[i].getX();
    		yPoints[i] = (int) p[i].getY();
    	}
    	
    	int[][] returnMe = new int[][] {xPoints, yPoints};
		return returnMe;
	}

	// Main method, creates the game
	public static void main (String[] args) {
   		new PolygonRush();
	}

	// On key pressed event (required by keyListener)
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			player.jump(true);
		}
	}
	
	// On key typed event (required by keyListener)
	public void keyTyped(KeyEvent e) {
		
	}

	// On key released event (required by keyListener)
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE) {
			player.jump(false);
		}
	}
}