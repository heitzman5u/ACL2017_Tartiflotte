package model;

import org.newdawn.slick.geom.Vector2f;

public class Hero {
	private Vector2f pos;
	
	public Hero(float x, float y){
		pos = new Vector2f(x,y);
	}
	
	public void move(float x, float y){
		pos.add(new Vector2f(x,y));
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public void render(){
		System.out.println("[ " + pos.getX() + ", " + pos.getY() + " ]\n");
	}
	
	public static void main(String[] args){
		Hero h = new Hero(0,0);
		h.render();
		h.move(2.0f, 3.0f);
		h.render();
	}

}
