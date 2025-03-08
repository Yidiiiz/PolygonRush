package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Player extends Polygon {
	public double yVel = 0;
	public double xVel = 0;
	
	public boolean isAlive = true;
	
	private static double gravity = -1;
	private static double jumpPower = 15;
	
	private boolean canJump = true;
	public Polygon newCollide;
 	
	public Player(Point[] inShape, Point inPosition, int rotation) {
		super(inShape, inPosition, rotation);
	}
	
	public void jump(Polygon floor) {
		if (canJump) {// this.collides(floor)
			yVel = jumpPower;
			canJump = false;
		}
	}
	
	public void move(Polygon floor, Map m) {		
		super.position.y -= yVel;
		super.position.x += xVel;
	
		
		if (super.collides(floor) || collidesMap(m)) { // (super.position.y >= 400) {
			
			
			if (super.collides(floor)) {
				super.position.y = floor.position.y - 30 - yVel;
				newCollide = floor;
			} else {
				for (MapElement e : m.mapArray) {
					if (super.collides((Polygon) e)) {
						if (this.position.y + yVel <= ((Polygon) e).position.y - 30 + gravity) {
							super.position.y = ((Polygon) e).position.y - 30 - yVel;
							break;
						} else if (newCollide != (Polygon) e && this.position.x + 30 < ((Polygon) e).position.x + 3) {
							newCollide = (Polygon) e;
							System.out.println("die");
							isAlive = false;
						}
					}
				}
			}
			
			super.position.y += yVel;
			super.position.x -= xVel;
			
			yVel = 0;
			canJump = true;
			super.rotation = 0;
		} else {
			yVel += gravity;
			
			if (!canJump) {
				super.rotation += 270/Math.abs((jumpPower)/gravity)/3;
			}
		}
		
	}
	
	public boolean collidesMap(Map m) {
		for (MapElement e : m.mapArray) {
			if (super.collides((Polygon) e)) {
				return true;
			}
		}
		return false;
	}
}
