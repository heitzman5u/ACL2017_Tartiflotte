package model;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import exception.NullArgumentException;

/**
 * Represents the model of the game
 * Singleton: there won't be two games running in the same time
 * @author Tartiflotte
 */
public class Game {
	
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private World world;
	private HudMessage victory;
		
	
	public Game() throws SlickException{
		world = new World();
		victory = new HudMessage("/hud/victory_achieved.png");
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
	 * Update the game state (calculate the new positions of the characters for example)
	 * @param delta milliseconds since last frame
	 */
	public void update(int delta){
		if(delta < 0) throw new IllegalArgumentException("delta >= 0");
		world.update(delta);
	}
	
	/**
	 * Display the graphic elements of the game
	 * @param g graphic object to write in
	 */
	public void render(Graphics g){
		if(g == null) throw new NullArgumentException();
		world.render(g);
		if(isWon()){
			victory.render(g);
		}
	}
	
	public PlayerController getPlayerController(){
		return world.getPlayerController();
	}
	
}
