package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

public class Boss extends Monster{

	public Boss(float x, float y, float speed, float attackDist, float viewDist, int fullLife, int attackP,
			long attackInter) {
		super(x, y, speed, attackDist, viewDist, fullLife, attackP, attackInter);
	}

	public void render(Graphics g) throws TartiException {
		if(g == null) throw new NullArgumentException();
		
		Animation[] animations = GraphicsFactory.getBossAnimation();
		
		//Boss Animation
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		//put the deplacement fonctions
	}

	@Override
	public void update(int delta) throws TartiException {
		// TODO Auto-generated method stub
		
	}
	
}
