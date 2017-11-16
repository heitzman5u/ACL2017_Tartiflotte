package model;

import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.tiled.TiledMap;

import exception.NotImplementedException;
import exception.NullArgumentException;

/**
 * Represents the elements which will be displayed on the graphic interface
 * 
 * @author Tartiflotte
 */
public class World {

	private TiledMap map;
	private Level level;

	private Hero hero;
	private Exit exit;

	private List<LifeFlask> flasks;
	private List<Monster> monsters;

	public World() throws SlickException {
		level = new Level(getClass().getResourceAsStream("/maps/level_1.tmx"), "maps");

		hero = level.getHero();
		hero.setWorld(this);

		exit = level.getExit();
		exit.setWorld(this);

		map = level.getMap();

		flasks = level.flasksInLevel();
		for (LifeFlask f : flasks)
			f.setWorld(this);

		monsters = level.monstersInLevel();
		for (Monster munch : monsters)
			munch.setWorld(this);
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
	public void render(Graphics g) {
		if (g == null)
			throw new NullArgumentException();
		map.render(0, 0);

		// call render of all flasks
		for (LifeFlask f : flasks)
			f.render(g);

		// call render of all monsters
		for (Monster m : monsters)
			m.render(g);

		hero.render(g);
		exit.render(g);
	}

	/**
	 * 
	 * @see Game.update()
	 */

	public void update(int delta) {
		if (delta < 0)
			throw new IllegalArgumentException("delta >= 0");

		// call update of all flasks
		for (LifeFlask f : flasks)
			f.update(delta);

		// call update of all monsters
		for (Monster m : monsters)
			m.update(delta);

		hero.update(delta);
		exit.update(delta);
	}

	/**
	 * 
	 * @param o
	 *            world object
	 * @return the distance between the monster m and the hero
	 */
	public Vector2f trajectoryToHero(WorldObject o) {
		if (o == null)
			throw new NullArgumentException();
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
	 * @deprecated not implemented yet
	 */
	@Deprecated
	public void pickFlask(LifeFlask f) {
		throw new NotImplementedException();
	}

	public Hero getHero() {
		return hero;
	}

	public PlayerController getPlayerController() {
		return hero.getPlayerController();
	}

	public Iterator<Monster> getMonsters() {
		return monsters.iterator();
	}
}
