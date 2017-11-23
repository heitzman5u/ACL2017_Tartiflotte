package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;

/**
 * Load the elements of the map
 * 
 * @author Tartiflotte
 */
public class Level implements Serializable {

	private static final long serialVersionUID = -8517676766628736059L;
	
	
	private final TiledMap map;
	private final Hero hero;
	
	private final Collection<LifeFlask> flasks;
	private final Collection<Monster> monsters;

	private final Exit exit;
	
	/**
	 * Load a level
	 * 
	 * @param file
	 *            file to load the map from
	 * @param tilesetLoc
	 *            path to the tileset location
	 * @throws SlickException
	 */
	public Level(InputStream file, String tilesetLoc) throws SlickException, TartiException {
		if (file == null || tilesetLoc == null) {
			throw new NullArgumentException();
		}

		map = new TiledMap(file, tilesetLoc);
		hero = getHeroInTmx();
		exit = new Exit(new Point(840, 350), new Point(900, 370));
		flasks = flasksInLevel();
		monsters = monstersInLevel();
		
		
		serialization();
		
		deserialize();
	}
	
	public Level(int number) throws SlickException, TartiException{
		this(Level.class.getResourceAsStream("/maps/level_"+number+".tmx"), "maps");
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
	public boolean collides(float x, float y) throws TartiException {
		if (x < 0f || x >= (float) (map.getWidth() * map.getTileWidth()) || y < 0f
				|| y >= (float) (map.getHeight() * map.getTileHeight())) {
			throw new InvalidArgumentException();
		}

		Image tile = this.map.getTileImage( // tile wich corresponds with the hero's position
				(int) x / this.map.getTileWidth(), (int) y / this.map.getTileHeight(), this.map.getLayerIndex("logic"));
		return tile != null; // null if no "logic" tile found there
	}

	/**
	 * return list of LifeFlask in the map.tmx
	 *
	 * @throws SlickException
	 */
	private Collection<LifeFlask> flasksInLevel() throws SlickException {
		Image tile;
		List<LifeFlask> listFlask = new ArrayList<LifeFlask>();
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
	
	/**
	 * return list of monster in the map.tmx
	 * @return
	 * @throws SlickException
	 */
	private Collection<Monster> monstersInLevel() throws SlickException {
		Image tile;
		List<Monster> monsters = new ArrayList<Monster>();
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

	private Hero getHeroInTmx() throws SlickException, TartiException {
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

	public Exit getExit() {
		return exit;
	}
	
	public Collection<LifeFlask> getFlasks(){
		return flasks;
	}
	
	public Collection<Monster> getMonsters(){
		return monsters;
	}
	
	private void serialization(){
		try {
			FileOutputStream fos = new FileOutputStream("level.serial");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(hero); 
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void deserialize(){
		try{
			FileInputStream fis = new FileInputStream("level.serial");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Hero tmp = (Hero) ois.readObject();
			System.out.println(tmp);
			ois.close();
			fis.close();
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}

	public void destroyMonster(Monster m) {
		monsters.remove(m);
	}
	
}
