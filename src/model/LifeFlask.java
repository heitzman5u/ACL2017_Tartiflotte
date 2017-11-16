package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import exception.NullArgumentException;

public class LifeFlask extends WorldObject {
	
	private Animation animationLittleFlask;
	private Animation[] animationBigFlask;
	
	private static float hp = 120f; 
	
	private static float PICK_UP_DISTANCE = 25f;

	protected LifeFlask(float x, float y) throws SlickException {
		super(x, y);
		
		//animationBigFlask = new Animation[3];
		animationLittleFlask = new Animation();
		
		creationAnimationsLittleFlask();
		//creationAnimationBigFlask();
	}
	
	public void update(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		/*final float d = world.trajectoryToHero(this).length();
		if(d < PICK_UP_DISTANCE){
			world.pickFlask(this);
		}*/
		//TODO
	}
	
	public void render(Graphics g){
		if(g == null) throw new NullArgumentException();
		g.drawAnimation(animationLittleFlask, pos.x, pos.y);
		
//		float nbFlaskHero = world.getHero().getNbFlasks();
//		if(nbFlaskHero >= 10)
//			g.drawAnimation(animationBigFlask[0], 10, 10);
//		if(nbFlaskHero >= 5)
//			g.drawAnimation(animationBigFlask[1], 10, 10);
//		if(nbFlaskHero >= 0)
//			g.drawAnimation(animationBigFlask[2], 10, 10);
	}
	
	private void creationAnimationsLittleFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("littleFlask", getClass().getResourceAsStream("/flask/images/LittleLifeFlask.png"), 50, 50);
		animationLittleFlask.addFrame(spriteSheet.getSprite(0, 0), 300);
		animationLittleFlask.addFrame(spriteSheet.getSprite(1, 0), 300);
	}
	
	private void creationAnimationBigFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("bigFlask", getClass().getResourceAsStream("/flask/images/BigLifeFlask.png"), 150, 150);
		
		for (int j = 0 ; j < 3 ; j++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(j, 0), 100);
			animationBigFlask[j] = animation;
		}
	}

	public static float getHp() {
		return hp;
	}
	
}
