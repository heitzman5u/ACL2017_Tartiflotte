package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;

/**
 * Represents an exit on the map, ie: space where the hero will move to the next stage
 * @author Tartiflotte
 */
public class Exit {
	protected World world;
	
	private Point topLeft;
	private Point bottomRight;
		
	public Exit(Point tl, Point br) throws SlickException, TartiException{
		if(tl == null || br == null) throw new NullArgumentException();
		if(tl.getX() > br.getX() || tl.getY() > br.getY()){
			throw new InvalidArgumentException("top-left isn't right positionned beside bottom-right");
		}
		topLeft = tl;
		bottomRight = br;
}

	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		float xArrow = (topLeft.getX() + bottomRight.getX())/2;
		float yArrow = topLeft.getY() - 90;
		
		Animation animation = GraphicsFactory.getExitAnimation();
		g.drawAnimation(animation, xArrow, yArrow);
	}
	
	/**
	 * 
	 * @see Game.update()
	 */
	public void update(int delta) throws TartiException{
		if(delta < 0) throw new InvalidArgumentException("delta >= 0");
		if(world.heroOnExitCase(this)){
			Game.getInstance().win();
		}

	}

	public Point getTopLeft() {
		return topLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}
	
	public void setWorld(World w){
		world = w;
	}
		
}
