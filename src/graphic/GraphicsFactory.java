package graphic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import exception.NotLoadedException;

/**
 * Class from wich we can retrieve animations and graphics used in the project
 * @author Tartiflotte
 *
 */
public class GraphicsFactory {
	
	private static Animation[] spellAnimation;
	private static Animation[] heroAnimation;
	private static Animation[] monsterAnimation;

	public static void loadSpellAnimation() throws SlickException{
		spellAnimation = new Animation[32];
		SpriteSheet spriteSheet = new SpriteSheet("spell", GraphicsFactory.class.getResourceAsStream("/spell/images/spell1.png"), 96, 93);
		
		for(int i = 0; i < 16; i++) {
			Animation animation = new Animation();
			for(int j = 0; j < 14; j++) {
				animation.addFrame(spriteSheet.getSprite(j, i), 200);
			}
			spellAnimation[i] = animation;
		}
	}
	
	
	public static void loadHeroAnimation() throws SlickException {
		heroAnimation = new Animation[9];
		SpriteSheet spriteSheet = new SpriteSheet("hero", GraphicsFactory.class.getResourceAsStream("/hero/images/hero.png"), 80,
				80);

		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0; i < nbDirections; i++) {
			Animation animation = new Animation();
			if (i == 1) {
				animation.addFrame(spriteSheet.getSprite(5, i), 100);
			} else {
				animation.addFrame(spriteSheet.getSprite(0, i), 100);
			}
			heroAnimation[i] = animation;
		}

		// MOVING POSITIONS
		for (int j = 0; j < 4; j++) {
			Animation animation = new Animation();
			for (int i = 0; i < 6; i++) {
				animation.addFrame(spriteSheet.getSprite(i, j), 100);
			}
			heroAnimation[j + nbDirections] = animation;
		}

		// DEAD POSITIONS
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 4), 100);
		heroAnimation[8] = animation;
		// --
	}
	
	
	public static void loadMonsterAnimation() throws SlickException{
		monsterAnimation = new Animation[8];
		SpriteSheet spriteSheet = new SpriteSheet("lycan", GraphicsFactory.class.getResourceAsStream("/monsters/images/lycan.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 200);
			monsterAnimation[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 4 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 200);
			}
			monsterAnimation[j+nbDirections] = animation;
		}
		//--
	}
	
	
	public static Animation[] getSpellAnimation() throws NotLoadedException{
		if(spellAnimation == null) throw new NotLoadedException();
		return spellAnimation;
	}
	
	public static Animation[] getHeroAnimation() throws NotLoadedException{
		if(heroAnimation == null) throw new NotLoadedException();
		return heroAnimation;
	}
	
	public static Animation[] getMonsterAnimation() throws NotLoadedException{
		if(monsterAnimation == null) throw new NotLoadedException();
		return monsterAnimation;
	}

}
