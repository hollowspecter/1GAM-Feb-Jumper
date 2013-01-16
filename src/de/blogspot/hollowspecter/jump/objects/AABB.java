package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AABB {
	
	protected float xMin, xMax, yMin, yMax;
	
	public AABB(float xMin, float xMax, float yMin, float yMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	public void update(float xMin, float xMax, float yMin, float yMax)
	{
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		g.setColor(Color.green);
		g.drawRect(xMin, yMin, xMax, yMax);
		g.setColor(Color.white);
	}

}
