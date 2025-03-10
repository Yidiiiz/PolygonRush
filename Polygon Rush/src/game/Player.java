package game;

import java.util.ArrayList;

// Player class, creates and moves the player
public class Player extends Polygon {
	// Changeable gravity and jump power
	private static double gravity = -1;
	private static double jumpPower = 13;
	
	// Initial velocity
	public double yVel = 0;
	public double xVel = 0;
	
	// Is player alive
	public boolean isAlive = true;
	
	// Can player currently jump
	private boolean canJump = true;
	
	// Is this player currently jumping
	private boolean isJumping = false;
	
	// Last polygon collided (used to check new collisions)
	public Polygon newCollide;
 	
	// Constructor for player object
	public Player(Point[] inShape, Point inPosition, int rotation) {
		super(inShape, inPosition, rotation);
	}
	
	// When jump key is pressed or released, this function is triggered, changing isJumping  
	public void jump(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	// Move function for player, including collision and jump logic
	public void move(Polygon floor, Map m, int mapSpeed) {		
		// If jump key is down, toggle player jump
		if (isJumping && canJump) {
			yVel = jumpPower;
			canJump = false;
		}
		
		// Moves the player based on current player velocity
		super.position.y -= yVel;
		super.position.x += xVel;
		
		// Gets the map element which the player collides with, null if none
		ArrayList<MapElement> collisions = collidesMap(m);
		
		// If player collides with floor or map
		if (super.collides(floor) || collisions != null) {
			// If player collides with an element on the map
			if (collisions != null) {
				for (MapElement e : collisions) {
					// If element is collided
					if (super.collides((Polygon) e)) {
						// If player fell from above the element, player is placed on top of it
						if (!e.resetPlayer && this.position.y + yVel <= ((Polygon) e).position.y - 30 + gravity) {
							super.position.y = ((Polygon) e).position.y - 30 - yVel;
							
						// Otherwise, if the player is colliding the block from the side, player dies
						} else if (e.resetPlayer || (newCollide != (Polygon) e && this.position.x + 30 <= ((Polygon) e).position.x + mapSpeed)) {
							newCollide = (Polygon) e;
							isAlive = false;
						}
					}
				}
				
			// If player is colliding the floor
			} else {
				super.position.y = floor.position.y - 30 - yVel;
				newCollide = floor;
			}
			
			// Since player is inside of something, movement is undone
			super.position.y += yVel;
			super.position.x -= xVel;
			
			// Resets velocity because of collision
			yVel = 0;
			
			// Allows player to jump again due to them touching ground
			canJump = true;
			
			// Resets player rotation
			super.rotation = 0;
			
		// If nothing collided
		} else {
			// Increments gravity on velocity
			yVel += gravity;
			
			// If player is currently jumping, player rotates at a speed relative to jump power and gravity
			if (!canJump) {
				super.rotation += 270/Math.abs((jumpPower)/gravity)/3;
			}
		}
		
	}
	
	// Gets the map element array list which the player collides with, null if none
	public ArrayList<MapElement> collidesMap(Map m) {
		// Checks through each element of the map
		ArrayList<MapElement> returnMe = new ArrayList<MapElement>();
		for (MapElement e : m.mapArray) {
			// If player collides with element, return the element
			if (super.collides((Polygon) e)) {
				returnMe.add(e);
			}
		}
		
		// Returns null if player does not collide with any map elements
		if (returnMe.size() > 0) {
			return returnMe;
		}  else {
			return null;
		}
	}
}
