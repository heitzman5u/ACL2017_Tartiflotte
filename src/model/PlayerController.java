package model;

import java.util.HashMap;
import java.util.Map;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import controller.PlayerCommand;
import exception.NullArgumentException;
import exception.TartiException;

/**
 * Tell what button is pressed or released and in wich direction
 * @author Tartiflotte
 *
 */

/*
 * Helper class that converts input into data usable by the hero
 */
public class PlayerController implements KeyListener, MouseListener {
	
	//private Stack<InputProperty> keyPressed;
	private Map<Integer, InputProperty> inputs = new HashMap<>();
	
	private Hero hero;
	
	/**
	 * The constructor initializes the attribute inputs.
	 * This attribute connects the four moving keys to the good displacement
	 */
	public PlayerController(){
		inputs.put(Input.KEY_Z, new InputProperty(PlayerCommand.UP));
		inputs.put(Input.KEY_S, new InputProperty(PlayerCommand.DOWN));
		inputs.put(Input.KEY_Q, new InputProperty(PlayerCommand.LEFT));
		inputs.put(Input.KEY_D, new InputProperty(PlayerCommand.RIGHT));
		
		inputs.put(Input.KEY_F, new InputProperty(PlayerCommand.USE_FLASK));
		inputs.put(Input.KEY_R, new InputProperty(PlayerCommand.USE_ATTACK_BOOST));
		inputs.put(Input.KEY_M, new InputProperty(PlayerCommand.ATTACK));
		inputs.put(Input.KEY_P, new InputProperty(PlayerCommand.NEXT_LEVEL));
		
		
		hero=null;
	}

	@Override
	public void inputEnded() {
	}
	
	@Override
	public void inputStarted() {
	}
	
	@Override
	public boolean isAcceptingInput() {
		return true;
	}
	
	@Override
	public void setInput(Input arg0) {		
	}	
	
	/**
	 * Get the current key pressed.
	 * Update the current displacement
	 * of the user of the class.
	 * Update the button lastPressed.
	 */
	@Override
	public void keyPressed(int key, char arg1) {
		if(hero == null) return;
		
		if(inputs.containsKey(key)){
			final InputProperty ip = inputs.get(key);
			hero.receiveCommand(ip.getCommand());
		}
	}
	
	/**
	 * Disable the lastPressed button
	 */
	@Override
	public void keyReleased(int key, char arg1) {
		if(hero == null) return;
		if(inputs.containsKey(key)){
			final InputProperty ip = inputs.get(key);
			if(inputs.get(key).getCommand().hasMovement()){
				hero.receiveCommand(ip.getCommand().opposite());
			}
		}
	}
	
	public void setHero(Hero h) throws TartiException{
		if(h == null) throw new NullArgumentException();
		hero = h;
	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		if(arg0==0){
			float xh=hero.getX(), yh=hero.getY();
			Vector2f dir = new Vector2f(arg1-xh,arg2-yh);
			try {
				hero.spawnSpell(xh, yh, dir);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return vector corresponding to the key pressed 
	 * and the direction associated 
	 */
	/*public Vector2f getMovement(){
		//here, prop is set and pressed
		Vector2f sum = new Vector2f(0,0);
		for(Entry<Integer, InputProperty> e : inputs.entrySet()){
			InputProperty prop= e.getValue();
			if(prop.pressed()){
				sum.add(prop.getMovement());
			}
		}
		return sum.getNormal();
	}*/

	/*public boolean isMoving() {
		return getMovement().length()!=0f;
	}*/
	
	/**
	 * @return the current direction of the last button pressed
	 */
	/*public int getDirection(){
		//return inputs.get(lastPressed).getDirection();
		double angle=getMovement().getTheta();
		double demiQuart=360/8;
		int dir=lastDirection;
		
		if(getMovement().length()==0){
			return lastDirection;
		}
		
		if((angle<demiQuart) || angle>7*demiQuart){
			dir=0;
		}
		else if(angle>=demiQuart && angle<=3*demiQuart){
			dir=3; 
		}
		else if(angle>3*demiQuart && angle<5*demiQuart){
			dir=1;
		}
		else if(angle>=5*demiQuart && angle<=7*demiQuart){
			dir=2;
		}
		else{
			return lastDirection;
		}
		lastDirection=dir;
		return dir;
	}*/
	
}
