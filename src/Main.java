

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import model.GameUI;

public class Main {
	
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
