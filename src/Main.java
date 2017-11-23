

import org.newdawn.slick.AppGameContainer;

import model.GameUI;

public class Main {
	
	public static void main(String[] args){
		int nbTry = 0;
		boolean succeed = false;
		
		while(nbTry < 3 && !succeed){
			try {
				new AppGameContainer(new GameUI("title"), 1120, 800, false).start();
				nbTry++;
				succeed = true;
			} catch (Throwable t){
				System.err.println(t.getMessage());
				t.printStackTrace();
				succeed = false;
			}
		}
		
		
	}
	

}
