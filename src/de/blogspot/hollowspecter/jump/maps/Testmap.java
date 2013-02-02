package de.blogspot.hollowspecter.jump.maps;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

@SuppressWarnings("unused")
public class Testmap {
	
	private TiledMap map;	
	private ArrayList<Shape> boxes, boxes_above;
	private float mapwidth;

	public void init(GameContainer container) throws SlickException
	{
//		map = new TiledMap("res/maps/testmap.tmx");	
		map = new TiledMap("res/maps/lvl1.tmx");		
		boxes = new ArrayList<Shape>();
		boxes_above = new ArrayList<Shape>();
		
		setMapwidth(map.getWidth()*32);
		
		/*
		 * For every collision tile, make a box of it!
		 * Then add to the array list coll_boxes!
		 */
		
		// 2 for-loops that look at every single tile! 
		
		//cache
		float xPos=0,yPos=0,width=0;
		
		//loops
		for (int xAxis=0; xAxis<map.getWidth(); xAxis++)
		{
			for (int yAxis=0; yAxis<map.getHeight(); yAxis++)
			{
				int tileID = map.getTileId(xAxis, yAxis, 2);
				
				if (tileID == 4) {
					xPos = xAxis*32;
					yPos = yAxis*32;
					width = 1;
				} else if (tileID == 3) {
					width++;
				} else if (tileID == 5) {
					width++;
					Shape box = new Rectangle(xPos, yPos, width*32, 32);
					boxes.add(box);
					
					//box aboce
					Shape box2 = new Rectangle(xPos+3, yPos-32, width*32, 32);
					boxes_above.add(box2);
				}
			}
		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException
	{
		map.render(0, 0, 0);
    	map.render(0, 0, 1);
    	
    	/*** render collision boxes ***
    	
    	for (Shape box : getList()) {
    		g.setColor(Color.red);
    		g.draw(box);
    		g.setColor(Color.white);
    	}
    	
    	for (Shape box : boxes_above) {
    		g.setColor(Color.green);
    		g.draw(box);
    		g.setColor(Color.white);
    	}
    	
    	***/
	}

	public ArrayList<Shape> getList() {
		return boxes;
	}
	
	public ArrayList<Shape> getBoxAbove() {
		return boxes_above;
	}

	public float getMapwidth() {
		return mapwidth;
	}

	public void setMapwidth(float mapwidth) {
		this.mapwidth = mapwidth;
	}
}
