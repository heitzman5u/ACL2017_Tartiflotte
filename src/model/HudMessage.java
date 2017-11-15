package model;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import exception.NullArgumentException;

/**
 * A message to be displayed to the hud
 * @author Tartiflotte
 *
 */
public class HudMessage {
	
	private Image img;

	/**
	 * 
	 * @param path path to the image file
	 * @param g game associated to this message
	 * @throws SlickException 
	 */
	public HudMessage(String path, Game g) throws SlickException {
		if(path == null || g == null) throw new NullArgumentException();
		img = new Image(getClass().getResourceAsStream(path), "victory_achieved", false, 0);
	}
	
	public void render(Graphics g){
		g.drawImage(img, 0, 0);
	}

}
