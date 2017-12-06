package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

/**
 * A flask that can be picked by the hero, and heals him
 * @author Tartiflotte
 *
 */
public class LifeFlask extends WorldObject {
	
	private static final long serialVersionUID = -6428207734845432786L;
	
	
	public static float HP = 3; 
	private static float PICK_UP_DISTANCE = 25f;
	
	private static float WIDTH = 50f;
	private static float HEIGHT = 50f;

	protected LifeFlask(float x, float y) {
		super(x, y);		
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		final float d = world.trajectoryToHero(this).length();
		if(d <= PICK_UP_DISTANCE){
			world.pickFlask(this);
		}
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		
		Animation animation = GraphicsFactory.getLittleFlaskAnimation();
		g.drawAnimation(animation, pos.x-WIDTH/2, pos.y-HEIGHT/2);
	}
	
	

	/**
	 * 
	 * @return Number of hp healed by a flask
	 */
	public static float getHp() {
		return HP;
	}
	
}
