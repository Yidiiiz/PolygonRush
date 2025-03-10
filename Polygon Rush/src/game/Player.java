package game;

<<<<<<< HEAD
import java.awt.*;
=======
import java.util.ArrayList;
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6

// Player class, creates and moves the player
public class Player extends Polygon {
	// Changeable gravity and jump power
	private static double gravity = -1;
<<<<<<< HEAD
	private static double jumpPower = 15;

=======
	private static double jumpPower = 13;
	
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
	// Initial velocity
	public double yVel = 0;
	public double xVel = 0;

	// Is player alive
	public boolean isAlive = true;

	// Can player currently jump
	private boolean canJump = true;
<<<<<<< HEAD

=======
	
	// Is this player currently jumping
	private boolean isJumping = false;
	
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
	// Last polygon collided (used to check new collisions)
	public Polygon newCollide;

	private Color color;

	// Constructor for player object
	public Player(Point[] inShape, Point inPosition, int rotation) {
		super(inShape, inPosition, rotation);
		this.color = Color.black;
	}
<<<<<<< HEAD

	// When jump keys pressed, this function is triggered, toggling the player jump
	public void jump(Polygon floor) {
		if (canJump) {
			yVel = jumpPower;
			canJump = false;
		}
=======
	
	// When jump key is pressed or released, this function is triggered, changing isJumping  
	public void jump(boolean isJumping) {
		this.isJumping = isJumping;
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
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
<<<<<<< HEAD
	public void move(Polygon floor, Map m, int mapSpeed) {
=======
	public void move(Polygon floor, Map m, int mapSpeed) {		
		// If jump key is down, toggle player jump
		if (isJumping && canJump) {
			yVel = jumpPower;
			canJump = false;
		}
		
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
		// Moves the player based on current player velocity
		super.position.y -= yVel;
		super.position.x += xVel;

		// Gets the map element which the player collides with, null if none
<<<<<<< HEAD
		MapElement e = collidesMap(m);

=======
		ArrayList<MapElement> collisions = collidesMap(m);
		
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
		// If player collides with floor or map
		if (super.collides(floor) || collisions != null) {
			// If player collides with an element on the map
<<<<<<< HEAD
			if (e != null) {
				Polygon collidedObject = (Polygon) e;
				// If element is collided
				if (e instanceof Triangle) {
					// Allow the player to land on the triangle, but not die
					if (this.position.y + yVel <= collidedObject.position.y - 30 + gravity) {
						super.position.y = collidedObject.position.y - 30 - yVel;
						yVel = 0;
						canJump = true; // Allow jumping from the triangle
					}
				} else if (super.collides(collidedObject)) { // Regular platform (square)
					// If player fell from above the element, player is placed on top of it
					if (this.position.y + yVel <= collidedObject.position.y - 30 + gravity) {
						super.position.y = collidedObject.position.y - 30 - yVel;
						canJump = true;
						// Otherwise, if the player is colliding the block from the side, player dies
					} else if (this.position.x + 30 < collidedObject.position.x + 10) {
						isAlive = false; // Kill the player
					}
				}
				// If player is colliding the floor
=======
			if (collisions != null) {
				for (MapElement e : collisions) {
					// If element is collided
					Polygon p = (Polygon) e;
					if (super.collides(p)) {
						// If player fell from above the element, player is placed on top of it
						if (!e.resetPlayer && this.position.y + yVel <= p.position.y - 30 + gravity) {
							super.position.y = p.position.y - 30 - yVel;
							
						// Otherwise, if the player is colliding the block from the side, player dies
						} else if (e.resetPlayer || (newCollide != p && this.position.x + 30 <= p.position.x + mapSpeed)) {
							newCollide = p;
							isAlive = false;
						}
					}
				}
				
			// If player is colliding the floor
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
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

			// If player is currently jumping, player rotates at a speed relative to jump
			// power and gravity
			if (!canJump) {
				super.rotation += 270 / Math.abs((jumpPower) / gravity) / 3;
			}
		}

	}
<<<<<<< HEAD

	// Gets the map element which the player collides with, null if none
	public MapElement collidesMap(Map m) {
=======
	
	// Gets the map element array list which the player collides with, null if none
	public ArrayList<MapElement> collidesMap(Map m) {
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
		// Checks through each element of the map
		ArrayList<MapElement> returnMe = new ArrayList<MapElement>();
		for (Polygon p : m.mapArray) {
			// If player collides with element, return the element
			if (super.collides(p)) {
				returnMe.add((MapElement) p);
			}
		}
<<<<<<< HEAD

		// returns null if player does not collide with any map elements
		return null;
=======
		
		// Returns null if player does not collide with any map elements
		if (returnMe.size() > 0) {
			return returnMe;
		}  else {
			return null;
		}
>>>>>>> 99b1f8c09ed8d89c8db78974df5bb3c7783d66b6
	}

}
