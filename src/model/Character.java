package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Character {
	protected SpriteSheet spriteSheet;
	protected Animation animation;
	
	protected Vector2f pos;
	protected float speed;
	
	protected Character(float x, float y, float speed){
		pos = new Vector2f(x,y);
		this.speed = speed;
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public void move(float x, float y){
		pos.add(new Vector2f(x,y));
	}
}
