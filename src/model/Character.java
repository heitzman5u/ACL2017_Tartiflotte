package model;

import org.newdawn.slick.geom.Vector2f;

/**
 * A character is a world's entity.
 * It can be alive or dead.
 * It can move on this world, and collision can be detected between it and walls.
 * @author Tartiflotte
 *
 */
public class Character {
	protected World world;
	
	protected Vector2f pos;
	protected float speed;
	
	protected boolean alive;
	
	protected int direction; //direction the character faces
	
	/**
	 * 
	 * @param x initial abscissa
	 * @param y initial ordinate
	 * @param speed initial speed (pixels per second)
	 */
	protected Character(float x, float y, float speed){
		pos = new Vector2f(x,y);
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
	 * 
	 * @return position of this character
	 */
	public Vector2f getPos(){
		return pos;
	}
	
	/**
	 * 
	 * @return abscissa of this character
	 */
	public float getX(){
		return pos.x;
	}
	
	/**
	 * 
	 * @return ordinate of this character
	 */
	public float getY(){
		return pos.y;
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
	
	/**
	 * 
	 * @return whether this character is alive or dead
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * set this character to alive or dead
	 * @param alive
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * associate a world with this character
	 * @param w
	 */
	public void setWorld(World w){
		world = w;
	}
	
	/**
	 * get the world this character is in
	 * @return
	 */
	public World getWorld(){
		return world;
	}
}
