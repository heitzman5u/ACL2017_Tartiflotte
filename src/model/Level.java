package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.tiled.TiledMap;

import exception.InvalidArgumentException;
import exception.TartiException;

/**
 * Load the elements of the map
 * 
 * @author Tartiflotte
 */
public class Level implements Serializable {

	private static final long serialVersionUID = -8517676766628736059L;
	
	private static Hero hero;

	
	private transient final TiledMap map;
	private transient static boolean heroCreated = false;
	
	private transient final Collection<WorldObject> pickableObject;
	private transient final Collection<Monster> monsters;

	private transient final Exit exit;
	
	private final int levelNumber;
	
	
	/**
	 * Load a level from file system
	 * @param number level number
	 * @throws SlickException
	 * @throws TartiException
	 */
	public Level(int number) throws SlickException, TartiException{
		final InputStream file = getClass().getResourceAsStream("/maps/level_"+number+".tmx");
		final String tilesetLoc = "maps";

		map = new TiledMap(file, tilesetLoc);
		hero = getHeroInTmx();
		exit = getExitInLevel();
		pickableObject = flasksInLevel();
		pickableObject.addAll(getAttackBoostInLevel());
		monsters = getMonstersInLevel();
		monsters.addAll(getGhostsInLevel());
		
		loadMusic(number);
		
		levelNumber = number;
		
		bossInLevel(levelNumber);
		
//		try{
//			deserialize();
//		} catch (IOException | ClassNotFoundException e){
//			
//		}

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
			return true;
		}

		Image tile = this.map.getTileImage( // tile wich corresponds with the hero's position
				(int) x / this.map.getTileWidth(), (int) y / this.map.getTileHeight(), this.map.getLayerIndex("solid"));
        if(tile == null) return false; //no block collision detected
        //pixel collision ?
        Color color = tile.getColor(
                (int) x % this.map.getTileWidth(), 
                (int) y % this.map.getTileHeight());
        return color.getAlpha() > 0;
	}

	/**
	 * return list of LifeFlask in the map.tmx
	 *
	 * @throws SlickException
	 */
	private Collection<WorldObject> flasksInLevel() throws SlickException {
		Image tile;
		List<WorldObject> listFlask = new ArrayList<WorldObject>();
		if(this.map.getLayerIndex("flask") == -1) return listFlask;
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
	 * return list of LifeFlask in the map.tmx
	 *
	 * @throws SlickException
	 */
	private Collection<WorldObject> getAttackBoostInLevel() throws SlickException {
		Image tile;
		List<WorldObject> attBoost = new ArrayList<WorldObject>();
		if(this.map.getLayerIndex("flask") == -1) return attBoost;
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				tile = this.map.getTileImage(x, y, this.map.getLayerIndex("attack_boost"));
				if (tile != null) {
					attBoost.add(new LifeFlask(x * this.map.getTileWidth(), y * this.map.getTileHeight()));
				}
			}
		}
		return attBoost;
	}
	
	/**
	 * return list of monster in the map.tmx
	 * @return
	 * @throws SlickException
	 */
	private Collection<Monster> getMonstersInLevel() throws SlickException {
		Image tile;
		List<Monster> monsters = new ArrayList<Monster>();
		if(this.map.getLayerIndex("monster") == -1) return monsters;
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				tile = this.map.getTileImage(x, y, this.map.getLayerIndex("monster"));
				if (tile != null) {
					monsters.add(new Wolf(x * this.map.getTileWidth(), y * this.map.getTileHeight()));
				}
			}
		}
		return monsters;
	}
	
	
	private Collection<Monster> getGhostsInLevel() throws SlickException{
		Image tile;
		List<Monster> monsters = new ArrayList<Monster>();
		if(this.map.getLayerIndex("ghost") == -1) return monsters;
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				tile = this.map.getTileImage(x, y, this.map.getLayerIndex("ghost"));
				if (tile != null) {
					monsters.add(new Ghost(x * this.map.getTileWidth(), y * this.map.getTileHeight()));
				}
			}
		}
		return monsters;
	}
	

	private Hero getHeroInTmx() throws SlickException, TartiException {
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				if (this.map.getTileImage(x, y, this.map.getLayerIndex("hero")) != null){
					if (!heroCreated){
						heroCreated = true;
						return new Hero(x * this.map.getTileWidth(), y * this.map.getTileHeight());
					} else {
						hero.setX(x * this.map.getTileWidth());
						hero.setY(y * this.map.getTileHeight());
						return hero;
					}
				}
			}
		}
		return null;
	}
	
	
	private Exit getExitInLevel() throws SlickException, TartiException{
		if(this.map.getLayerIndex("exit") == -1 ) return null;
		for (int x = 0; x < this.map.getWidth(); x++) {
			for (int y = 0; y < this.map.getHeight(); y++) {
				if (this.map.getTileImage(x, y, this.map.getLayerIndex("exit")) != null){
					final int xe = x * this.map.getTileWidth();
					final int ye = y * this.map.getTileHeight();
					final int wOn2 = this.map.getTileWidth()/2;
					final int hOn2 = this.map.getTileHeight()/2;
					return new Exit(
							new Point(xe, ye), 
							new Point(xe + wOn2, ye + hOn2)
							);
				}
			}
		}
		return null;
	}
	
	private void loadMusic(int level) {
		try {
			if(level == 3) {
				Music m = new Music(getClass().getResourceAsStream("/musics/abyss_watchers.ogg"), "abyss_watchers.ogg");
				m.loop();
			}
		}
		catch(Exception e) {
			System.err.println("Unable to load the music");
		}
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
	
	public Collection<WorldObject> getPickableObject(){
		return pickableObject;
	}
	
	public Collection<Monster> getMonsters(){
		return monsters;
	}
	
	public int getCurrentLevel() {
		return levelNumber;
	}
	
	public void serialize(){
		try {
			FileOutputStream fos = new FileOutputStream("level.serial");
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this); 
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unused")
	private void deserialize() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("level.serial");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Level tmp = (Level) ois.readObject();
		hero.setLife(tmp.getHero().getLife());
		ois.close();
		fis.close();

		
	}

	public void destroyMonster(Monster m) {
		monsters.remove(m);
	}
	
	private Boss bossInLevel(int level) {
		Boss b = null;
		if(level == 3 ) {
			b = new Boss(500,500);
			monsters.add(b);
		}
		return b;
	}
	
}
