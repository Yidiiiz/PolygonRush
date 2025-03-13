package game;
import java.awt.*;
import java.util.ArrayList;

// Player class, creates and moves the player
public class Player extends Polygon {
	// Changeable gravity and jump power
	private static double gravity = -1;
	private static double jumpPower = 15;
	
	// Initial velocity
	private double yVel = 0;
	private double xVel = 0;

	// Is player alive
	private boolean isAlive = true;

	// Can player currently jump
	private boolean canJump = true;
	
	// Is this player currently jumping
	private boolean isJumping = false;
	
	// Last polygon collided (used to check new collisions)
	private Polygon newCollide;

	private Color color;

	// Constructor for player object
	public Player(Point[] inShape, Point inPosition, int rotation) {
		super(inShape, inPosition, rotation);
		this.color = Color.black;
	}

	// When jump keys pressed, this function is triggered, toggling the player jump
	public void jump(boolean isJumping) {
		this.isJumping = isJumping;
	}
	public void jump() {
		yVel = jumpPower;
		canJump = false;
	}

	// Add a method to set the player's color
	public void setColor(Color color) {
		this.color = color;
	}

	// Add a method to get the player's color if needed
	public Color getColor() {
		return color;
	}

	public void draw(Graphics g) {
		g.setColor(color); // Set the player's color before drawing
		super.draw(g); // Call the parent method to draw the shape
	}

	// Move function for player, including collision and jump logic
	public void move(Polygon floor, Map m, int mapSpeed, Polygon end, int width, int height) {		
		// If jump key is down, toggle player jump
		if (isJumping && canJump) {
			yVel = jumpPower;
			canJump = false;
		}
		
		// Moves the player based on current player velocity
		super.getPosition().y -= yVel;
		super.getPosition().x += xVel;

		// Gets the map element which the player collides with, null if none
		ArrayList<MapElement> collisions = collidesMap(m);
		
		// If player collides with floor or map
		if (super.collides(floor) || collisions != null) {
				
			// If player is colliding the map
			if (collisions != null) {
				for (MapElement e : collisions) {
					Polygon p = (Polygon) e;
					
					// If the player is colliding the block from the side, player dies
					if (e.getResetPlayer() || (newCollide != p && this.getPosition().x + 30 <= p.getPosition().x)) {
						isAlive = false;
						
					// If player fell from above the element, player is placed on top of it
					} else if (newCollide == p || this.getPosition().y + yVel + 30 <= p.getPosition().y + gravity) {
						super.getPosition().y = p.getPosition().y - 30 - yVel;
					}
					newCollide = p;
				}
				
			// If player is colliding the floor
			} else {
				super.getPosition().y = floor.getPosition().y - 30 - yVel;
				newCollide = floor;
			}

			// Since player is inside of something, movement is undone
			super.getPosition().y += yVel;

			// Resets velocity because of collision
			yVel = 0;

			// Allows player to jump again due to them touching ground
			canJump = true;

			// Resets player rotation
			super.setRotation(0);

		// If nothing collided
		} else {
			// Increments gravity on velocity
			yVel += gravity;

			// If player is currently jumping, player rotates at a speed relative to jump power and gravity
			if (!canJump) {
				super.setRotation(super.getRotation() + 270 / Math.abs((jumpPower) / gravity) / 3);
			}
		}
		
		// Slow down animation at start
		if (xVel > 0) {
			xVel -= .25;
		} else {
			xVel = 0;
		}
		
		// If near end, jump
		if (Math.abs(getPosition().x - (end.getPosition().x - 500)) < 10) {
			jump();
		}
		
		// If near end, spin and move upwards
		if (getPosition().x >= end.getPosition().x - 470) {
			yVel = (Math.abs(end.getPosition().x - getPosition().x)/400)*2;
    		super.setRotation(-Math.abs(end.getPosition().x - getPosition().x)*2);
		}
		
		// if near end, move right
		if (end.getPosition().x <= width) {
    		if (end.getPosition().x % 5 == 0) {
    			xVel += 1.3;
    		}
    	}

	}

	// Gets the map element array list which the player collides with, null if none
	public ArrayList<MapElement> collidesMap(Map m) {
		// Checks through each element of the map
		ArrayList<MapElement> returnMe = new ArrayList<MapElement>();
		for (Polygon p : m.getMap()) {
			// If player collides with element, return the element
			if (super.collides(p)) {
				returnMe.add((MapElement) p);
			}
		}
		
		// Returns null if player does not collide with any map elements
		if (returnMe.size() > 0) {
			return returnMe;
		}  else {
			return null;
		}
	}
	
	// Getter for isAlive
	public boolean getIsAlive() {
		return isAlive;
	}
	
	// Setter for isAlive
	public void setIsAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	// Setter for newCollide
	public void setNewCollide(Polygon newCollide) {
		this.newCollide = newCollide;
	}
	
	// Resets player
	public void reset() {
		super.setX(-30);
		canJump = false;
		yVel = 0;
		xVel = 10;
	}
}
