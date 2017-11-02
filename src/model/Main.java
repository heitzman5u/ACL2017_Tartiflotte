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
			new AppGameContainer(new GameUI("title"), 1120, 800, false).start();
		} catch (SlickException e) {
			System.err.println("SlickException!");
			e.printStackTrace();
		}catch(Throwable t){
			System.err.println("Exception");
			t.printStackTrace();
		}
	}
	

}
