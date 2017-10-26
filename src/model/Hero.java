package model;

import org.newdawn.slick.geom.Vector2f;

public class Hero extends Character {
	
	public Hero(float x, float y){
		super(x, y, 1);
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
