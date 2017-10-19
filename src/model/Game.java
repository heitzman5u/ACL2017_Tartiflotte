package model;

import java.util.Scanner;

public class Game {
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private Hero hero;
	private World world;
	
	
	public Game(){
		world = new World(30,30);
		hero = world.getHero();
	}
	
	public boolean isWon(){
		return gameState == WIN;
		
	}
	
	public void update(){
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		if(str.length() > 0){
			char carac = str.charAt(0);
			if (carac == 'z' || carac == 'Z'){
				hero.move(0, -1);
			}
			if (carac == 'q' || carac == 'Q'){
				hero.move(-1, 0);
			}
			if (carac == 's' || carac == 'S'){
				hero.move(0, 1);
			}
			if (carac == 'd' || carac == 'D'){
				hero.move(1, 0);
			}
		}
		
	}
	
	public void render(){
		world.render();
	}
	
}
