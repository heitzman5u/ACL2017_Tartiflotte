package model;

import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * represents the model of the game 
 * @author Tartiflotte
 */
public class Game {
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private Hero hero;
	private World world;
		
	
	public Game() throws SlickException{
		world = new World(30,30);
		hero = world.getHero();
	}
	
	/**
	 * 
	 * @return true if we won the game ; false if not
	 */
	public boolean isWon(){
		return gameState == WIN;
		
	}
	
	/**
	 * 
	 * @param delta
	 * update the game state (calculate the new positions of the characters for example)
	 */
	public void update(int delta){
		world.update(delta);
	}
	
	/**
	 * display the graphic elements of the game
	 * @param g
	 */
	public void render(Graphics g){
		world.render(g);
	}
	
	public PlayerController getPlayerController(){
		return world.getPlayerController();
	}
	
}
