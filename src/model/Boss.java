package model;

import java.awt.Font;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.NotLoadedException;
import exception.NullArgumentException;
import exception.TartiException;
import graphic.GraphicsFactory;


public class Boss extends Monster{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1480519936011295029L;
	private long spellInterval;
	
	private TrueTypeFont ttf;

	private enum state {
		ENTER, TELEPORTATION, CHARGED;

	};
	
	private Random r;

	private int heightMap = 800;
	private int widthMap = 1120;
	
	private Vector2f direction;

	private state stateNow;

	public Boss(float x, float y) {
		super(x, y, 0.4f, 40f, 80000f, 100, 2, 1000);
		stateNow = state.ENTER;
		
		spellInterval=2000;
		Font font = new Font("Time New Roman", Font.PLAIN, 20);
		ttf = new TrueTypeFont(font, true);
		r = new Random();
	}

	/**
	 * create a spell and add it to the list in world
	 * 
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public void spawnSpell(float x, float y, float xCible, float yCible) {
		if(attackTimer.elapsed()){
			Spell sp=new EnemySpell(x, y, xCible, yCible);
			sp.setWorld(world);
			world.addSpell(sp);
			attackTimer.start(spellInterval);
		}
	}

	private void displayName() {
		ttf.drawString(widthMap/2-ttf.getWidth("Galdepin")/2, 720, "Galdepin",new Color(107,13,13));
	}

	/**
	 * Display the life bar of the monster
	 * 
	 * @throws NotLoadedException
	 */
	private void lifeBarHUD() throws NotLoadedException {
		Image lifeBarImg = GraphicsFactory.getBossLifeBarImage();
		Image lifeImg = GraphicsFactory.getBossLifeImage();

		float bigger = 400;
		float lifeRatio = life / (float) fullLife;
		float width = (float) lifeImg.getWidth() + bigger;
		float height = (float) lifeImg.getHeight();
		float widthRatio = ((float) lifeImg.getWidth() + bigger) * lifeRatio;

		lifeBarImg.draw(widthMap / 2 - width / 2, 750, width, height);
		lifeImg.draw(widthMap / 2 - width / 2, 753, widthRatio - 4, height - 6);
	}

	private void bossEnter(int delta) {
		if (stateNow == state.ENTER)
			if (getY() < heightMap) {
				setY(getY() + getSpeed()*(float)delta);
				setDirection(0);
			} else {
				stateNow = state.TELEPORTATION;
			}
	}

	private void teleportation() {
		if (stateNow == state.TELEPORTATION) {
			int rand = r.nextInt(3);
			switch (rand) {
			case 0:
				direction = new Vector2f(0, 1);
				setPos(new Vector2f(world.getHero().getX(), 0));
				break;
			case 1:
				direction = new Vector2f(-1, 0);
				setPos(new Vector2f(widthMap, world.getHero().getY()));
				break;
			case 2:
				direction = new Vector2f(1, 0);
				setPos(new Vector2f(0,world.getHero().getY()));
				break;
			case 3:
				direction = new Vector2f(0, -1);
				setPos(new Vector2f(world.getHero().getX(), heightMap));
				break;
			}
		
			setDirection(rand);
			stateNow = state.CHARGED;
		}
	}

	public void charged(int delta) {
		if (stateNow == state.CHARGED) {
			pos.add( direction.getNormal().scale(speed*(float)delta) );
			if (getX() > 0 && getX() < 1120 && getY() > 0 && getY() < 800)
				;
			else
				stateNow = state.TELEPORTATION;
		}
	}

	@Override
	public void update(int delta) throws TartiException {
		if (delta < 0)
			throw new InvalidArgumentException("delta >= 0");
		attack();
		teleportation();
		charged(delta);
		bossEnter(delta);
		if(getLife() <= (int)fullLife / 2) {
			Vector2f p = world.getHero().getPos();
			spawnSpell(getX(), getY(), p.x, p.y);
		}		
	}
	
	public void receiveDamage(int dmg){
		life -= dmg;
		if(life <= 0){
			setAlive(false);
			Game.getInstance().win();
			world.destroyObject(this);
		}
	}

	
	public void render(Graphics g) throws TartiException {
		if (g == null)
			throw new NullArgumentException();

		Animation[] animations = GraphicsFactory.getBossAnimation();

		// Boss Animation
		g.setColor(new Color(48, 48, 48));
		g.fillOval(pos.x - 20, pos.y, 40, 16);
		// put the deplacement fonctions
		g.drawAnimation(animations[getDirection()], pos.x - 40, pos.y - 65);

		lifeBarHUD();
		displayName();
	}
}
