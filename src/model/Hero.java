package model;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import controller.PlayerCommand;
import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;
import graphic.HudHeroInfo;
//import test.SafeMethod;

/**
 * Main character of the game ; character that the player control
 * 
 * @author Tartiflotte
 */

public class Hero extends Character {

	private static final long serialVersionUID = 3797645190029724829L;
	

	private transient static final float SPEED = 0.2f;
	private transient static final int FULL_LIFE = 10;
	
	private transient Vector2f movement;

	private transient int nbFlasks;

	/**
	 * create a new Hero at the given position
	 * 
	 * @param x
	 *            abscissa
	 * @param y
	 *            ordinate
	 * @throws SlickException
	 * @throws TartiException 
	 */
	public Hero(float x, float y){
		super(x, y, SPEED);
		
		movement = new Vector2f();
		nbFlasks = 0;
		life = 6;
		try {
			GraphicsFactory.getHudHero().setFullLife((float)FULL_LIFE);
		} catch (NotLoadedException e) {
			System.err.println("The hero's hud was not properly loaded");
		}
	}

	
	public Hero(Hero other) throws TartiException{
		super(other);
		//animations = other.animations;
		movement = other.movement.copy();

		nbFlasks = other.nbFlasks;
	}
	
	
	/**
	 * For testing purpose
	 * @param m
	 */
	@Deprecated
	public void setMovement(Vector2f m){
		//SafeMethod.forTesting();
		movement = m;
	}
	
	/**
	 * For testing purpose
	 * @return 
	 */
	@Deprecated
	public Vector2f getMovement(){
		//SafeMethod.forTesting();
		return movement;
	}
	

	public String toString(){
		return "<"+pos.x+";"+pos.y+">";
	}
	
	/**
	 * Allow the Hero to use one of his life flask to regain HP
	 */
	public void useFlask() {
		if (nbFlasks > 0) {
			nbFlasks--;
			if ((life + LifeFlask.HP) >= FULL_LIFE) {
				life = FULL_LIFE;
			} else {
				life += LifeFlask.HP;
			}
		}
	}

	/**
	 * Get copy of the Hero, after this frame, if nothing blocks his path
	 * 
	 * @param delta
	 *            milliseconds since last frame
	 * @return the future position of the Hero ; needed for the collisions
	 */
	public Hero futurePos(int delta) throws TartiException {
		if (delta < 0)
			throw new InvalidArgumentException("delta >= 0");
		Hero h = new Hero(this);
		h.move(delta);
		return h;
	}
	

	/**
	 * Allow the hero to move towards the hero
	 * @param delta milliseconds since last frame
	 */
	public void move(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		if (isAlive()){
			//scale to have constant speed
			Vector2f vspeed = movement.getNormal().scale(speed*(float)delta);
			pos.add(vspeed);
		}
	}

	/**
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		Animation[] animations = GraphicsFactory.getHeroAnimation();
		if (isAlive()){
			g.drawAnimation(animations[direction + (movement.length() > 0.0001 ? 4 : 0)], pos.x-40, pos.y-65);
		} else {
			g.drawAnimation(animations[8], pos.x - 40, pos.y - 65);
		}
		HudHeroInfo hudLifeFlask = GraphicsFactory.getHudHero();
		hudLifeFlask.update(nbFlasks, (float) life);
		hudLifeFlask.render(g);
	}

	/**
	 * @see Game.update()
	 */
	public void update(int delta) throws TartiException {
		setDirection();
		if( !world.collideToWall(futurePos(delta)) ){
			move(delta);
		}
	}
	
	
	
	public void receiveCommand(PlayerCommand c){
		if(c.hasMovement()){
			movement.add(c.getMovement());
		}
		if (c == PlayerCommand.USE_FLASK){
			useFlask();
		}
		if (c == PlayerCommand.ATTACK){
			attackMonsters();
		}
		if (c == PlayerCommand.NEXT_LEVEL){
			Game.getInstance().loadNextLevel();
		}
	}
	
	
	private void setDirection(){
		double angle = movement.getTheta();
		double demiQuart=360/8;
		int dir=direction;
		
		if(movement.length() < 0.0001){
			return; //no change
		}
		
		if((angle<demiQuart) || angle>7*demiQuart){
			dir=0;
		}
		else if(angle>=demiQuart && angle<=3*demiQuart){
			dir=3; 
		}
		else if(angle>3*demiQuart && angle<5*demiQuart){
			dir=1;
		}
		else if(angle>=5*demiQuart && angle<=7*demiQuart){
			dir=2;
		}
		else{
			throw new RuntimeException("Impossible angle case found!");
		}
		direction = dir;
	}

	public void attackMonsters() {
		for (Monster m : world.getMonsters()) {
			if (distance(m) <= 1200f) {
				m.setAlive(false);
			}
		}
	}

	public int getNbFlasks() {
		return nbFlasks;
	}

	/**
	 * Add a flask to the hero's inventory
	 */
	public void pickFlask() {
		nbFlasks++;
	}
	
	/**
	 * create a spell and add it to the list in hero
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public void spawnSpell(float x, float y, Vector2f dir) {
		Spell sp=new HeroSpell(x, y, dir);
		sp.setWorld(world);
		world.addSpell(sp);
	}

}
