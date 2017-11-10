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

/**
 * represents the elements which will be displayed on the graphic interface
 * @author Tartiflotte
 */
public class World {
	
	private TiledMap map;
	private Level level;
	
	private Vector2f ladder;
	
	private Hero hero;
	private Monster monster;
	private Exit exit;
	
	public World(int x, int y) throws SlickException {
		ladder=new Vector2f(5,5);
		level = new Level(getClass().getResourceAsStream("/maps/level_1.tmx"), "maps");
		
		hero = level.getHero();
		hero.setWorld(this);
		
		monster = level.getMonster();
		monster.setWorld(this);
		
		exit = level.getExit();
		exit.setWorld(this);
		
		map = level.getMap();
	}
	
	/**
	 * 
	 * @param h Hero
	 * @return true if the hero collide to monster ; false if not
	 */
	private boolean collideToMonster(Hero h){
		if(h.getPos().distance(getPosLadder())<=4)
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param h Character (monster or hero)
	 * @return true if the character is out of the map or collide to a wall
	 */
	public boolean collideToWall(Character h){
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

	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g){
		map.render(0, 0);
		hero.render(g);
		exit.render(g);
		monster.render(g);
	}
	
	/**
	 * 
	 * @see Game.update()
	 */
	public void update(int delta){
		hero.update(delta);
		exit.update(delta);
		monster.update(delta);
	}
	
	/**
	 * 
	 * @param m Monster
	 * @return the distance between the monster m and the hero
	 */
	public Vector2f distanceWithHero(Monster m){
		return new Vector2f(hero.getX() - m.getX(), hero.getY() - m.getY());
	}
	
	/**
	 * 
	 * @param e Exit zone
	 * @return true if the hero is on an Exit zone ; false if not
	 */
	public boolean heroOnExitCase(Exit e){
		float xLeft = e.getTopLeft().getX();
		float xRight = e.getBottomRight().getX();
		float yTop = e.getTopLeft().getY();
		float yBot = e.getBottomRight().getY();
		
		if(hero.getX() >= xLeft && hero.getX() <= xRight && hero.getY() >= yTop && hero.getY() <= yBot){
			return true;
		}
		return false;
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
	
	private void setHero(int x, int y) throws SlickException{
		hero=new Hero(x,y);
	}
	
	private void setLadder(int x, int y){
		ladder=new Vector2f(x,y);
	}
}
