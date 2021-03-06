package graphic;

import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;



/**
 * Hud that indicates how many life flasks are in the hero's inventory
 * @author Tartiflotte
 *
 */
public class HudHeroInfo {
	
	private Animation[] animationBigFlask;
	private Animation animationAttackDamage;
	private TrueTypeFont ttf;
	
	private int nbFlaskHero;
	private int nbAttackBoostHero;
	
	private float fullLifeHero;
	private float lifeHero;
	
	private Image lifeBarImg;
	private Image lifeImg;
	private Image attackBoostImg;
	
	public HudHeroInfo() throws SlickException{
		animationBigFlask = new Animation[3];
		nbFlaskHero = 0;
		nbAttackBoostHero = 0;
		
		creationAnimationBigFlask();
		
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
		
		lifeBarImg = new Image("/res/hero/images/life_bar.png");
		lifeImg = new Image("/res/hero/images/life.jpg");
		attackBoostImg = new Image("/res/flask/images/attackBoost.png");
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
	 * Update the hud components with current status
	 * @param nbFlask number of flasks the hero has
	 * @param life life the hero has
	 */
	public void update(int nbAttackBoost, int nbFlask, float life){
		nbAttackBoostHero = nbAttackBoost;
		nbFlaskHero = nbFlask;
		lifeHero = life;
	}
	
	
	/**
	 * Set the maximum health
	 * @param life 
	 */
	public void setFullLife(float life){
		fullLifeHero = life;
	}
	
	
	/**
	 * @see Game.render()
	 */
	public void render(Graphics g){
		flaskHUD(g);
		lifeBarHUD();
		boostHUD(g);
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

	private void boostHUD(Graphics g){
		g.setColor(new Color(78,61,40));
		g.fillRect(100, 35, attackBoostImg.getWidth(), attackBoostImg.getHeight());
		g.setColor(Color.black);
		g.drawRect(100, 35, attackBoostImg.getWidth(), attackBoostImg.getHeight());
		if(nbAttackBoostHero > 0){
			attackBoostImg.draw(100, 35, 1.0f);
		}
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
