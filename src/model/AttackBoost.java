package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

public class AttackBoost extends WorldObject {

	private static final long serialVersionUID = 3984625908343107668L;

	public static final int BONUS_ATTACK_DAMAGE = 1;
	
	private static float PICK_UP_DISTANCE = 25f;
	private static float WIDTH = 32f;
	private static float HEIGHT = 32f;
	
	public AttackBoost(float x, float y) {
		super(x, y);
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		final float d = world.trajectoryToHero(this).length();
		if(d <= PICK_UP_DISTANCE){
			world.pickAttackBoost(this);
		}
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		
		Image img = GraphicsFactory.getAttackBoostImage();
		img.draw(getX()-WIDTH/2, getY()-HEIGHT/2);
	}
	
}
