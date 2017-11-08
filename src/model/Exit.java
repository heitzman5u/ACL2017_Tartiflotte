package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Point;

/**
 * 
 * @author Tartiflotte
 * Represent an exit on the map
 */
public class Exit {
	protected World world;
	
	private Point topLeft;
	private Point bottomRight;
	
	private Animation animation;
	
	public Exit(Point tl, Point br){
		topLeft = tl;
		bottomRight = br;
		
		setAnimation();
	}

	/**
	 * 
	 * @see Game.render()
	 */
	public void render(Graphics g){
		float xArrow = (topLeft.getX() + bottomRight.getX())/2;
		float yArrow = topLeft.getY() - 90;
		g.drawAnimation(animation, xArrow, yArrow);
	}
	
	/**
	 * 
	 * @see Game.update()
	 */
	public void update(int delta){
		if(world.heroOnExitCase(this)){
			System.out.println("you win !!!");
		}

	}
	
	/**
	 * create the animation of the arrow to indicate the exit zone
	 */
	private void setAnimation(){
		try {
			SpriteSheet spriteSheet = new SpriteSheet("exit_arrow", getClass().getResourceAsStream("/maps/images/exit_case_arrow.png"), 20, 25);
			animation = new Animation();
			for(int i = 0 ; i < 4 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, 0), 150);
			}
		} catch (SlickException e) {
			e.printStackTrace();
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
