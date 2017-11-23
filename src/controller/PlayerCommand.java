package controller;

import org.newdawn.slick.geom.Vector2f;
import org.omg.CORBA.COMM_FAILURE;

/**
 * Enum that 
 * @author Tartiflotte
 *
 */
public enum PlayerCommand {
	LEFT(-1f, 0f),
	RIGHT(1f, 0f),
	UP(0f, -1f),
	DOWN(0f, 1f),
	
	ATTACK,
	USE_FLASK,
	NEXT_LEVEL;
	
	
	// Enum logic --
	private final Vector2f movement;
	
	private PlayerCommand(){
		movement = null;
	}
	
	private PlayerCommand(float dx, float dy){
		movement = new Vector2f(dx, dy).normalise();
	}
	
	public Vector2f getMovement(){
		return new Vector2f(movement);
	}
	
	public boolean hasMovement(){
		return movement != null;
	}
	
	/**
	 * 
	 * @return the opposite of this command (if it is a movement command)
	 */
	public PlayerCommand opposite(){
		switch(this){
		case LEFT: return RIGHT;
		case RIGHT: return LEFT;
		case UP: return DOWN;
		case DOWN: return UP;
		default:
			return this;
		}
	}
}
