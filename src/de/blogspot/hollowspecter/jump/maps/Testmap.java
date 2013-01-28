package de.blogspot.hollowspecter.jump.maps;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Testmap {
	
	private TiledMap map;	
	private ArrayList<Shape> boxes;

	public void init(GameContainer container) throws SlickException
	{
		map = new TiledMap("res/maps/testmap.tmx");		
		boxes = new ArrayList<Shape>();
		
		/*
		 * For every collision tile, make a box of it!
		 * Then add to the array list coll_boxes!
		 */
		
		// 2 for-loops that look at every single tile! 
		for (int xAxis=0; xAxis<map.getWidth(); xAxis++)
		{
			for (int yAxis=0; yAxis<map.getHeight(); yAxis++)
			{
				int tileID = map.getTileId(xAxis, yAxis, 2);
				if (tileID == 3)
				{
					Shape box = new Rectangle(xAxis*32, yAxis*32, 32, 32);
					boxes.add(box);
				}
			}
		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException
	{
		map.render(0, 0, 0);
    	map.render(0, 0, 1);
    	
    	for (Shape box : getList()) {
    		g.setColor(Color.red);
    		g.draw(box);
    		g.setColor(Color.white);
    	}
	}

	public ArrayList<Shape> getList() {
		return boxes;
	}
}
