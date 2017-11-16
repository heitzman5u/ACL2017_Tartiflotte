package model;

import org.newdawn.slick.geom.Vector2f;

public class WorldObject {
	protected World world;
	protected Vector2f pos;
	
	protected WorldObject(float x, float y){
		pos = new Vector2f(x, y);
	}
	
	/**
	 * Copy constructor that returns a deep copy of position and shallow copy of world
	 * @param other other to copy
	 */
	protected WorldObject(WorldObject other){
		pos = new Vector2f(other.pos);
		world = other.world;
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
	
	public void setWorld(World w){
		world = w;
	}
}
