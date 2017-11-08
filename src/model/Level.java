package model;


import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

/**
 * load the elements of the map
 * @author Tartiflotte
 */
public class Level {
	
	private TiledMap map;
	private Hero hero;
	
	private Monster monster;
	
	private Exit exit;
	
	public Level(InputStream file, String tilesetLoc) throws SlickException{
		map = new TiledMap(file, tilesetLoc);
		monster = new Monster(450, 200);
		hero = new Hero(30, 260);
		exit = new Exit(new Point(840, 350), new Point(900, 370));
		
	}
	
	/**
	 * 
	 * @param x abscissa of the character
	 * @param y ordinate of the character
	 * @return true if the character collides to a wall ; false if not
	 */
	public boolean collides(float x, float y){
		Image tile = this.map.getTileImage( //tile wich corresponds with the hero's position
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
	
	public TiledMap getMap(){
		return map;
	}
	
	public Hero getHero(){
		return hero;
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	public Exit getExit(){
		return exit;
	}
	
	public Iterator<Monster> getMonsters(){
		return null; //TODO
	}
	
}
