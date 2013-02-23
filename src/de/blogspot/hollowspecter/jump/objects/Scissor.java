package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Scissor extends GameObj {
	
	private boolean type;
	private Animation animation;
	private SpriteSheet spritesheet;
	private Shape collision;
	private Timer timer;
	private float overallSpd;

	public Scissor(float posX, float posY) {
		super(posX, posY, 0, 0, "scissor", 10);
		type = false;
		overallSpd = 100;
	}

	public Scissor(float posX, float posY, boolean type) {
		this(posX, posY);
		this.type = type;
	}
	
	public void init(GameContainer container) throws SlickException
	{
		Image img2 = new Image("res/img/scissor.png");
		spritesheet = new SpriteSheet(img2, 20, 20, 0, 0);
		animation = new Animation();
		animation.setAutoUpdate(true);
		for (int frame=0;frame<2;frame++) {
			animation.addFrame(spritesheet.getSprite(frame, 0), 120);
		}
		collision = new Rectangle(posX+5, posY+5, 15, 15);
		
		timer = new Timer(2000);
		
		if (!type) {
			spdX = overallSpd;
		} else
			spdY = overallSpd;
	}
	
	public void update(GameContainer container, int delta) throws SlickException {
		updateColl();
		
		timer.addTime(delta); 

		if (timer.isTimeOver()) {
			spdX *= -1; 
			spdY *= -1;
			timer.reset();
		}
		
		super.update(container, delta);
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.drawAnimation(animation, posX, posY);
	}
	
	public void updateColl() {
		collision.setX(posX+5);
		collision.setY(posY+5);
	}

	public Shape getShape() {
		return collision;
	}

}
