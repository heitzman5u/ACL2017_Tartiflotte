package model;

import org.newdawn.slick.Graphics;

import exception.NullArgumentException;

public class Boss extends Monster{

	public void render(Graphics g) throws NullArgumentException {
		if(g == null) throw new NullArgumentException();
		
	}
	
}
