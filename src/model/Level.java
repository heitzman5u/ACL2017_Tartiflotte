package model;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	private TiledMap map;
	private Hero hero;
	
	public Level(InputStream file) throws SlickException{
		map = new TiledMap(file);
		hero = new Hero(0,  0);
	}
	
	public Level(URL file) throws SlickException{
		map = new TiledMap(file.getFile());
		hero = new Hero(0, 0);
	}
	
	
	
	public TiledMap getMap(){
		return map;
	}
	
	public Hero getHero(){
		return hero;
	}
	
	public Iterator<Monster> getMonsters(){
		return null; //TODO
	}
	
	public boolean collides(float x, float y){
		Image tile = this.map.getTileImage(
                (int) x / this.map.getTileWidth(), 
                (int) y / this.map.getTileHeight(), 
                this.map.getLayerIndex("logic"));
		return tile != null; //null if no "logic" tile found there
	}
	
	public int getWidth(){
		return map.getWidth();
	}
	
	public int getHeight(){
		return map.getHeight();
	}
	
}
