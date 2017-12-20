package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;
/**
 * Enemies of the player
 * @author Tartiflotte
 */
public class Monster extends Character {
	
	private static final long serialVersionUID = -6851386506754886875L;
	
	
	private boolean moving;	
	private boolean attack;
	
	private static final float SPEED = 2.0f;
	private static final float ATTACK_DISTANCE = 600f;
	private static final float VIEW_DISTANCE = 80_000f;
	private static final int FULL_LIFE = 10;
	
	/**
	 * Create a monster at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Monster(float x, float y) {
		super(x, y, SPEED);
		
		moving = false;
		direction = 0;
		life = FULL_LIFE;
		attack = false;
	}
	
	/**
	 * Create a copy of a monster
	 * @param monster copied
	 */
	private Monster(Monster other){
		super(other);
		
		moving = other.moving;
		attack = other.attack;
	}
	
	/**
	 * Allow the monster to move towards the hero
	 * @param delta milliseconds since last frame
	 */
	public void move(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
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
	private Monster futurePos(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		Monster m = new Monster(this);
		m.move(delta);
		return m;
	}
	
	/**
	 * Allows the monster to attack the hero
	 */
	public void attack() throws TartiException{
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
	public void update(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		if(!world.collideToWall(futurePos(delta))){
			move(delta);
		}
		attack();
	}
	
	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		
		Animation[] animations = GraphicsFactory.getMonsterAnimation();
		// MONSTER ANIMATION
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], pos.x-40, pos.y-65);
		
		// ATTACK ANIMATION
		if (attack == true){
			Animation attackAnim = GraphicsFactory.getScratchAnimation();
			g.drawAnimation(attackAnim, world.getHero().getX()-30, world.getHero().getY()-30);
		}
		
		// LIFE BAR
		lifeBarHUD();
		
		// --
	}
	
	
	/**
	 * Display the life bar of the monster
	 * @throws NotLoadedException 
	 */
	private void lifeBarHUD() throws NotLoadedException{
		Image lifeBarImg = GraphicsFactory.getMonsterLifeBarImage();
		Image lifeImg = GraphicsFactory.getMonsterLifeImage();
		
		float lifeRatio = life/(float)FULL_LIFE;
		float width = (float)lifeImg.getWidth();
		float height = (float)lifeImg.getHeight();
		float widthRatio = (float)lifeImg.getWidth() * lifeRatio;

		lifeBarImg.draw(getX() - (width/2), getY() - 60, 1.0f);
		lifeImg.draw(getX() - (width/2) +1, getY()+1 - 60, widthRatio, height);
	}
	
}
