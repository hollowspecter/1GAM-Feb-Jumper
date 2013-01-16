package de.blogspot.hollowspecter.jump.maps;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import de.blogspot.hollowspecter.jump.objects.AABB;

public class Testmap {
	
	private TiledMap map;	
	private ArrayList<AABB> coll_boxes;

	public void init(GameContainer container) throws SlickException
	{
		map = new TiledMap("res/maps/testmap.tmx");		
		coll_boxes = new ArrayList<AABB>();
		
		/*
		 * For every collision tile, make a AABB box of it!
		 * Then add to the array list coll_boxes!
		 */
		
		// 2 for-loops that look at every single tile!
//		for (int xAxis=0; xAxis<map.getWidth(); xAxis++)
//		{
//			for (int yAxis=0; yAxis<map.getHeight(); yAxis++)
//			{
//				int tileID = map.getTileId(xAxis, yAxis, 2);
//				
//				if (tileID == 3) {
//					AABB box = new AABB(xAxis*32, xAxis*32+32, yAxis*32, yAxis*32+32);
//					getList().add(box);
//				}
//			}
//		}

	}

	public void render(GameContainer container, Graphics g) throws SlickException
	{
		map.render(0, 0, 0);
    	map.render(0, 0, 1);
    	
//    	for (AABB box : getList()) {
//    		box.render(container, g);
//    	}
    	//collision data
    	//map.render(0, 0, 2);
	}

	public ArrayList<AABB> getList() {
		return coll_boxes;
	}

	public void setList(ArrayList<AABB> coll_boxes) {
		this.coll_boxes = coll_boxes;
	}
}
