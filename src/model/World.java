package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import exception.NullArgumentException;

/**
 * Represents the elements which will be displayed on the graphic interface
 * @author Tartiflotte
 */
public class World {
	
	private TiledMap map;
	private Level level;
		
	private Hero hero;
	private Monster monster;
	private Exit exit;
	private LifeFlask lifeFlask;
	
	public World() throws SlickException {
		level = new Level(getClass().getResourceAsStream("/maps/level_1.tmx"), "maps");
		
		hero = level.getHero();
		hero.setWorld(this);
		
		monster = level.getMonster();
		monster.setWorld(this);
		
		exit = level.getExit();
		exit.setWorld(this);
		
		lifeFlask = level.getLifeFlask();
		lifeFlask.setWorld(this);
		
		map = level.getMap();
	}
	
	
	/**
	 * 
	 * @param h Character (monster or hero)
	 * @return true if the character is out of the map or collide to a wall
	 */
	public boolean collideToWall(Character h){
		if(h == null) return false;
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
		if(g == null) throw new NullArgumentException();
		map.render(0, 0);
		lifeFlask.render(g);
		hero.render(g);
		exit.render(g);
		monster.render(g);
	}
	
	/**
	 * 
	 * @see Game.update()
	 */
	public void update(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		lifeFlask.update(delta);
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
		if(m == null) throw new NullArgumentException();
		return new Vector2f(hero.getX() - m.getX(), hero.getY() - m.getY());
	}
	
	/**
	 * 
	 * @param e Exit zone
	 * @return true if the hero is on an Exit zone ; false if not
	 */
	public boolean heroOnExitCase(Exit e){
		if(e == null) return false;
		float xLeft = e.getTopLeft().getX();
		float xRight = e.getBottomRight().getX();
		float yTop = e.getTopLeft().getY();
		float yBot = e.getBottomRight().getY();
		
		if(hero.getX() >= xLeft && hero.getX() <= xRight && hero.getY() >= yTop && hero.getY() <= yBot){
			return true;
		}
		return false;
	}
	
	
	public Hero getHero(){
		return hero;
	}
	
	public PlayerController getPlayerController(){
		return hero.getPlayerController();
	}
}
