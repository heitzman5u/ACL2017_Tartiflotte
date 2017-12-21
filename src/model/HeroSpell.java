package model;

import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.TartiException;

public class HeroSpell extends Spell {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7744441920185044982L;

	public HeroSpell(float x, float y, Vector2f dir) {
		super(x, y, dir);
		speed = 0.7f;
		damage = 2;
		range = 0.3f;
	}
	
	public HeroSpell(float x, float y, float xCible, float yCible) {
		super(x, y, xCible, yCible);
		speed = 0.7f;
		damage = 2;
		range = 0.3f;
	}
	
	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		
		Monster m = world.collideToMonster(this);
		
		if(m == null && range > 0 && !world.collideToWall(this)) {
			move(delta);
			range-= (float)delta / 1000f;
		}else {
			if(m != null && m.getLife() <= 0)
				world.destroyObject(m);
			world.destroyObject(this);
		}
	}

}
