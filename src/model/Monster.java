package model;

import org.newdawn.slick.geom.Vector2f;

import exception.TartiException;

/**
 * Enemies of the player
 * @author Tartiflotte
 */
public abstract class Monster extends Character {
	
	private static final long serialVersionUID = -6851386506754886875L;
	
	protected boolean moving;	
	protected boolean attack;
		
	protected final Timer attackTimer;
	
	protected float attackDistance;
	protected float viewDistance;
	protected int fullLife;
	protected int attackPower;
	
	protected long attackInterval; //milliseconds

	
	public Monster(float x, float y, float speed, float attackDist, float viewDist, int fullLife, int attackP, long attackInter){
		super(x, y, speed);		
		
		moving = false;
		direction = 0;
		attack = false;
		
		attackDistance = attackDist;
		viewDistance = viewDist;
		this.fullLife = fullLife;
		life = this.fullLife;
		attackPower = attackP;
		attackInterval = attackInter;
		
		attackTimer = new Timer(attackInterval);
	}
	
	public Monster(Monster other){
		super(other);
		moving = other.moving;
		attack = other.attack;
		
		attackDistance = other.attackDistance;
		viewDistance = other.viewDistance;
		fullLife = other.fullLife;
		attackPower = other.attackPower;
		attackInterval = other.attackInterval;
		
		attackTimer = other.attackTimer;
	}
	
	
	/**
	 * Allows the monster to attack the hero
	 */
	public void attack() throws TartiException{
		float xHero = world.trajectoryToHero(this).getX();
		float yHero = world.trajectoryToHero(this).getY();

		//attack only if within the range
		if (((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= Math.pow(attackDistance, 2)) 
				&& attackTimer.elapsed()){
			attack = true;
			world.getHero().receiveDamage(attackPower);
			attackTimer.start(attackInterval);
		}
		else{
			attack = false;
		}

	}
	/**
	 * @param xHero abscissa of the hero
	 * @param yHero ordinate of the hero
	 * @return a direction vector needed to the displacement of the monster towards the hero
	 */
	protected Vector2f direction(float xHero, float yHero){
		int signX, signY;
		if (xHero <= 0){
			signX = -1; 
			if (Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0){
				direction = 1;
			}
		} else {
			signX = 1;
			if (Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0){
				direction = 2;
			}
		}
		if (yHero <= 0){
			signY = -1;
			if (!(Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0)){
				direction = 3;
			}
		} else {
			signY = 1;
			if (!(Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0)){
				direction = 0;
			}
		}
		 return new Vector2f(signX, signY);
	}
		
}
