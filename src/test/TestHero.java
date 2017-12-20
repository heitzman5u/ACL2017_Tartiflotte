package test;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import controller.PlayerCommand;
import exception.TartiException;

import static org.junit.Assert.*;

import model.Hero;
import model.World;


public class TestHero {
	
	@BeforeClass
	public static void setUpTest(){
		SafeMethod.testMethods();
	}

	@Test
	public void testUpdateRight() throws TartiException{
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		h.setMovement(new Vector2f(1f, 0f));
		
		World w = EasyMock.createMock(World.class);
		h.setWorld(w);
		EasyMock.expect(w.collideToWall(EasyMock.anyObject(Hero.class))).andReturn(false);
		
		EasyMock.replay(w);
		h.update(100);
		assertEquals(1010f, h.getX(), 0.01);
		EasyMock.verify(w);
	}
	
	
	@Test
	public void testCollisionWall() throws TartiException{
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		h.setMovement(new Vector2f(1f, 0f));
		
		World w = EasyMock.createMock(World.class);
		h.setWorld(w);
		EasyMock.expect(w.collideToWall(EasyMock.anyObject(Hero.class))).andReturn(true);
		
		EasyMock.replay(w);
		h.update(100);
		assertEquals(10f, h.getX(), 0.01);
		EasyMock.verify(w);
	}
	
	
	@Test
	public void testReceiveCommandMovement(){
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		
		h.receiveCommand(PlayerCommand.RIGHT);
		assertEquals(1f, h.getMovement().x, 0.01f);
		assertEquals(0f, h.getMovement().y, 0.01f);
	}
	
	
	@Test
	public void testReceiveCommandUseFlaskLifeRight(){
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		h.receiveDamage(2);
		h.pickFlask();
		final int lifeStart = h.getLife();
		
		h.receiveCommand(PlayerCommand.USE_FLASK);
		final int lifeEnd = h.getLife();

		assertTrue(lifeStart < lifeEnd);
	}
	
	
	@Test
	public void testReceiveCommandUseFlaskNbRight(){
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		h.receiveDamage(2);
		h.pickFlask();
		h.pickFlask();
		
		h.receiveCommand(PlayerCommand.USE_FLASK);

		assertEquals(1, h.getNbFlasks());
	}
	
	
	@Test
	public void testReceiveCommandUseFlaskNb0(){
		Hero h = new Hero(10f, 10f);
		h.setSpeed(10f);
		h.receiveDamage(2);
		final int lifeStart = h.getLife();
		
		h.receiveCommand(PlayerCommand.USE_FLASK);
		final int lifeEnd = h.getLife();

		assertEquals(lifeStart, lifeEnd);;
	}
	
}
