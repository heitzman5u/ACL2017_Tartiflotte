package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

import exception.InvalidArgumentException;
import exception.NullArgumentException;
import exception.TartiException;

/**
 * Represents an exit on the map, ie: space where the hero will move to the next stage
 * @author Tartiflotte
 */
public class Exit {
	protected World world;
	
	private Point topLeft;
	private Point bottomRight;
	
	private Animation animation;
	
	public Exit(Point tl, Point br) throws SlickException, TartiException{
		if(tl == null || br == null) throw new NullArgumentException();
		if(tl.getX() > br.getX() || tl.getY() > br.getY()){
			throw new InvalidArgumentException("top-left isn't right positionned beside bottom-right");
		}
		topLeft = tl;
		bottomRight = br;
		
		setAnimation();
	}

	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g) throws TartiException{
		if(g == null) throw new NullArgumentException();
		float xArrow = (topLeft.getX() + bottomRight.getX())/2;
		float yArrow = topLeft.getY() - 90;
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
	
	/**
	 * create the animation of the arrow to indicate the exit zone
	 * @throws SlickException 
	 */
	private void setAnimation() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("exit_arrow", getClass().getResourceAsStream("/maps/images/exit_case_arrow.png"), 20, 25);
		animation = new Animation();
		for(int i = 0 ; i < 4 ; i++){
			animation.addFrame(spriteSheet.getSprite(i, 0), 150);
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
