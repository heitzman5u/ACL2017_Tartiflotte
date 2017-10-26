package model;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {
	
	private TiledMap map;
	
	public Level(String file){
		loadMap(file);
	}

	private void loadMap(String file){
		try {
			map = new TiledMap(file);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
}
