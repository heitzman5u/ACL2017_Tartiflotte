package test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.geom.Vector2f;

import exception.TartiException;
import model.LifeFlask;
import model.World;

public class TestLifeFlask {

	@BeforeClass
	public static void setUpClass() {
		SafeMethod.testMethods();
	}

	@Test
	public void testUpdateRight() throws TartiException {
		World w = EasyMock.createMock(World.class);
		LifeFlask l = new LifeFlask(10f, 10f);
		l.setWorld(w);
		
		EasyMock.expect(w.trajectoryToHero(l)).andReturn(new Vector2f(0.1f, 0.1f));
		w.pickFlask(l);
		
		EasyMock.replay(w);
		l.update(100);
		//the flask should be picked up
		EasyMock.verify(w);
	}
	
	@Test
	public void testUpdateNoPick() throws TartiException{
		World w = EasyMock.createMock(World.class);
		LifeFlask l = new LifeFlask(10f, 10f);
		l.setWorld(w);
		
		EasyMock.expect(w.trajectoryToHero(l)).andReturn(new Vector2f(200f, 200f));
		w.pickFlask(l);
		
		EasyMock.replay(w);
		l.update(100);
		//the flask should be picked up
		EasyMock.verify(w);
	}

}
