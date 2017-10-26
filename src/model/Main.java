package model;

import java.io.IOException;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main {
	private GameUI gameUI;
	
	public static void main(String[] args){
		/*try {
			new AppGameContainer(new Main("title"), 640, 480, false).start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//main game loop
		try(Game g = new Game()){ //try with statement to ensure correct resource close (Scanner)
			g.render();
			while(!g.isWon()){
				g.update();
				g.render();
			}
		}catch(IOException e){
			System.err.println("IO error:");
			e.printStackTrace();
		}
		System.out.println("You won");
	}

}
