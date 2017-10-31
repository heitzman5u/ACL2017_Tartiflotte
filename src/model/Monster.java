package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Monster extends Character {
	
	private Animation[] animations;
	
	public Monster(float x, float y){
		super(x, y, 0.2f);
		
		animations = new Animation[8];
		try {
			creationAnimations();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void move(int delta){
		//TODO
	}
	
	public void update(int delta){
		world.distanceWithHero(this);
		move(delta);
	}
	
	public void render(Graphics g){
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		g.drawAnimation(animations[4], pos.x-40, pos.y-65);
	}
	
	private void creationAnimations() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("lycan", getClass().getResourceAsStream("/monsters/images/lycan.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 100);
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 4 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 100);
			}
			animations[j+nbDirections] = animation;
		}
		//--
	}
}
