package model;

public class Game {
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private Hero hero;
	private World world;
	
	
	public Game(){
		
	}
	
	public boolean isWon(){
		return gameState == WIN;
		
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
	
}
