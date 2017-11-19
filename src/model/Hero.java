package model;

import java.util.Iterator;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import exception.NullArgumentException;

/**
 * Main character of the game ; character that the player control
 * @author Tartiflotte
 */
public class Hero extends Character {
	private PlayerController playerController;
	private Animation[] animations;
	
	private static final float SPEED = 0.2f;
	private static final int FULL_LIFE = 10;

	private HudHeroInfo hudLifeFlask;
	private int nbFlasks;
	
	/**
	 * create a new Hero at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Hero(float x, float y) throws SlickException{
		super(x, y, SPEED);
		playerController = new PlayerController(this);
		
		nbFlasks = 0;
		life = FULL_LIFE;
		animations = new Animation[9];
		creationAnimations();
		
		hudLifeFlask = new HudHeroInfo();
	}
	
	
	/**
	 * Copy a Hero. Deep copy of position, orientation, nb of flasks
	 * Shallow copy of other objects
	 * @param other the copied hero
	 */
	public Hero(Hero other){
		super(other);
		playerController = other.playerController;
		animations = other.animations;
		
		nbFlasks = other.nbFlasks;
	}
	
	/**
	 * Allow the hero to move towards the hero
	 * @param delta milliseconds since last frame
	 */
	public void move(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		if (isAlive()){
			//scale to have constant speed
			Vector2f vspeed = playerController.getMovement().scale(speed*(float)delta);
			pos.add(vspeed);
		}
	}
	

	/**
	 * Get copy of the Hero, after this frame, if nothing blocks his path
	 * @param delta milliseconds since last frame
	 * @return the future position of the Hero ; needed for the collisions
	 */
	public Hero futurePos(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		Hero h = new Hero(this);
		h.move(delta);
		return h;
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g){
		if(g == null) throw new NullArgumentException();
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		if (isAlive()){
			g.drawAnimation(animations[direction + (playerController.isMoving() ? 4 : 0)], pos.x-40, pos.y-65);
		} else {
			g.drawAnimation(animations[8], pos.x-40, pos.y-65);
		}
		hudLifeFlask.render(g);
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta){
		setDirection();
		if(playerController.isMoving() 
				&& !world.collideToWall(futurePos(delta)) ){
			move(delta);
		}
		hudLifeFlask.update(delta, nbFlasks);
	}
	
	/**
	 * create the differents animations of the hero thanks to his SpriteSheet
	 */
	private void creationAnimations() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("hero", getClass().getResourceAsStream("/hero/images/hero.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			if (i == 1){
				animation.addFrame(spriteSheet.getSprite(5, i), 100);
			} else {
				animation.addFrame(spriteSheet.getSprite(0, i), 100);
			}
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 6 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 100);
			}
			animations[j+nbDirections] = animation;
		}
		
		// DEAD POSITIONS
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 4), 100);
		animations[8] = animation;		
		//--
	}
	
	public PlayerController getPlayerController(){
		return playerController;
	}
	
	private void setDirection(){
		direction = playerController.getDirection();
	}
	
	
	public void attackMonsters(){
		Iterator<Monster> it = world.getMonsters();
		
		while(it.hasNext()){
		Monster m = it.next();
			if(distance(m)<=1200f){
				m.setAlive(false);
			}
		}
	}

	public void useFlask(){
		if (nbFlasks > 0){
			nbFlasks --;
			if ((life + LifeFlask.HP) >= FULL_LIFE){
				life = FULL_LIFE;
			} else {
				life += LifeFlask.HP;
			}
		}
	}

	public int getNbFlasks() {
		return nbFlasks;
	}
	
	/**
	 * Add a flask to the hero's inventory
	 */
	public void pickFlask(){
		nbFlasks++;
	}
		
}
