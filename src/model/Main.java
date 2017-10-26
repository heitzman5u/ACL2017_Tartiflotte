package model;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main {
	private GameUI gameUI;
	
	public static void main(String[] args){
		try {
			new AppGameContainer(new GameUI("title"), 320, 320, false).start();
		} catch (SlickException e) {
			System.err.println("SlickException!");
			e.printStackTrace();
		}catch(Throwable t){
			System.err.println("Exception");
			t.printStackTrace();
		}
		
		//main game loop
//		try(Game g = new Game()){ //try with statement to ensure correct resource close (Scanner)
//			g.render();
//			while(!g.isWon()){
//				g.update();
//				g.render();
//			}
//		}catch(IOException e){
//			System.err.println("IO error:");
//			e.printStackTrace();
//		}catch(SlickException e){
//			System.err.println("Slick exception");
//			e.printStackTrace();
//		}
//		System.out.println("You won");
	}
	

}
