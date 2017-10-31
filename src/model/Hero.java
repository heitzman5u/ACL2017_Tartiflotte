package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Hero extends Character {
	private PlayerController playerController;
	
	private static final float SPEED = 0.2f;
	
	public Hero(float x, float y){
		super(x, y, SPEED);
		playerController = new PlayerController();
	}
	
	public Hero(Hero other){
		super(other.getX(), other.getY(), SPEED);
		playerController = other.playerController;
	}
	
	public void move(int delta){
		Vector2f vspeed = playerController.getMovement().scale(speed*(float)delta);
		pos.add(vspeed);
	}
	
	public Hero futurePos(int delta){
		Hero h = new Hero(this);
		h.move(delta);
		return h;
	}
	
	public void render(Graphics g){
		g.fillOval(pos.x-16, pos.y-16, 32, 32);
	}
	
	public void update(int delta){
		if( playerController.isMoving() 
				&& !world.collideToWall(futurePos(delta)) ){
			move(delta);
		}
	}
	
	public PlayerController getPlayerController(){
		return playerController;
	}
		
}
