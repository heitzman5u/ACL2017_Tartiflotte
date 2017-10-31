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
	
	
	public Hero(float x, float y){
		super(x, y, 1);
		playerController = new PlayerController();
		try {
			creationAnimations();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	public void move(){
		if (playerController.isMoving()){
			pos.add(playerController.getMovement().scale(speed));
		}
		
	}
	
	public void render(Graphics g){
		//System.out.println("[ " + pos.getX() + ", " + pos.getY() + " ]\n");
		g.fillOval(pos.x, pos.y, 32, 32);
		g.drawAnimation(animations[5], pos.x, pos.y);
	}
	
	public void update(int delta){
		move();
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
