package model;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Vector2f;

public class PlayerController implements KeyListener {
	
	private boolean moving;
		
	private Map<Integer, Boolean> pressed = new TreeMap<>(); //whether a key is pressed or not
	private Map<Integer, Vector2f> movement = new TreeMap<>(); //associate a key with a speed vector
	private int lastPressed = -1;
	
	public PlayerController(){
		moving = false;
		
		//fill map with default values (non moving)
		pressed.put(Input.KEY_Z, false);
		pressed.put(Input.KEY_S, false);
		pressed.put(Input.KEY_Q, false);
		pressed.put(Input.KEY_D, false);
		
		//fill map with default values (1 unit vectors with good direction)
		movement.put(Input.KEY_Z, new Vector2f(0f, -1f));
		movement.put(Input.KEY_S, new Vector2f(0f, 1f));
		movement.put(Input.KEY_Q, new Vector2f(-1f, 0f));
		movement.put(Input.KEY_D, new Vector2f(1f, 0f));
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
		if(pressed.containsKey(key)){
			pressed.put(key, true);
			lastPressed = key;
		}
	}

	@Override
	public void keyReleased(int key, char arg1) {
		if(pressed.containsKey(key)){
			pressed.put(key, false);
		}
	}

	public Vector2f getMovement(){
		Boolean moving = pressed.get(lastPressed);
		//check if we are moving, and get a copy of the speed vector
		if(moving != null && moving){
			return new Vector2f(movement.get(lastPressed));
		}
		return new Vector2f(0f, 0f);
	}

	public boolean isMoving() {
		for(Entry<Integer, Boolean> e : pressed.entrySet()){
			if(e.getValue() == true){
				return true;
			}
		}
		return false;
	}
	
}
