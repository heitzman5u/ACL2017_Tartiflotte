package model;

import java.io.Serializable;

import org.newdawn.slick.geom.Vector2f;

/**
 * An object that is in the world map, and can interact with it
 * @author Tartiflotte
 *
 */
public class WorldObject implements Serializable {
	protected transient World world;
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
	
	public void setPos(Vector2f p){
		pos=p;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public void setX(float x){
		pos.x = x;
	}
	
	public void setY(float y){
		pos.y = y;		
	}
	
	public void setWorld(World w){
		world = w;
	}
	
	public float distance(WorldObject wo){
		return this.getPos().distance(wo.getPos());
	}
}
