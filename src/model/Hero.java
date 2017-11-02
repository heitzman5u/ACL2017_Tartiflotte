package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Hero extends Character {
	private PlayerController playerController;
	
	private Animation[] animations;
	
	private static final float SPEED = 0.2f;	
	
	public Hero(float x, float y){
		super(x, y, SPEED);
		playerController = new PlayerController();
		
		
		animations = new Animation[9];
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
		if (isAlive()){
			Vector2f vspeed = playerController.getMovement().scale(speed*(float)delta);
			pos.add(vspeed);
		}
	}
	
	public Hero futurePos(int delta){
		Hero h = new Hero(this);
		h.move(delta);
		return h;
	}
	
	public void render(Graphics g){
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		if (isAlive()){
			g.drawAnimation(animations[4], pos.x-40, pos.y-65);
		} else {
			g.drawAnimation(animations[8], pos.x-40, pos.y-65);
		}

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
		SpriteSheet spriteSheet = new SpriteSheet("hero", getClass().getResourceAsStream("/hero/images/hero.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			if (i == 1){
				animation.addFrame(spriteSheet.getSprite(5, i), 100);
			} else {
				animation.addFrame(spriteSheet.getSprite(0, i), 100);
			}
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 6 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 100);
			}
			animations[j+nbDirections] = animation;
		}
		
		// DEAD POSITIONS
		Animation animation = new Animation();
		animation.addFrame(spriteSheet.getSprite(0, 4), 100);
		animations[8] = animation;		
		//--
	}
		
		
}
