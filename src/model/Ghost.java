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

public class Ghost extends Monster {
	private Timer timerAnimAttack;
	
	private boolean meet;
	
	/**
	 * Create a wolf at the given position
	 * @param x abscissa
	 * @param y ordinate
	 * @throws SlickException
	 */
	public Ghost(float x, float y) {
		super(x, y, 1.2f , 24f, 130f, 10, 1, 1000);
		timerAnimAttack = new Timer();
		meet = false;
	}
	
	/**
	 * Create a copy of a wolf
	 * @param monster copied
	 */
	private Ghost(Ghost other){
		super(other);
	}
	
	private Monster futurePos(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		Ghost m = new Ghost(this);
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
		if(((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= Math.pow(viewDistance, 2) || meet == true) 
				&& ((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) >= Math.pow(attackDistance, 2))){
			moving = true;
			meet = true;
			pos.add(direction(xHero, yHero).scale(speed));	
		} else {
			moving = false;
		}
	}
	
	public void render(Graphics g) throws TartiException {
		if(g == null) throw new NullArgumentException();
		if(world.trajectoryToHero(this).length() <= viewDistance){
			
		Animation[] animations = GraphicsFactory.getGhostAnimation();
		// MONSTER ANIMATION
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-16, pos.y, 32, 10);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], pos.x-16, pos.y-30);
		
		// ATTACK ANIMATION
		if (attack == true){
			timerAnimAttack.start(100);
		}
		Animation attackAnim;
		if (!timerAnimAttack.elapsed()){
			attackAnim = GraphicsFactory.getBloodAnimation();
			g.drawAnimation(attackAnim, world.getHero().getX()-30, world.getHero().getY()-30);
		}
		
		// LIFE BAR
		lifeBarHUD();
			
		// --
		}
		
	}

	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		if(!world.collideToWall(futurePos(delta))){
			move(delta);
		}
		attack();
	}
	
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
