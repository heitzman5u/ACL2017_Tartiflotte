package model;

import java.util.ArrayList;
import java.util.Collection;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;
//import test.SafeMethod;

/**
 * Represents the elements which will be displayed on the graphic interface
 * 
 * @author Tartiflotte
 */
public class World {

	private TiledMap map;
	private Level level;
	final Collection<WorldObject> toBeRemoved;
	private final Collection<WorldObject> toBeAdded;
	private Collection<WorldObject> objects;

	public World(int lvl) throws SlickException, TartiException {
		level = new Level(lvl);
		objects = new ArrayList<>();
		map = level.getMap();
		level.getHero().setWorld(this);
		Exit e = level.getExit();
		if(e != null) e.setWorld(this);
		
		for (LifeFlask f : level.getFlasks()) {
			f.setWorld(this);
		}
		objects.addAll(level.getFlasks());
		
		toBeRemoved = new ArrayList<>();
		toBeAdded = new ArrayList<>();
		for (Monster m : level.getMonsters()) {
			m.setWorld(this);
		}
		objects.addAll(level.getMonsters());
	}
	
//	/**
//	 * For testing purpose
//	 */
//	@Deprecated
//	public World(){
//		SafeMethod.forTesting();
//		toBeRemoved = null;
//	}

	/**
	 * 
	 * @param h
	 *            Character (monster or hero)
	 * @return true if the character is out of the map or collide to a wall
	 * @throws TartiException 
	 */
	public boolean collideToWall(WorldObject h) throws TartiException {
		/*if (h == null)
			return false;
		// out of map
		if ((int) h.getX() < 0 || (int) h.getX() >= map.getWidth() * map.getTileWidth() || (int) h.getY() < 0
				|| (int) h.getY() >= map.getHeight() * map.getTileHeight()) {
			return true;
		}
		// on a wall
		Image tile = map.getTileImage((int) h.getX() / map.getTileWidth(), (int) h.getY() / map.getTileHeight(),
				map.getLayerIndex("solid"));
		return tile != null;*/
		return level.collides(h.getX(), h.getY());
	}

	/**
	 * Hurt the life bar of monster on hit.
	 * 
	 * @param s
	 * @return m if the spell collide the monster m first
	 */
	public Monster collideToMonster(Spell s) {
		for (Monster m : level.getMonsters()) {
			if (m.getPos().distance(s.getPos()) <= 25) {
				m.receiveDamage(s.getDamage());
				if (m.getLife() <= 0) {
					m.setAlive(false);
					
				}
				return m;
			}
		}
		return null;
	}
	
	/**
	 * Hurt the life bar of the hero, on hit.
	 * 
	 * @param s
	 */
	public boolean collideToHero(Spell s) {
		Hero h=level.getHero();
		if (h.getPos().distance(s.getPos()) <= 25) {
			h.receiveDamage(s.getDamage());
			if (h.getLife() <= 0) {
				h.setAlive(false);
				
			}
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException {
		if (g == null)
			throw new NullArgumentException();
		map.render(0, 0);
		
		level.getHero().render(g);
		
		for(WorldObject o : objects)
			o.render(g);
		Exit e = level.getExit();
		if(e != null) e.render(g);
	}

	/**
	 * 
	 * @param delta
	 *            milliseconds since last frame
	 * @param nextLevel
	 *            level to load (0 = no loading)
	 * @throws TartiException
	 */
	public void update(int delta, boolean nextLevel) throws SlickException, TartiException {
		if (delta < 0) {
			throw new InvalidArgumentException("delta >= 0");
		}
		
		
		for(WorldObject o : objects)
			o.update(delta);
	

		level.getHero().update(delta);
		Exit e = level.getExit();
		if(e != null) e.update(delta);
		
		level.getFlasks().removeAll(toBeRemoved);
		level.getMonsters().removeAll(toBeRemoved);
		objects.removeAll(toBeRemoved);
		if(!toBeAdded.isEmpty())
		{
			objects.addAll(toBeAdded);
		}
		toBeAdded.clear();
		toBeRemoved.clear();
		
		if (nextLevel) {
			loadLevel(getCurrentLevel()+1);
		}
	}

	/**
	 * Load a specific level
	 * 
	 * @param number
	 *            level to load
	 * @throws SlickException
	 * @throws TartiException
	 */
	private void loadLevel(int number) throws SlickException, TartiException {
		objects.clear();
		level.serialize();
		level = new Level(number);
		map = level.getMap();
		
		toBeAdded.clear();
		toBeRemoved.clear();

		Hero tmp = level.getHero();
		tmp.setWorld(this);
		Exit e = level.getExit();
		if(e != null) e.setWorld(this);;
		for (LifeFlask f : level.getFlasks()) {
			f.setWorld(this);
		}
		for (Monster m : level.getMonsters()) {
			m.setWorld(this);
		}
		
		objects.addAll(level.getMonsters());
		objects.addAll(level.getFlasks());
	}

	/**
	 * 
	 * @param o
	 *            world object
	 * @return the distance between the monster m and the hero
	 */
	public Vector2f trajectoryToHero(WorldObject o) throws TartiException {
		if (o == null)
			throw new NullArgumentException();
		final Hero hero = level.getHero();
		return new Vector2f(hero.getX() - o.getX(), hero.getY() - o.getY());
	}

	/**
	 * 
	 * @param e
	 *            Exit zone
	 * @return true if the hero is on an Exit zone ; false if not
	 */
	public boolean heroOnExitCase(Exit e) {
		if (e == null)
			return false;
		float xLeft = e.getTopLeft().getX();
		float xRight = e.getBottomRight().getX();
		float yTop = e.getTopLeft().getY();
		float yBot = e.getBottomRight().getY();

		final Hero hero = level.getHero();
		if (hero.getX() >= xLeft && hero.getX() <= xRight && hero.getY() >= yTop && hero.getY() <= yBot) {
			return true;
		}
		return false;
	}

	/**
	 * Remove a flask from the world and att it to the character
	 * 
	 * @param f
	 *            flask to pick
	 */
	public void pickFlask(LifeFlask f) {
		toBeRemoved.add(f);
		level.getHero().pickFlask();
	}
	
	public void destroyObject(WorldObject o) {
		toBeRemoved.add(o);
	}

	public Hero getHero() {
		return level.getHero();
	}

	public Iterable<Monster> getMonsters() {
		return level.getMonsters();
	}
	
	public void addSpell(Spell s) {
		toBeAdded.add(s);
	}
	
	public int getCurrentLevel() {
		return level.getCurrentLevel();
	}
	
}
