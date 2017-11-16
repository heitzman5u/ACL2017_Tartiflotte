package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class LifeFlask extends WorldObject {
	
	private Animation animationLittleFlask;
	
	private static float hp = 120; 

	protected LifeFlask(float x, float y) throws SlickException {
		super(x, y);
		animationLittleFlask = new Animation();
		
		creationAnimationsLittleFlask();
	}
	
	public void update(int delta){
		
	}
	
	public void render(Graphics g){
		g.drawAnimation(animationLittleFlask, pos.x, pos.y);
	}
	
	private void creationAnimationsLittleFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("littleFlask", getClass().getResourceAsStream("/flask/images/LittleLifeFlask.png"), 50, 50);
		animationLittleFlask.addFrame(spriteSheet.getSprite(0, 0), 300);
		animationLittleFlask.addFrame(spriteSheet.getSprite(1, 0), 300);
	}
	

	public static float getHp() {
		return hp;
	}
	
}
