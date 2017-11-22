package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import exception.InvalidArgumentException;
import exception.NotImplementedException;
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

	//private Hero hero;
	//private Exit exit;

	//private List<LifeFlask> flasks;
	//private List<Monster> monsters;
	
	private final List<LifeFlask> toBeRemoved;

	public World() throws SlickException, TartiException {
		level = new Level(getClass().getResourceAsStream("/maps/level_1.tmx"), "maps");

		//hero = level.getHero();
		//hero.setWorld(this);

		//exit = level.getExit();
		//exit.setWorld(this);

		map = level.getMap();

		//flasks = level.flasksInLevel();
		//for (LifeFlask f : flasks)
		//	f.setWorld(this);
		toBeRemoved = new ArrayList<>(level.getFlasks().size());

		//monsters = level.monstersInLevel();
		//for (Monster munch : monsters)
		//	munch.setWorld(this);
		
		level.getHero().setWorld(this);
		level.getExit().setWorld(this);
		for(LifeFlask f : level.getFlasks()){
			f.setWorld(this);
		}
		for(Monster m : level.getMonsters()){
			m.setWorld(this);
		}
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
	 * @see Game.update()
	 */

	public void update(int delta) throws TartiException {
		if (delta < 0)
			throw new InvalidArgumentException("delta >= 0");
		
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
	 * @param f flask to pick
	 */
	public void pickFlask(LifeFlask f) {
		toBeRemoved.add(f);
		level.getHero().pickFlask();
	}

	public Hero getHero() {
		return level.getHero();
	}

	public PlayerController getPlayerController() {
		return level.getHero().getPlayerController();
	}

	public Iterable<Monster> getMonsters() {
		return level.getMonsters();
	}
}
