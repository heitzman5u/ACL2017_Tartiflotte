package model;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;

/**
 * Represents the model of the game
 * Singleton: there won't be two games running in the same time
 * @author Tartiflotte
 */
public class Game {
	
	private static Game instance = new Game();
	
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private int currentLevel;
	private boolean loadingLevel;
	
	private World world;
	private HudMessage victory;
	
	private PlayerController playerController;

	private GameUI ui;
			
	private Game(){
		this.currentLevel = 1;
		this.loadingLevel = false;
		this.playerController = null;
		this.world = null;
		this.victory = null;
	}
	
	
	public void setContext(int lvl, World w, HudMessage victory, PlayerController pc) throws TartiException{
		this.currentLevel = lvl;
		this.loadingLevel = false;
		this.playerController = pc;
		this.world = w;
		this.playerController.setHero(this.world.getHero());
		this.victory = victory;
	}
	
	
	/**
	 * 
	 * @return only instance of Game
	 */
	public static Game getInstance(){
		return instance;
	}

	
	/**
	 * 
	 * @return true if we won the game ; false if not
	 */
	public boolean isWon(){
		return gameState == WIN;
	}
	
	/**
	 * Tell the model that the player won
	 */
	public void win(){
		gameState = WIN;
	}
	
	
	/**
	 * Tell the model that we should move on next stage
	 */
	public void loadNextLevel(){
		if(isWon()){
			loadingLevel = true;
		}
	}
	
	/**
	 * Update the game state (calculate the new positions of the characters for example)
	 * @param delta milliseconds since last frame
	 */
	public void update(int delta) throws SlickException, TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		world.update(delta, loadingLevel ? ++currentLevel : 0);
		if(loadingLevel){
			gameState = Game.IN_GAME;
		}
		loadingLevel = false;
	}
	
	/**
	 * Display the graphic elements of the game
	 * @param g graphic object to write in
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		world.render(g);
		if(isWon()){
			victory.render(g);
		}
	}
	
	public PlayerController getPlayerController(){
		return playerController;
	}
	
	public void setControllerHero(Hero h) throws TartiException{
		playerController.setHero(h);
	}
	
	public void setUI(GameUI ui){
		this.ui = ui;
	}
	
}
