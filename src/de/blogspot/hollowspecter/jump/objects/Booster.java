package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Booster extends GameObj{
	
	private boolean collected;
	private Shape shape;
	private Image image;
	
	public Booster(float posX, float posY) {
		super(posX, posY, 0, 0, "res/img/fuel.png", 10);
		collected = false;
	}

	public void init(GameContainer container) throws SlickException{
		image = new Image("res/img/fuel.png");
		setShape(new Rectangle(posX+5, posY+5, 15, 15));
	}
	
//	public void update(GameContainer container, int delta) throws SlickException
//	{
//		
//	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		if (!collected) {
			image.draw(posX, posY);
		}
	}
	
	//getter und setter

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}
	
	public void collect() {
		collected = true;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

}
