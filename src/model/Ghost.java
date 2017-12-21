package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;

public class Ghost extends Monster {

	/**
	 * Create a wolf at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Ghost(float x, float y) {
		super(x, y, 1.0f , 600f, 60_000f, 10, 1, 1000);
	}
	
	/**
	 * Create a copy of a wolf
	 * @param monster copied
	 */
	private Ghost(Ghost other){
		super(other);
	}
	
	private Monster futurePos(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		Ghost m = new Ghost(this);
		m.move(delta);
		return m;
	}
	
	/**
	 * Allow the monster to move towards the hero
	 * @param delta milliseconds since last frame
	 */
	public void move(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		float xHero = world.trajectoryToHero(this).getX();
		float yHero = world.trajectoryToHero(this).getY();
		
		//move only if the hero is within his view range, and no collisions will occur
		if(((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= viewDistance) 
				&& ((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) >= attackDistance)){
			moving = true;
			pos.add(direction(xHero, yHero).scale(speed));	
		} else {
			moving = false;
		}
	}
	
	public void render(Graphics g) throws TartiException {
		if(g == null) throw new NullArgumentException();
		
	}

	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		if(!world.collideToWall(futurePos(delta))){
			move(delta);
		}
		attack();
	}
	
	
}
