package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

public class Boss extends Monster{

	public Boss(float x, float y) {
		super(x, y, 3.0f, 600f, 80000f, 20, 2, 1000);
	}
	
	public void attackSpell() {
		
	}
	
	public void move(int delta) {
		
	}
	
	public void render(Graphics g) throws TartiException {
		if(g == null) throw new NullArgumentException();
		
		Animation[] animations = GraphicsFactory.getBossAnimation();
		
		//Boss Animation
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		//put the deplacement fonctions
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], pos.x-40, pos.y-65);
	}
	
	@Override
	public void update(int delta) throws TartiException {
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		move(delta);
		attack();
		if(life <= fullLife/2) {
			attackSpell();
		}
	}
	
}
