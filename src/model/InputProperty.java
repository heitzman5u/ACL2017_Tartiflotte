package model;

import org.newdawn.slick.geom.Vector2f;

/**
 * Descriptor of properties of an input. Used by PlayerController
 */
public class InputProperty {
	
	//immutable
	private Vector2f movement; //1 unit speed vector associated to this input
	private int direction; //direction faced after pushing this input
	
	//mutable
	private boolean pressed; //whether the input is pressed or not

	/**
	 * 
	 * @param x x param of speed vector
	 * @param y y param of speed vector
	 * @param direction direction this input faces
	 */
	public InputProperty(float x, float y, int direction) {
		this.movement = new Vector2f(x, y);
		this.direction = direction;
		
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
		return new Vector2f(movement);
	}
	
	/**
	 * 
	 * @return the direction associated to this input
	 * @see Hero
	 */
	public int getDirection(){
		return direction;
	}

}
