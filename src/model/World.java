package model;

import org.newdawn.slick.geom.Vector2f;

public class World {
	
	private int height, width;
	private Vector2f ladder;
	private Hero hero;
	
	public World(int x, int y) {
		height=y;
		width=x;
		
		ladder=new Vector2f(0,0);
		hero=new Hero(width,height);
	}
	
	private void setHero(int x, int y){
		hero=new Hero(x,y);
	}
	
	private void setLadder(int x, int y){
		ladder=new Vector2f(x,y);
	}
	
	private boolean collideTo(Hero h){
		if(h.getPos().distance(getPosLadder())<=4)
			return true;
		return false;
	}

	private void render(){
		System.out.println("( "+hero.getPos().getX()+" ; "+hero.getPos().getY()+" )\n");
	}
	
	private int getPosHero(){
		return hero.getPos();
	}
	
	private int getPosLadder(){
		return ladder;
	}
	
}
