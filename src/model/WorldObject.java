package model;

import org.newdawn.slick.geom.Vector2f;

public class WorldObject {
	protected World world;
	protected Vector2f pos;
	
	protected WorldObject(float x, float y){
		pos = new Vector2f(x, y);
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
	
	public float distance(WorldObject wo){
		return this.getPos().distance(wo.getPos());
	}
}
