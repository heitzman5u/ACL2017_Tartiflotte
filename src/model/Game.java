package model;

import java.io.IOException;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game implements AutoCloseable {
	public static final int WIN = 1;
	public static final int IN_GAME = 0;
	public static final int LOOSE = -1;
	
	private int gameState;
	
	private Hero hero;
	private World world;
	
	private static Scanner sc = new Scanner(System.in);
	
	
	public Game() throws SlickException{
		world = new World(30,30);
		hero = world.getHero();
	}
	
	public boolean isWon(){
		return gameState == WIN;
		
	}
	
	public void update(int delta){
		/*String str = sc.nextLine();
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
		}*/
		world.update(delta);
	}
	
	public void render(Graphics g){
		world.render(g);
	}

	@Override
	public void close() throws IOException {
		sc.close();
	}
	
}
