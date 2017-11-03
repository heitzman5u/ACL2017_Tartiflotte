package model;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

public class Monster extends Character {
	
	private Animation[] animations;
	
	private boolean moving;
	private int direction;
	
	private boolean attack;
	
	private static final float SPEED = 2.0f;
	
	public Monster(float x, float y){
		super(x, y, SPEED);
		
		moving = false;
		direction = 0;
		
		attack = false;
		
		animations = new Animation[8];
		try {
			creationAnimations();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void move(int delta){
		float xHero = world.distanceWithHero(this).getX();
		float yHero = world.distanceWithHero(this).getY();
		
		if(((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= 80000) && ((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) >= 600)){
			moving = true;
			pos.add(direction(xHero, yHero).scale(speed));	
		} else {
			moving = false;
		}
	}
	
	public void attack(){
		float xHero = world.distanceWithHero(this).getX();
		float yHero = world.distanceWithHero(this).getY();

		if (((Math.pow(Math.abs(xHero), 2.0) + Math.pow(Math.abs(yHero), 2.0)) <= 600)){
			attack = true;
			world.getHero().setAlive(false);
		}

	}
	
	private Vector2f direction(float xHero, float yHero){
		int signX, signY;
		if (xHero <= 0){
			signX = -1; 
			if (Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0){
				direction = 1;
			}
		} else {
			signX = 1;
			if (Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0){
				direction = 2;
			}
		}
		
		if (yHero <= 0){
			signY = -1;
			if (!(Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0)){
				direction = 3;
			}
		} else {
			signY = 1;
			if (!(Math.abs(yHero) <= 3 && Math.abs(yHero) >= 0)){
				direction = 0;
			}
		}
		 return new Vector2f(signX, signY);
	}
	
	public void update(int delta){
			move(delta);
			attack();
	}
	
	public void render(Graphics g){
		// MONSTER ANIMATION
		g.setColor(new Color(48,48,48));
		g.fillOval(pos.x-20, pos.y, 40, 16);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], pos.x-40, pos.y-65);
		
		// ATTACK ANIMATION
		if (attack == true){
			try {
				SpriteSheet spriteSheet;
				spriteSheet = new SpriteSheet("griffure", getClass().getResourceAsStream("/monsters/images/scratch.png"), 60, 60);
				Animation attackAnim = new Animation();
				attackAnim.addFrame(spriteSheet.getSprite(0, 0), 100);
				g.drawAnimation(attackAnim, world.getHero().getX()-30, world.getHero().getY()-30);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		// --
	}
	
	private void creationAnimations() throws SlickException{
		SpriteSheet spriteSheet = new SpriteSheet("lycan", getClass().getResourceAsStream("/monsters/images/lycan.png"), 80, 80);
				
		// STOP POSITIONS
		int nbDirections = 4;
		for (int i = 0 ; i < nbDirections ; i++){
			Animation animation = new Animation();
			animation.addFrame(spriteSheet.getSprite(0, i), 200);
			animations[i] = animation;
		}
		
		// MOVING POSITIONS
		for (int j = 0 ; j < 4 ; j++){
			Animation animation = new Animation();
			for (int i = 0 ; i < 4 ; i++){
				animation.addFrame(spriteSheet.getSprite(i, j), 200);
			}
			animations[j+nbDirections] = animation;
		}
		//--
	}
}
