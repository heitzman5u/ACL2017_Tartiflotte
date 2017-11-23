package model;

import org.newdawn.slick.geom.Vector2f;

import controller.PlayerCommand;

/**
 * Descriptor of properties of an input. Used by PlayerController
 */
public class InputProperty {
	
	private final PlayerCommand command; //command to send to the hero so he can update himself
	private boolean pressed; //whether the input is pressed or not

	/**
	 * 
	 * @param x x param of speed vector
	 * @param y y param of speed vector
	 * @param direction direction this input faces
	 */
	public InputProperty(PlayerCommand c) {
		this.command = c;
		this.pressed = false;
	}
	
	public void setPressed(boolean value){
		pressed = value;
	}
	
	public boolean pressed(){
		return pressed;
	}
	
	/**
	 * @return the speed vector associated to this input
	 */
	public Vector2f getMovement(){
		return command.getMovement();
	}
	
	
	public PlayerCommand getCommand(){
		return command;
	}

}
