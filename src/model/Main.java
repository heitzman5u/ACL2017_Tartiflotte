package model;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{

	public Main(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args){
		/*try {
			new AppGameContainer(new Main("title"), 640, 480, false).start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//main game loop
		Game g = new Game();
		while(!g.isWon()){
			g.update();
			g.render();
		}
		System.out.println("You won");
	}


}
