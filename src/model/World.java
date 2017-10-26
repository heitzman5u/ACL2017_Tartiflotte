package model;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	
	private TiledMap map;
	
	private int height, width;
	private Vector2f ladder;
	private Hero hero;
	
	public World(int x, int y) {
		height=y;
		width=x;
		
		ladder=new Vector2f(0,0);
		hero=new Hero(width/2,height/2);
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

	public void render(){
		hero.render();
		//System.out.println("( "+hero.getPos().getX()+" ; "+hero.getPos().getY()+" )\n");
	}
	
	private Vector2f getPosHero(){
		return hero.getPos();
	}
	
	private Vector2f getPosLadder(){
		return ladder;
	}
	
	public Hero getHero(){
		return hero;
	}
}
