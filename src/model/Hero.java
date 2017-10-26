package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Hero extends Character {
	private PlayerController playerController;
	
	public Hero(float x, float y){
		super(x, y, 1);
		playerController = new PlayerController();
	}
	
	public void move(){
		if (playerController.isMoving()){
			pos.add(playerController.getMovement().scale(speed));
		}
		
	}
	
	public void render(Graphics g){
		//System.out.println("[ " + pos.getX() + ", " + pos.getY() + " ]\n");
		g.fillOval(pos.x, pos.y, 32, 32);
	}
	
	public void update(int delta){
		move();
	}
		
	public static void main(String[] args){
//		Hero h = new Hero(0,0);
//		h.render();
//		h.move(2.0f, 3.0f);
//		h.render();
	}

}
