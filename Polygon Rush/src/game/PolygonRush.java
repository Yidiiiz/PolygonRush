package game;

/*
CLASS: YourGameNameoids
DESCRIPTION: Extending Game, YourGameName is all in the paint method.
NOTE: This class is the metaphorical "main method" of your program,
      it is your control center.

*/
import java.awt.*;
import java.awt.event.*;

class PolygonRush extends Game implements KeyListener {
	static int counter = 0;
	public Player player;
	public Polygon floor;
	public Map m;
	

	public PolygonRush() {
		super("YourGameName!",1200,600);
	    this.setFocusable(true);
	    this.requestFocus();
	    addKeyListener(this);

	    Point[] playerShape = new Point[] {new Point(0, 0), new Point(0, 30), new Point(30, 30), new Point(30, 0)};
	    Point playerPosition = new Point(100, height - 100 - 30);
   		player = new Player(playerShape, playerPosition, 0);

   		Point[] floorShape = new Point[] {new Point(0, 0), new Point(0, 100), new Point(width, 100), new Point(width, 0)};
   		Point floorPosition = new Point(0, height - 100);
	    floor = new Polygon(floorShape, floorPosition, 0);
	    
//	    Point[] newShape = new Point[] {new Point(0, 0), new Point(0, 30), new Point(30, 30), new Point(30, 0)};
//	    Point newPosition = new Point(width, height - 100 - 30);
//   		Polygon p = new Polygon(newShape, newPosition, 0);
   		
   		m = new Map();
   		m.addElement(new Platform(30, new Point(200, height - 100 - 30), 0, Color.black));
	}

  
	public void paint(Graphics brush) {
    	brush.setColor(Color.white);
    	brush.fillRect(0,0,width,height);
    	
    	brush.setColor(Color.black);
    	
    	// Draw floor
    	Point[] floorPoints = floor.getPoints();
    	int[] floorXPoints = new int[floorPoints.length];
    	int[] floorYPoints = new int[floorPoints.length];
    	
    	for (int i = 0; i < floorPoints.length; i++) {
    		floorXPoints[i] = (int) Math.floor(floorPoints[i].getX());
    		floorYPoints[i] = (int) Math.floor(floorPoints[i].getY());
    	}
    	
    	brush.fillPolygon(floorXPoints, floorYPoints, floorPoints.length);
    	
    	
    	// Draw player
    	Point[] playerPoints = player.getPoints();
    	int[] xPoints = new int[playerPoints.length];
    	int[] yPoints = new int[playerPoints.length];
    	 
    	for (int i = 0; i < playerPoints.length; i++) {
    		xPoints[i] = (int) playerPoints[i].getX();
       		yPoints[i] = (int) playerPoints[i].getY();
    	}
    	
    	brush.fillPolygon(xPoints, yPoints, playerPoints.length);
    	player.move(floor, m);

    	if (!player.isAlive) {
    		player.isAlive = true;
    		player.newCollide = null;
    		for (MapElement e : m.mapArray) {
        		Polygon p = (Polygon) e;
        		p.position.x = 200;
        	}
    	}

    	for (MapElement e : m.mapArray) {
    		Polygon p = (Polygon) e;
    		p.position.x -= 3;
    		if (p.position.x <= -30) {
    			p.position.x = 200;
    		}
    		playerPoints = p.getPoints();
        	xPoints = new int[playerPoints.length];
        	yPoints = new int[playerPoints.length];
        	 
        	for (int i = 0; i < playerPoints.length; i++) {
        		xPoints[i] = (int) playerPoints[i].getX();
           		yPoints[i] = (int) playerPoints[i].getY();
        	}
        	
        	brush.fillPolygon(xPoints, yPoints, playerPoints.length);
    	}
    	
    	// sample code for printing message for debugging
    	// counter is incremented and this message printed
    	// each time the canvas is repainted
    	
    	counter++;
    	brush.setColor(Color.black);
    	brush.drawString("Counter is " + counter,10,10);
	}
  
	public static void main (String[] args) {
   		PolygonRush a = new PolygonRush();
		a.repaint();
	}


	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_SPACE || e.getKeyChar() == KeyEvent.VK_W || e.getKeyChar() == KeyEvent.VK_UP) {
			player.jump(floor);
		}
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}