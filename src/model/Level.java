package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

import exception.NullArgumentException;

/**
 * Load the elements of the map
 * 
 * @author Tartiflotte
 */
public class Level {

	private TiledMap map;
	private Hero hero;
	
	private ArrayList<Monster> monsters;

	private Monster monster;

	private Exit exit;

	private ArrayList<LifeFlask> listFlask;

	/**
	 * Load a level
	 * 
	 * @param file
	 *            file to load the map from
	 * @param tilesetLoc
	 *            path to the tileset location
	 * @throws SlickException
	 */
	public Level(InputStream file, String tilesetLoc) throws SlickException {
		if (file == null || tilesetLoc == null) {
			throw new NullArgumentException();
		}

		map = new TiledMap(file, tilesetLoc);
		monsters= monstersInLevel();
		hero = getHeroInTmx();
		exit = new Exit(new Point(840, 350), new Point(900, 370));

		listFlask = flasksInLevel();
	}

	/**
	 * Determines if the given position is on a "logic" block of the map
	 * 
	 * @param x
	 *            abscissa of the character x must be within the map
	 * @param y
	 *            ordinate of the character y must be within the map
	 * @return true if the character collides to a wall ; false if not
	 */
	public boolean collides(float x, float y) {
		if (x < 0f || x >= (float) (map.getWidth() * map.getTileWidth()) || y < 0f
				|| y >= (float) (map.getHeight() * map.getTileHeight())) {
			throw new IllegalArgumentException();
		}

		Image tile = this.map.getTileImage( // tile wich corresponds with the hero's position
				(int) x / this.map.getTileWidth(), (int) y / this.map.getTileHeight(), this.map.getLayerIndex("logic"));
		return tile != null; // null if no "logic" tile found there
	}

	/**
	 * return list of objects in type obj in the level.tmx
	 * 
	 * @param obj
	 * @throws SlickException
	 */
	private ArrayList<LifeFlask> flasksInLevel() throws SlickException {
		Image tile;
		ArrayList<LifeFlask> listFlask = new ArrayList<LifeFlask>();
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				tile = this.map.getTileImage(x, y, this.map.getLayerIndex("flask"));
				if (tile != null) {
					listFlask.add(new LifeFlask(x * this.map.getTileWidth(), y * this.map.getTileHeight()));
				}
			}
		}
		return listFlask;
	}
	
	private ArrayList<Monster> monstersInLevel() throws SlickException {
		Image tile;
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				tile = this.map.getTileImage(x, y, this.map.getLayerIndex("monster"));
				if (tile != null) {
					monsters.add(new Monster(x * this.map.getTileWidth(), y * this.map.getTileHeight()));
				}
			}
		}
		return monsters;
	}

	private Hero getHeroInTmx() throws SlickException {
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				if (this.map.getTileImage(x, y, this.map.getLayerIndex("hero")) != null)
					return new Hero(x * this.map.getTileWidth(), y * this.map.getTileHeight());
			}
		}
		return null;
	}

	/**
	 * @return width in tiles
	 */
	public int getWidth() {
		return map.getWidth();
	}

	/**
	 * @return height in tiles
	 */
	public int getHeight() {
		return map.getHeight();
	}

	public TiledMap getMap() {
		return map;
	}

	public Hero getHero() {
		return hero;
	}

	public Monster getMonster() {
		return monster;
	}

	public Exit getExit() {
		return exit;
	}

	public Iterator<LifeFlask> getFlasks() {
		return listFlask.iterator();
	}

	/**
	 * Access level's monsters
	 * 
	 * @return null
	 */

	public Iterator<Monster> getMonsters(){
		return monsters.iterator();
	}
}
