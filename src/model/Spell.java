package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;
import test.SafeMethod;

public class Spell extends WorldObject{

	private static final long serialVersionUID = 5044107265511033106L;
	
	
	private int damage;
	private float range; // in seconds
	private float speed;
	private Vector2f direction;
	
	public Spell(float x, float y, Vector2f dir) {
		super(x, y);
		speed = 0.7f;
		damage = 2;
		range = 0.3f;
		direction=dir.getNormal();
	}
	
	public void setSpeed(float f){
		speed = f;
	}
	
	public void setRange(float r){
		range = r;
	}
	
	public int getDamage() {
		return damage;
	}
	
	private int calculateSpritePosition(){
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
		return true; //change to take account of collisions
	}
	
	public void move(int delta)throws TartiException {
		if (delta < 0)
			throw new InvalidArgumentException("delta >= 0");
		
		Vector2f newPos=futurePos(delta);
		setPos(newPos);
	}

	public void render(Graphics g) throws NullArgumentException, TartiException {
		if(g == null) throw new NullArgumentException();
		Animation[] animations = GraphicsFactory.getSpellAnimation();
		final int spritePos = calculateSpritePosition();
		g.drawAnimation(animations[spritePos], pos.x-48, pos.y-60);
	}
	
	public void update(int delta) throws TartiException {
		
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
