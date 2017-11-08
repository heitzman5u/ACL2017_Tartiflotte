package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Vector2f;

/**
 * Tell what button is pressed or released and in wich direction
 * @author Tartiflotte
 *
 */

/*
 * Helper class that converts input into data usable by the hero
 */
public class PlayerController implements KeyListener {
		
	private Map<Integer, InputProperty> inputs = new HashMap<>();
	private int lastPressed = Input.KEY_S; //facing south by default
	
	/**
	 * The constructor initializes the attribute inputs.
	 * This attribute connects the four moving keys to the good displacement
	 */
	public PlayerController(){
		inputs.put(Input.KEY_Z, new InputProperty(0f,  -1f, 2));
		inputs.put(Input.KEY_S, new InputProperty(0f,  1f, 3));
		inputs.put(Input.KEY_Q, new InputProperty(-1f,  0f, 1));
		inputs.put(Input.KEY_D, new InputProperty(1f, 0f, 0));
	}

	@Override
	public void inputEnded() {
	}
	
	@Override
	public void inputStarted() {
	}
	
	@Override
	public boolean isAcceptingInput() {
		return true;
	}
	
	@Override
	public void setInput(Input arg0) {		
	}	
	
	/**
	 * Get the current key pressed.
	 * Update the current displacement
	 * of the user of the class.
	 * Update the button lastPressed.
	 */
	@Override
	public void keyPressed(int key, char arg1) {
		if(inputs.containsKey(key)){
			inputs.get(key).setPressed(true);
			lastPressed = key;
		}
	}
	
	/**
	 * Disable the lastPressed button
	 */
	@Override
	public void keyReleased(int key, char arg1) {
		if(inputs.containsKey(key)){
			inputs.get(key).setPressed(false);
		}
	}

	/**
	 * Return a vector corresponding to the key pressed 
	 * and the direction associated 
	 * @return
	 */
	public Vector2f getMovement(){
		InputProperty prop = inputs.get(lastPressed);
		if(prop == null || !prop.pressed()){
			return new Vector2f(0f, 0f);
		}
		//here, prop is set and pressed
		return new Vector2f(prop.getMovement());
	}

	/**
	 * Return true if a key is pressed, 
	 * else return false.
	 * @return
	 */
	public boolean isMoving() {
		for(Entry<Integer, InputProperty> e : inputs.entrySet()){
			if(e.getValue().pressed()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * return the current direction of the last button pressed
	 * @return
	 */
	public int getDirection(){
		return inputs.get(lastPressed).getDirection();
	}
	
}
