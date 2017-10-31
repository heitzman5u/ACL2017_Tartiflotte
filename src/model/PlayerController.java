package model;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.geom.Vector2f;

public class PlayerController implements KeyListener {
	
	private boolean moving;
	
	private Vector2f movement;
	
	public PlayerController(){
		moving = false;
		movement = new Vector2f(0,0);
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
		if (key == Input.KEY_Z){
			moving = true;
			movement.x = 0;
			movement.y = -1;
		}
		if (key == Input.KEY_Q){
			moving = true;
			movement.x = -1;
			movement.y = 0;			
		}
		if (key == Input.KEY_S){
			moving = true;
			movement.x = 0;
			movement.y = 1;	
		}
		if (key == Input.KEY_D){
			moving = true;
			movement.x = 1;
			movement.y = 0;	
		}
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		moving = false;
		movement.x = 0;
		movement.y = 0;
	}

	public Vector2f getMovement(){
		return new Vector2f(movement);
	}

	public boolean isMoving() {
		return moving;
	}
	
}
