package model;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

public class Boss extends Monster{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1480519936011295029L;
	private long spellInterval;
	private TrueTypeFont ttf;

	public Boss(float x, float y) {
		super(x, y, 3.0f, 40f, 80000f, 100, 2, 1000);
		
		spellInterval=2000;
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
	}
	
	public void attackSpell() {
		
	}
	
	public void move(int delta) {
		
	}
	
	/**
	 * create a spell and add it to the list in world
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public void spawnSpell(float x, float y, float xCible, float yCible) {
		if(attackTimer.elapsed()){
			Spell sp=new EnemySpell(x, y, xCible, yCible);
			sp.setWorld(world);
			world.addSpell(sp);
			attackTimer.start(spellInterval);
		}
	}
	
	private void displayName() {
		ttf.drawString(1120/2-ttf.getWidth("Galdepin")/2, 720, "Galdepin",new Color(107,13,13));
	}
	
	/**
	 * Display the life bar of the monster
	 * @throws NotLoadedException 
	 */
	private void lifeBarHUD() throws NotLoadedException{
		Image lifeBarImg = GraphicsFactory.getBossLifeBarImage();
		Image lifeImg = GraphicsFactory.getBossLifeImage();
		
		float bigger = 400;
		float lifeRatio = life/(float)fullLife;
		float width = (float)lifeImg.getWidth() + bigger;
		float height = (float)lifeImg.getHeight();
		float widthRatio = ((float)lifeImg.getWidth()+bigger) * lifeRatio;

		lifeBarImg.draw(1120/2-width/2, 750, width, height);
		lifeImg.draw(1120/2-width/2, 753, widthRatio-4, height-6);
	}
	
	
	@Override
	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		//move(delta);
		attack();
		/*if(life <= fullLife/2) {
			spawnSpell();
		}*/

		spawnSpell(getX(), getY(), world.getHero().getX(), world.getHero().getY());
			
	}
	
	public void render(Graphics g) throws TartiException {
		if(g == null) throw new NullArgumentException();
		
		Animation[] animations = GraphicsFactory.getBossAnimation();
		
		//Boss Animation
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		//put the deplacement fonctions
		g.drawAnimation(animations[1], pos.x-40, pos.y-65);
		
		lifeBarHUD();
		displayName();
	}
}
	
	

