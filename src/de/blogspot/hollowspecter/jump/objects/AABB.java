package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class AABB {
	
	protected float posX;
	private float posY;
	protected float width;
	private float height;
	
	public AABB(float posX, float posY, float width, float height)
	{
		this.posX = posX;
		this.setPosY(posY);
		this.width = width;
		this.setHeight(height);
	}
	
	public AABB(float xMin, float yMin)
	{
		this(xMin, yMin, 32, 32);
	}
	
	public void update(float xMin, float yMin)
	{
		this.posX = xMin;
		this.setPosY(yMin);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		g.setColor(Color.red);
		g.drawRect(posX, getPosY(), width, getHeight());
		g.setColor(Color.white);
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
