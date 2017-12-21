package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

public class Wolf extends Monster {	

	/**
	 * Create a wolf at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Wolf(float x, float y) {
		super(x, y, 2.0f , 24f, 283f, 10, 3, 1000);
	}
	
	/**
	 * Create a copy of a wolf
	 * @param monster copied
	 */
	private Wolf(Wolf other){
		super(other);
	}
	
	/**
	 * Get copy of the Monster, after this frame, if nothing blocks his path
	 * @param delta milliseconds since last frame
	 * @return the future position of the Monster ; needed to the collisions
	 */
	private Monster futurePos(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		Wolf m = new Wolf(this);
		m.move(delta);
		return m;
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
		if(((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= Math.pow(viewDistance,2)) 
				&& ((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) >= Math.pow(attackDistance, 2))){
			moving = true;
			pos.add(direction(xHero, yHero).scale(speed));	
		} else {
			moving = false;
		}
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
		
		float lifeRatio = life/(float)fullLife;
		float width = (float)lifeImg.getWidth();
		float height = (float)lifeImg.getHeight();
		float widthRatio = (float)lifeImg.getWidth() * lifeRatio;

		lifeBarImg.draw(getX() - (width/2), getY() - 60, 1.0f);
		lifeImg.draw(getX() - (width/2) +1, getY()+1 - 60, widthRatio, height);
	}
	
}
