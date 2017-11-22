

import org.newdawn.slick.AppGameContainer;

import model.GameUI;

public class Main {
	
	public static void main(String[] args){
		try {
			new AppGameContainer(new GameUI("title"), 1120, 800, false).start();
		} catch (Throwable t){
			System.err.println(t.getMessage());
			t.printStackTrace();
		}
	}
	

}
