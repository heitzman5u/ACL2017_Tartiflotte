package model;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import exception.TartiException;
import graphic.GraphicsFactory;

/**
 * Graphic interface of the game
 * @author Tartiflotte
 */
public class GameUI extends BasicGame {
	
	private GameContainer container;
	
	private static final int FRAME_RATE = 60;


	/**
	 * create a new GameUI, where the game will run
	 * @param title title of the window
	 */
	public GameUI(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		try{
			container = arg0;
			container.setTargetFrameRate(FRAME_RATE);
			
			//load graphics
			GraphicsFactory.loadSpellAnimation();
			GraphicsFactory.loadHeroAnimation();
			GraphicsFactory.loadWolfAnimation();
			GraphicsFactory.loadGhostAnimation();
			GraphicsFactory.loadLittleFlaskAnimation();
			GraphicsFactory.loadExitAnimation();
			GraphicsFactory.loadScratchAnimation();
			GraphicsFactory.loadMonsterLifeBarImages();
			GraphicsFactory.loadHudHero();
			
			
			//Set the game
			PlayerController pc = new PlayerController();
			container.getInput().addKeyListener(pc);
			container.getInput().addMouseListener(pc);
			HudMessage victory = new HudMessage("/hud/victory_achieved.png");
			World w = new World(1);
			Music m = new Music(getClass().getResourceAsStream("/musics/abyss_watchers.ogg"), "abyss_watchers.ogg");
			m.loop();
			
			Game.getInstance().setContext(w, victory, pc);
		}catch(TartiException e){
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void update(GameContainer arg0, int delta) {
		try{
			Game.getInstance().update(delta);
		}catch(SlickException | TartiException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer arg0, Graphics g) {
		try{
			Game.getInstance().render(g);
		}catch(TartiException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }
	

}
