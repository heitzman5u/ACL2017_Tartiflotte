package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import exception.NullArgumentException;

public class Spell extends WorldObject{

	private Animation[] animations;
	private int damage;
	private float range;
	private float speed;
	private Vector2f direction;
	
	protected Spell(float x, float y, Vector2f dir) throws SlickException {
		super(x, y);
		speed = 20;
		damage = 10;
		range = 50;
		direction=dir;
		animations = new Animation[32];
		creationAnimation();
	}
		
	private void creationAnimation() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("spell", getClass().getResourceAsStream("/spell/images/spell1.png"), 93, 96);
		
		for(int i = 0; i < 16; i++) {
			Animation animation = new Animation();
			for(int j = 0; j < 14; j++) {
				animation.addFrame(spriteSheet.getSprite(j, i), 100);
			}
			animations[i] = animation;
		}
	}
	
	public int getDamage() {
		return damage;
	}

	public void render(Graphics g) throws NullArgumentException {
		if(g == null) throw new NullArgumentException();
	}
	
	public void update(int delta) {
		if(!world.collideToMonster(this) && range > 0) { //test if collide to wall too
			//update
		}else {
			//kill him in world
		}
	}
}
