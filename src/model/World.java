package model;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	
	private TiledMap map;
	private Level level;
	
	private Vector2f ladder;
	
	private Hero hero;
	private Monster monster;
	
	public World(int x, int y) throws SlickException {
		ladder=new Vector2f(5,5);
		level = new Level(getClass().getResourceAsStream("/maps/main.tmx"), "maps");
		
		hero = level.getHero();
		hero.setWorld(this);
		
		monster = level.getMonster();
		monster.setWorld(this);
		
		map = level.getMap();
	}
	
	private void setHero(int x, int y){
		hero=new Hero(x,y);
	}
	
	private void setLadder(int x, int y){
		ladder=new Vector2f(x,y);
	}
	
	private boolean collideToMonster(Hero h){
		if(h.getPos().distance(getPosLadder())<=4)
			return true;
		return false;
	}
	
	public boolean collideToWall(Hero h){
		//out of map
		if((int)h.getX() < 0 || (int)h.getX() >= map.getWidth()*map.getTileWidth()
				|| (int)h.getY() < 0 || (int)h.getY() >= map.getHeight()*map.getTileHeight()){
			return true;
		}
		//on a wall
		Image tile = map.getTileImage((int)h.getX()/map.getTileWidth(), 
				(int)h.getY()/map.getTileHeight(), 
				map.getLayerIndex("solid"));
		return tile != null;
	}

	public void render(Graphics g){
		map.render(0, 0);
		hero.render(g);
		monster.render(g);
	}
	
	public void update(int delta){
		hero.update(delta);
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
	
	public PlayerController getPlayerController(){
		return hero.getPlayerController();
	}
}
