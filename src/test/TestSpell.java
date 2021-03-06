package test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import exception.InvalidArgumentException;
import exception.TartiException;
import model.HeroSpell;
import model.Monster;
import model.Spell;
import model.Wolf;
import model.World;


public class TestSpell {
	
	@BeforeClass
	public static void setUpTest(){
		SafeMethod.testMethods();
	}

	@Test
	public void testUpdateRight() throws TartiException{
		Spell s = new HeroSpell(10f, 10f, new Vector2f(1f, 0f));
		s.setSpeed(10f);
		s.setRange(1f); //1 sec
		
		
		World w = EasyMock.createMock(World.class);
		s.setWorld(w);
		EasyMock.expect(w.collideToMonster(s)).andReturn(null);
		EasyMock.expect(w.collideToWall(s)).andReturn(false);
		
		EasyMock.replay(w);
		s.update(100); //100 milliseconds
		assertEquals(1010f, s.getX(), 0.01);
		EasyMock.verify(w);
	}


	@Test(expected=InvalidArgumentException.class)
	public void testInvalidTimeLap() throws TartiException{
		Spell s = new HeroSpell(10f, 10f, new Vector2f(1f, 0));
		
		s.update(-10);
		fail("Invalid delta time but update still executed");
	}
	
	
	@Test
	public void testCollisionWall() throws TartiException{
		Spell s = new HeroSpell(10f, 10f, new Vector2f(1f, 0f));
		s.setSpeed(10f);
		s.setRange(1f); //1 sec
		
		
		World w = EasyMock.createMock(World.class);
		s.setWorld(w);
		EasyMock.expect(w.collideToMonster(s)).andReturn(null);
		EasyMock.expect(w.collideToWall(s)).andReturn(true);
		w.destroyObject(s);
		
		EasyMock.replay(w);
		s.update(100); //100 milliseconds
		EasyMock.verify(w);
	}
	
	@Test
	public void testCollisionMonster() throws TartiException{
		Spell s = new HeroSpell(10f, 10f, new Vector2f(1f, 0f));
		s.setSpeed(10f);
		s.setRange(1f); //1 sec
		
		Monster m = new Wolf(10f, 10f);
		m.setLife(0);
		
		World w = EasyMock.createMock(World.class);
		s.setWorld(w);
		EasyMock.expect(w.collideToMonster(s)).andReturn(m);
		w.destroyObject(s);
		w.destroyObject(m);
		
		EasyMock.replay(w);
		s.update(100); //100 milliseconds
		EasyMock.verify(w);
	}
	

}
