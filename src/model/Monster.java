package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import exception.NullArgumentException;
/**
 * Enemies of the player
 * @author Tartiflotte
 */
public class Monster extends Character {
	
	private Animation[] animations;
	
	private boolean moving;	
	private boolean attack;
	
	private static final float SPEED = 2.0f;
	private static final float ATTACK_DISTANCE = 600f;
	private static final float VIEW_DISTANCE = 80_000f;
	
	/**
	 * Create a monster at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Monster(float x, float y) throws SlickException{
		super(x, y, SPEED);
		
		moving = false;
		direction = 0;
		
		attack = false;
		
		animations = new Animation[8];

		creationAnimations();
	}
	
	/**
	 * Create a copy of a monster
	 * @param monster copied
	 */
	private Monster(Monster other){
		super(other.getX(), other.getY(), SPEED, other.direction, other.world);
		
		moving = other.moving;
		attack = other.attack;
		
		//shallow copy of animations (because they should share the same animations, no deep copy needed)
		animations = other.animations;
	}
	
	/**
	 * Allow the monster to move towards the hero
	 * @param delta milliseconds since last frame
	 */
	public void move(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		float xHero = world.trajectoryToHero(this).getX();
		float yHero = world.trajectoryToHero(this).getY();
		
		//move only if the hero is within his view range, and no collisions will occur
		if(((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= VIEW_DISTANCE) 
				&& ((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) >= ATTACK_DISTANCE)){
			moving = true;
			pos.add(direction(xHero, yHero).scale(speed));	
		} else {
			moving = false;
		}
	}
	
	/**
	 * Get copy of the Monster, after this frame, if nothing blocks his path
	 * @param delta milliseconds since last frame
	 * @return the future position of the Monster ; needed to the collisions
	 */
	private Monster futurePos(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		Monster m = new Monster(this);
		m.move(delta);
		return m;
	}
	
	/**
	 * Allows the monster to attack the hero
	 */
	public void attack(){
		float xHero = world.trajectoryToHero(this).getX();
		float yHero = world.trajectoryToHero(this).getY();

		//attack only if within the range
		if (((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= ATTACK_DISTANCE)){
			attack = true;
			world.getHero().setAlive(false);
		}

	}
	/**
	 * @param xHero abscissa of the hero
	 * @param yHero ordinate of the hero
	 * @return a direction vector needed to the displacement of the monster towards the hero
	 */
	private Vector2f direction(float xHero, float yHero){
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
	
	/**
	 * 
	 * @see Game.update()
	 */
	public void update(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		if(!world.collideToWall(futurePos(delta))){
			move(delta);
		}
		attack();
	}
	
	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g){
		if(g == null) throw new NullArgumentException();
		// MONSTER ANIMATION
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], pos.x-40, pos.y-65);
		
		// ATTACK ANIMATION
		if (attack == true){
			try {
				SpriteSheet spriteSheet;
				spriteSheet = new SpriteSheet("griffure", getClass().getResourceAsStream("/monsters/images/scratch.png"), 60, 60);
				Animation attackAnim = new Animation();
				attackAnim.addFrame(spriteSheet.getSprite(0, 0), 100);
				g.drawAnimation(attackAnim, world.getHero().getX()-30, world.getHero().getY()-30);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		// --
	}
	
	/**
	 * create the differents animations of the monster thanks to his SpriteSheet
	 * @throws SlickException
	 */
	private void creationAnimations() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("lycan", getClass().getResourceAsStream("/monsters/images/lycan.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 200);
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 4 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 200);
			}
			animations[j+nbDirections] = animation;
		}
		//--
	}
}
