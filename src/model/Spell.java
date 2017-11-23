package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;

public class Spell extends WorldObject{

	private Animation[] animations;
	private int spritePos;
	private int damage;
	private float range;
	private float speed;
	private Vector2f direction;
	
	protected Spell(float x, float y, Vector2f dir) throws SlickException {
		super(x, y);
		speed = 0.7f;
		damage = 2;
		range = 50;
		direction=dir.getNormal();
		spritePos=calculateSpritePosition();
		animations = new Animation[32];
		creationAnimation();
	}
		
	private void creationAnimation() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("spell", getClass().getResourceAsStream("/spell/images/spell1.png"), 96, 93);
		
		for(int i = 0; i < 16; i++) {
			Animation animation = new Animation();
			for(int j = 0; j < 14; j++) {
				animation.addFrame(spriteSheet.getSprite(j, i), 200);
			}
			animations[i] = animation;
		}
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int calculateSpritePosition(){
		double approachedAngle=0, actualAngle=direction.getTheta();
		double valAngInter=360/32, valAngAppr=360/16;
		int i=0;
		while(Math.abs(actualAngle-approachedAngle)>=valAngInter && approachedAngle<361){
			approachedAngle+=valAngAppr;
			i=(i+1)%16;
		}
		return (11+i)%16;
	}
	
	public Vector2f futurePos(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		Vector2f newPos = getPos();
		newPos.add(direction.copy().scale(speed*delta));
		return newPos;
	}
	
	public boolean canMove(Vector2f pos){
		return true; //à changer pour prendre en compte les collisions
	}
	
	public void move(int delta)throws TartiException {
		if (delta < 0)
			throw new InvalidArgumentException("delta >= 0");
		
		Vector2f newPos=futurePos(delta);
		setPos(newPos);
	}

	public void render(Graphics g) throws NullArgumentException {
		if(g == null) throw new NullArgumentException();
		g.drawAnimation(animations[spritePos], pos.x-48, pos.y-60);
	}
	
	public void update(int delta) throws TartiException {
		
		Monster m = world.collideToMonster(this);
		
		if(m == null && range > 0 && !world.collideToWall(this)) {
			move(delta);
		}else {
			if(m != null && m.getLife() <= 0)
				world.destroyObject(m);
			world.destroyObject(this);
		}
	}
}
