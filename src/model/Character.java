package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Character {
	protected World world;
	
	protected Vector2f pos;
	protected float speed;
	
	protected boolean alive;
	
	protected Character(float x, float y, float speed){
		pos = new Vector2f(x,y);
		this.speed = speed;
		alive = true;
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public void move(float x, float y){
		pos.add(new Vector2f(x,y));
	}
		
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setWorld(World w){
		world = w;
	}
	
	public World getWorld(){
		return world;
	}
}
