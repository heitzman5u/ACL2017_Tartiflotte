package model;

import org.newdawn.slick.geom.Vector2f;

/*
 * Descriptor of properties of an input
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
	 * @param direction
	 */
	public InputProperty(float x, float y, int direction) {
		this.movement = new Vector2f(x, y);
		this.direction = direction;
		
		this.pressed = false;
	}
	
	/**
	 * Set this input to pressed (or not)
	 * @param value
	 */
	public void setPressed(boolean value){
		pressed = value;
	}
	
	/**
	 * 
	 * @return whether this input is pressed or not
	 */
	public boolean pressed(){
		return pressed;
	}
	
	/**
	 * 
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
