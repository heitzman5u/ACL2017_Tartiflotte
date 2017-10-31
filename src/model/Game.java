package model;

import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
	
	public boolean isWon(){
		return gameState == WIN;
		
	}
	
	public void update(int delta){
		world.update(delta);
	}
	
	public void render(Graphics g){
		world.render(g);
	}
	
	public PlayerController getPlayerController(){
		return world.getPlayerController();
	}
	
}
