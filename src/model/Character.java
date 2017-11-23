package model;

import org.newdawn.slick.geom.Vector2f;

/**
 * A character is a world's entity.
 * It can be alive or dead.
 * It can move on this world, and collision can be detected between it and walls.
 * @author Tartiflotte
 *
 */
public class Character extends WorldObject {	
	protected float speed;
	
	protected boolean alive;
	protected int life;
	protected int damage;
	
	protected int direction; //direction the character faces
	
	/**
	 * 
	 * @param x initial abscissa
	 * @param y initial ordinate
	 * @param speed initial speed (pixels per second)
	 */
	protected Character(float x, float y, float speed){
		super(x,y);
		this.speed = speed;
		alive = true;
	}
	
	/**
	 *
	 * @param x initial abscissa
	 * @param y initial ordinate
	 * @param speed initial speed (pixels per second)
	 * @param d initial direction this character is facing
	 * @param w world this character is in
	 */
	protected Character(float x, float y, float speed, int d, World w){
		this(x, y, speed);
		direction = d;
		world = w;
	}
	
	
	/**
	 * Copy constructor that returns a deep copy of the character
	 * @param other other to copy
	 */
	protected Character(Character other){
		super(other);
		speed = other.speed;
		alive  = other.alive;
		life = other.life;
		damage = other.damage;
		direction = other.direction;
	}
	
	
	/**
	 * 
	 * @param x x param of speed vector
	 * @param y y param of speed vector
	 */
	public void move(float x, float y){
		assert(pos != null):"character pos not set";
		pos.add(new Vector2f(x,y));
	}
	
	public boolean isAlive() {
		return alive;
	}


	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public int getLife() {
		return life;
	}
	
	/**
	 * Deal damage to this character.
	 * No damage taken if dmg <= 0
	 * @param dmg damage taken
	 */
	public void receiveDamage(int dmg){
		if(dmg < 0) dmg = 0;
		life -= dmg;
	}

}
