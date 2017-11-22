package model;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;



/**
 * Hud that indicates how many life flasks are in the hero's inventory
 * @author Tartiflotte
 *
 */
public class HudHeroInfo {
	
	private Animation[] animationBigFlask;
	private TrueTypeFont ttf;
	
	private int nbFlaskHero;
	
	private float fullLifeHero;
	private float lifeHero;
	
	private Image lifeBarImg;
	private Image lifeImg;
	
	public HudHeroInfo(float fullLife, float life) throws SlickException{
		animationBigFlask = new Animation[3];
		nbFlaskHero = 0;
		fullLifeHero = fullLife;
		lifeHero = life;
		
		creationAnimationBigFlask();
		
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
		
		lifeBarImg = new Image("/res/hero/images/life_bar.png");
		lifeImg = new Image("/res/hero/images/life.jpg");
	}
	
	/**
	 * Display the animation of the number of life flask
	 * @throws SlickException
	 */
	private void creationAnimationBigFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("bigFlask", getClass().getResourceAsStream("/flask/images/BigLifeFlask.png"), 100, 100);
		
		for (int j = 0 ; j < 3 ; j++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(j, 0), 100);
			animationBigFlask[j] = animation;
		}
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta, int nbFlask, float life){
		nbFlaskHero = nbFlask;
		lifeHero = life;
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g){
		flaskHUD(g);
		lifeBarHUD();
	}
	
	
	/**
	 * Display of the number of life flask of the hero
	 * @param g Graphics
	 */
	private void flaskHUD(Graphics g){
		if(nbFlaskHero > 5)
			g.drawAnimation(animationBigFlask[0], 10, 20);
		if(nbFlaskHero > 0)
			g.drawAnimation(animationBigFlask[1], 10, 20);
		if(nbFlaskHero == 0)
			g.drawAnimation(animationBigFlask[2], 10, 20);
		g.setColor(Color.white);

		ttf.drawString(80.0f, 90.0f, "x"+ nbFlaskHero, Color.white);
	}

	/**
	 * Display of the life bar of the hero 
	 */
	private void lifeBarHUD(){
		lifeBarImg.draw(10, 10, 1.0f);
		
		float lifeRatio = lifeHero/fullLifeHero;
		float width = (int)lifeImg.getWidth() * lifeRatio;
		float height = (int)lifeImg.getHeight();
		lifeImg.draw(10,12,width,height);
	}
	
}
