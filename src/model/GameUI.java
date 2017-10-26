package model;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameUI extends BasicGame {
	
	private Game game;
	private GameContainer container;

	public GameUI(String title) throws SlickException {
		super(title);
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		container = arg0;
		game = new Game();
	}

	@Override
	public void update(GameContainer arg0, int delta) throws SlickException {
		//game.update(delta);
	}
	
	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		//game.render(g);
	}
	
	@Override
	public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            container.exit();
        }
    }

}
