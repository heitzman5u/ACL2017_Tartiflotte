package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import exception.NullArgumentException;

/**
 * A flask that can be picked by the hero, and heals him
 * @author Tartiflotte
 *
 */
public class LifeFlask extends WorldObject {
	
	private Animation animationLittleFlask;
	
	private static float hp = 120f; 
	private static float PICK_UP_DISTANCE = 25f;
	
	private static float WIDTH = 50f;
	private static float HEIGHT = 50f;

	protected LifeFlask(float x, float y) throws SlickException {
		super(x, y);
		animationLittleFlask = new Animation();
		
		creationAnimationsLittleFlask();
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		final float d = world.trajectoryToHero(this).length();
		if(d <= PICK_UP_DISTANCE){
			//System.out.println("Flask picked");
			world.pickFlask(this);
		}
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g){
		if(g == null) throw new NullArgumentException();
		g.drawAnimation(animationLittleFlask, pos.x-WIDTH/2, pos.y-HEIGHT/2);
	}
	
	private void creationAnimationsLittleFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("littleFlask", getClass().getResourceAsStream("/flask/images/LittleLifeFlask.png"), 50, 50);
		animationLittleFlask.addFrame(spriteSheet.getSprite(0, 0), 300);
		animationLittleFlask.addFrame(spriteSheet.getSprite(1, 0), 300);
	}
	

	/**
	 * 
	 * @return Number of hp healed by a flask
	 */
	public static float getHp() {
		return hp;
	}
	
}
