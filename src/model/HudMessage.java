package model;

import java.awt.Font;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;

import exception.NullArgumentException;
import exception.TartiException;

/**
 * A message to be displayed to the hud
 * @author Tartiflotte
 *
 */
public class HudMessage {
	
	private Image img;
	
	private TrueTypeFont ttf;
	/**
	 * 
	 * @param path path to the image file
	 * @param g game associated to this messageddd
	 * @throws SlickException 
	 */
	public HudMessage(String path) throws SlickException, TartiException {
		if(path == null) throw new NullArgumentException();
		img = new Image(getClass().getResourceAsStream(path), "victory_achieved", false, 0);
		
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
	}
	
	public void render(Graphics g){
		g.drawImage(img,1120/2 - img.getWidth()/2, 800/2 -img. getHeight()/2);
		ttf.drawString(500.0f, 470.0f, "Press p", Color.white);
	}

}
