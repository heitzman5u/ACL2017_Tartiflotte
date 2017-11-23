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

/**
 * Represents the elements which will be displayed on the graphic interface
 * 
 * @author Tartiflotte
 */
public class World {

	private TiledMap map;
	private Level level;

	private final Collection<WorldObject> toBeRemoved;
	private Collection<WorldObject> objects;

	public World() throws SlickException, TartiException {
		level = new Level(1);

		map = level.getMap();
		level.getHero().setWorld(this);
		level.getExit().setWorld(this);
		
		for (LifeFlask f : level.getFlasks()) {
			f.setWorld(this);
		}
		objects.addAll(level.getFlasks());
		
		toBeRemoved = new ArrayList<>(level.getFlasks().size());
		for (Monster m : level.getMonsters()) {
			m.setWorld(this);
		}
		objects.addAll(level.getMonsters());
	}

	/**
	 * 
	 * @param h
	 *            Character (monster or hero)
	 * @return true if the character is out of the map or collide to a wall
	 */
	public boolean collideToWall(Character h) {
		if (h == null)
			return false;
		// out of map
		if ((int) h.getX() < 0 || (int) h.getX() >= map.getWidth() * map.getTileWidth() || (int) h.getY() < 0
				|| (int) h.getY() >= map.getHeight() * map.getTileHeight()) {
			return true;
		}
		// on a wall
		Image tile = map.getTileImage((int) h.getX() / map.getTileWidth(), (int) h.getY() / map.getTileHeight(),
				map.getLayerIndex("solid"));
		return tile != null;
	}

	/**
	 * 
	 * @param s
	 * @return true if the spell collide a monster
	 */
	public boolean collideToMonster(Spell s) {
		for (Monster m : level.getMonsters()) {
			if (m.getPos().distance(s.getPos()) <= 25) {
				m.receiveDamage(s.getDamage());
				if (m.getLife() <= 0) {
					m.setAlive(false);
				}
				return true;
			}
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

		// call render of all flasks
		for (LifeFlask f : level.getFlasks())
			f.render(g);

		level.getHero().render(g);

		// call render of all monsters
		for (Monster m : level.getMonsters())
			m.render(g);

		level.getExit().render(g);
	}

	/**
	 * 
	 * @param delta
	 *            milliseconds since last frame
	 * @param nextLevel
	 *            level to load (0 = no loading)
	 * @throws TartiException
	 */
	public void update(int delta, int nextLevel) throws SlickException, TartiException {
		if (delta < 0) {
			throw new InvalidArgumentException("delta >= 0");
		}
		if (nextLevel < 0) {
			throw new InvalidArgumentException("Loading a level with number < 0");
		}

		// call update of all flasks
		toBeRemoved.clear();
		for (LifeFlask f : level.getFlasks())
			f.update(delta);
		level.getFlasks().removeAll(toBeRemoved);

		// call update of all monsters
		for (Monster m : level.getMonsters())
			m.update(delta);

		level.getHero().update(delta);
		level.getExit().update(delta);

		if (nextLevel != 0) {
			loadLevel(nextLevel);
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
		level = new Level(number);
		map = level.getMap();
		toBeRemoved.clear();

		level.getHero().setWorld(this);
		level.getExit().setWorld(this);
		for (LifeFlask f : level.getFlasks()) {
			f.setWorld(this);
		}
		for (Monster m : level.getMonsters()) {
			m.setWorld(this);
		}
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
	
	public void destroyMonster(Monster m) {
		toBeRemoved.add(m);
		level.destroyMonster(m);
	}
	
	public void destroySpell(Spell s) {
		toBeRemoved.add(s);
		level.getHero().destroySpell(s);
	}

	public Hero getHero() {
		return level.getHero();
	}

	public Iterable<Monster> getMonsters() {
		return level.getMonsters();
	}
	
	public void addSpell(Spell s) {
		objects.add(s);
	}
}
