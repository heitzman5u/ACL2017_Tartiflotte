package graphic;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import exception.NotLoadedException;

/**
 * Class from wich we can retrieve animations and graphics used in the project
 * 
 * @author Tartiflotte
 *
 */
public class GraphicsFactory {

	private static Animation[] spellAnimation;
	private static Animation[] heroAnimation;
	private static Animation[] monsterAnimation;
	private static Animation[] bossAnimation;
	private static Animation littleFlaskAnimation;
	private static Animation exitAnimation;
	private static Animation scratchAnimation;
	private static Image monsterLifeBarImg;
	private static Image monsterLifeImg;

	private static HudHeroInfo hudHero;

	public static void loadSpellAnimation() throws SlickException {
		spellAnimation = new Animation[32];
		SpriteSheet spriteSheet = new SpriteSheet("spell",
				GraphicsFactory.class.getResourceAsStream("/spell/images/spell1.png"), 96, 93);

		for (int i = 0; i < 16; i++) {
			Animation animation = new Animation();
			for (int j = 0; j < 14; j++) {
				animation.addFrame(spriteSheet.getSprite(j, i), 200);
			}
			spellAnimation[i] = animation;
		}
	}

	public static void loadHeroAnimation() throws SlickException {
		heroAnimation = new Animation[9];
		SpriteSheet spriteSheet = new SpriteSheet("hero",
				GraphicsFactory.class.getResourceAsStream("/hero/images/hero.png"), 80, 80);

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

	public static void loadWolfAnimation() throws SlickException {
		monsterAnimation = new Animation[8];
		SpriteSheet spriteSheet = new SpriteSheet("lycan",
				GraphicsFactory.class.getResourceAsStream("/monsters/images/lycan.png"), 80, 80);

		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0; i < nbDirections; i++) {
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 200);
			monsterAnimation[i] = animation;
		}

		// MOVING POSITIONS
		for (int j = 0; j < 4; j++) {
			Animation animation = new Animation();
			for (int i = 0; i < 4; i++) {
				animation.addFrame(spriteSheet.getSprite(i, j), 200);
			}
			monsterAnimation[j + nbDirections] = animation;
		}
		// --
	}

	public static void loadLittleFlaskAnimation() throws SlickException {
		littleFlaskAnimation = new Animation();
		SpriteSheet spriteSheet = new SpriteSheet("littleFlask",
				GraphicsFactory.class.getResourceAsStream("/flask/images/LittleLifeFlask.png"), 50, 50);
		littleFlaskAnimation.addFrame(spriteSheet.getSprite(0, 0), 300);
		littleFlaskAnimation.addFrame(spriteSheet.getSprite(1, 0), 300);
	}

	public static void loadExitAnimation() throws SlickException {
		exitAnimation = new Animation();
		SpriteSheet spriteSheet = new SpriteSheet("exit_arrow",
				GraphicsFactory.class.getResourceAsStream("/maps/images/exit_case_arrow.png"), 20, 25);
		for (int i = 0; i < 4; i++) {
			exitAnimation.addFrame(spriteSheet.getSprite(i, 0), 150);
		}
	}

	public static void loadBossAnimation() throws SlickException {
		bossAnimation = new Animation[4];
		SpriteSheet spriteSheet = new SpriteSheet("boss",
				GraphicsFactory.class.getResourceAsStream("/boss/images/boss.png"), 107, 96);

		for (int i = 0; i < 4; i++) {
			Animation animation = new Animation();
			for (int j = 0; j < 4; j++) {
				animation.addFrame(spriteSheet.getSprite(j, i), 200);
			}
			bossAnimation[i] = animation;
		}
	}

	public static void loadScratchAnimation() throws SlickException {
		scratchAnimation = new Animation();
		SpriteSheet spriteSheet = new SpriteSheet("griffure",
				GraphicsFactory.class.getResourceAsStream("/monsters/images/scratch.png"), 60, 60);
		scratchAnimation.addFrame(spriteSheet.getSprite(0, 0), 100);
	}

	public static void loadMonsterLifeBarImages() throws SlickException {
		monsterLifeImg = new Image("/res/monsters/images/life.jpg");
		monsterLifeBarImg = new Image("/res/monsters/images/life_bar.png");
	}
	
	public static void loadBossLifeBarImages() {
		// TODO Auto-generated method stub
		
	}

	public static void loadHudHero() throws SlickException {
		hudHero = new HudHeroInfo();
	}

	// -------------------------------------------------------------------------------------------

	public static Animation[] getSpellAnimation() throws NotLoadedException {
		if (spellAnimation == null)
			throw new NotLoadedException();
		return spellAnimation;
	}

	public static Animation[] getHeroAnimation() throws NotLoadedException {
		if (heroAnimation == null)
			throw new NotLoadedException();
		return heroAnimation;
	}

	public static Animation[] getMonsterAnimation() throws NotLoadedException {
		if (monsterAnimation == null)
			throw new NotLoadedException();
		return monsterAnimation;
	}

	public static Animation getLittleFlaskAnimation() throws NotLoadedException {
		if (littleFlaskAnimation == null)
			throw new NotLoadedException();
		return littleFlaskAnimation;
	}

	public static Animation getExitAnimation() throws NotLoadedException {
		if (exitAnimation == null)
			throw new NotLoadedException();
		return exitAnimation;
	}

	public static Animation getScratchAnimation() throws NotLoadedException {
		if (scratchAnimation == null)
			throw new NotLoadedException();
		return scratchAnimation;
	}

	public static Image getMonsterLifeBarImage() throws NotLoadedException {
		if (monsterLifeBarImg == null)
			throw new NotLoadedException();
		return monsterLifeBarImg;
	}

	public static Image getMonsterLifeImage() throws NotLoadedException {
		if (monsterLifeImg == null)
			throw new NotLoadedException();
		return monsterLifeImg;
	}

	public static HudHeroInfo getHudHero() throws NotLoadedException {
		if (hudHero == null)
			throw new NotLoadedException();
		return hudHero;
	}
	
	public static Animation[] getBossAnimation() throws NotLoadedException {
		if (bossAnimation == null)
			throw new NotLoadedException();
		return bossAnimation;
	}
}
