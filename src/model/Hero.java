package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Hero extends Character {
	private PlayerController playerController;
	
	private static final int NB_SPRITE_H = 11; 
	private static final int NB_SPRITE_W = 4;
	private Animation[] animations = new Animation[NB_SPRITE_H];
	
	private static final float SPEED = 0.2f;
	
	public Hero(float x, float y){
		super(x, y, SPEED);
		playerController = new PlayerController();
		try {
			creationAnimations();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public Hero(Hero other){
		super(other.getX(), other.getY(), SPEED);
		playerController = other.playerController;
	}
	
	public void move(int delta){
		Vector2f vspeed = playerController.getMovement().scale(speed*(float)delta);
		pos.add(vspeed);
	}
	
	public Hero futurePos(int delta){
		Hero h = new Hero(this);
		h.move(delta);
		return h;
	}
	
	public void render(Graphics g){
		g.fillOval(pos.x-16, pos.y-16, 32, 32);
		g.drawAnimation(animations[5], pos.x, pos.y);

	}
	
	public void update(int delta){
		if( playerController.isMoving() 
				&& !world.collideToWall(futurePos(delta)) ){
			move(delta);
		}
	}
	
	public PlayerController getPlayerController(){
		return playerController;
	}
	
	private void creationAnimations() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("hero", getClass().getResourceAsStream("/hero/images/hero.png"), 74, 56);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 100);
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < NB_SPRITE_W ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < NB_SPRITE_H ; i++){
				animation.addFrame(spriteSheet.getSprite(j, i), 100);
			}
			animations[j+nbDirections] = animation;
		}
		//--
	}
		
		
}
