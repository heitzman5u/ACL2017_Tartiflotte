package model;

import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.TartiException;

public class EnemySpell extends Spell {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4160079854161834657L;

	public EnemySpell(float x, float y, Vector2f dir) {
		super(x, y, dir);
		speed = 0.7f;
		damage = 2;
		range = 2f;
	}
	
	public EnemySpell(float x, float y, float xCible, float yCible) {
		super(x, y, xCible, yCible);
		speed = 0.5f;
		damage = 2;
		range = 0.6f;
	}
	
	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		
		if(range > 0 && !world.collideToWall(this) && !world.collideToHero(this)) {
			move(delta);
			range-= (float)delta / 1000f;
		} else {
			world.destroyObject(this);
		}
	}

}
