package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.Spell;


public class TestSpell {
	

	@Test
	public void testUpdateRight() throws SlickException{
		Spell s = new Spell(10f, 10f, new Vector2f(1f, 0f));
	}

}
