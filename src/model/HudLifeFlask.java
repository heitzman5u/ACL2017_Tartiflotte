package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.UnicodeFont;

import javafx.scene.paint.Color;

public class HudLifeFlask {
	
	private Animation[] animationBigFlask;
	//private Hero hero;
	
	private int nbFlaskHero;
	
	public HudLifeFlask() throws SlickException{
		animationBigFlask = new Animation[3];
		nbFlaskHero = 0;
		creationAnimationBigFlask();
	}
	
	
	private void creationAnimationBigFlask() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("bigFlask", getClass().getResourceAsStream("/flask/images/BigLifeFlask.png"), 150, 150);
		
		for (int j = 0 ; j < 3 ; j++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(j, 0), 100);
			animationBigFlask[j] = animation;
		}
	}
	
	public void update(int delta, int nbFlask){
		nbFlaskHero = nbFlask;
	}
	
	public void render(Graphics g){
		//float nbFlaskHero = hero.getNbFlasks();
		if(nbFlaskHero >= 10)
			g.drawAnimation(animationBigFlask[0], 10, 10);
		if(nbFlaskHero >= 5)
			g.drawAnimation(animationBigFlask[1], 10, 10);
		if(nbFlaskHero >= 0)
			g.drawAnimation(animationBigFlask[2], 10, 10);
		//g.setFont(new UnicodeFont(new java.awt.Font(java.awt.Font.SANS_SERIF, java.awt.Font.PLAIN, 200)));
		//g.setColor(new Color(0f,0f,0f));
		g.drawString("x"+ nbFlaskHero, 110, 120); 
		
	}

}
