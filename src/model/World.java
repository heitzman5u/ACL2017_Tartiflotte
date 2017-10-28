package model;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

public class World {
	
	private TiledMap map;
	private Level level;
	
	private Vector2f ladder;
	private Hero hero;
	
	public World(int x, int y) throws SlickException {
		ladder=new Vector2f(5,5);
		level = new Level(getClass().getResourceAsStream("/maps/main.tmx"), "maps");
		//level = new Level(getClass().getResource("/maps/main.tmx"));
		hero = level.getHero();
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

	public void render(Graphics g){
		map.render(0, 0);
		hero.render(g);
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
}
