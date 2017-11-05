package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Vector2f;

/*
 * Helper class that converts input into data usable by the hero
 */
public class PlayerController implements KeyListener {
		
	private Map<Integer, InputProperty> inputs = new HashMap<>();
	private int lastPressed = Input.KEY_S; //facing south by default
	
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
	
	@Override
	public void keyPressed(int key, char arg1) {
		if(inputs.containsKey(key)){
			inputs.get(key).setPressed(true);
			lastPressed = key;
		}
	}

	@Override
	public void keyReleased(int key, char arg1) {
		if(inputs.containsKey(key)){
			inputs.get(key).setPressed(false);
		}
	}

	public Vector2f getMovement(){
		InputProperty prop = inputs.get(lastPressed);
		if(prop == null || !prop.pressed()){
			return new Vector2f(0f, 0f);
		}
		//here, prop is set and pressed
		return new Vector2f(prop.getMovement());
	}

	public boolean isMoving() {
		for(Entry<Integer, InputProperty> e : inputs.entrySet()){
			if(e.getValue().pressed()){
				return true;
			}
		}
		return false;
	}
	
	public int getDirection(){
		return inputs.get(lastPressed).getDirection();
	}
	
}
