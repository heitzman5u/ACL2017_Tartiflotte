package model;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
	
	public HudHeroInfo() throws SlickException{
		animationBigFlask = new Animation[3];
		nbFlaskHero = 0;
		creationAnimationBigFlask();
		
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
	}
	
	
	private void creationAnimationBigFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("bigFlask", getClass().getResourceAsStream("/flask/images/BigLifeFlask.png"), 150, 150);
		
		for (int j = 0 ; j < 3 ; j++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(j, 0), 100);
			animationBigFlask[j] = animation;
		}
	}
	
	/**
	 * @see Game.update()
	 */
	public void update(int delta, int nbFlask){
		nbFlaskHero = nbFlask;
	}
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g){
		//float nbFlaskHero = hero.getNbFlasks();
		if(nbFlaskHero > 5)
			g.drawAnimation(animationBigFlask[0], 10, 10);
		if(nbFlaskHero > 0)
			g.drawAnimation(animationBigFlask[1], 10, 10);
		if(nbFlaskHero == 0)
			g.drawAnimation(animationBigFlask[2], 10, 10);
		g.setColor(Color.white);

		ttf.drawString(110.0f, 120.0f, "x"+ nbFlaskHero, Color.white);
		//g.drawString("x"+ nbFlaskHero, 110, 120); 
		
	}

}
