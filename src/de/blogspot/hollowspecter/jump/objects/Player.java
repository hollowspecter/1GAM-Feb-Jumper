package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.blogspot.hollowspecter.jump.other.Constants;
import de.blogspot.hollowspecter.jump.other.paths;

public class Player extends GameObj{
	
	private Animation player;
	private Shape coll_box;
	private int state;
	

	public Player(float posX, float posY, String imgpath, boolean apply_gravity) {
		super(posX, posY, Constants.PLAYER_SPEED, 0, imgpath, Constants.PLAYER_MASS);
	}
	
	public Player(float posX, float posY, String imgpath) {
		this(posX, posY, imgpath, false);
	}

	public void init (GameContainer container) throws SlickException
	{
		super.init(container);
		state = Constants.PLAYER_air;
		
		SpriteSheet sheet = new SpriteSheet(img, paths.IMG_PLAYER_WIDTH, paths.IMG_PLAYER_HEIGHT,3,0);
		player = new Animation();
		player.setAutoUpdate(true);
		for (int frame=0;frame<3;frame++) {
			player.addFrame(sheet.getSprite(frame, 0), 150);
		}
				
				
		//apply gravity please
		apply_gravity = true;
		
		coll_box = new Rectangle(posX, posY, paths.IMG_PLAYER_WIDTH, paths.IMG_PLAYER_HEIGHT);
	}
	
	public void update (GameContainer container, int delta) throws SlickException
	{
		jumpControl(container, delta);		
		super.update(container, delta);
		updateColl();
	}
	
	public void render (GameContainer container, Graphics g) throws SlickException
	{
		g.drawAnimation(player, posX, posY);
//		g.draw(coll_box);
	}
	
	public boolean checkCollisionWith(Shape other)
	{
	
		if (this.coll_box == other)
				return false;
			
		return (coll_box.contains(other) ||
				coll_box.intersects(other) ||
				other.contains(coll_box));
	}
	
	public void updateColl() {
		coll_box.setX(posX);
		coll_box.setY(posY);
	}
	
	public void jumpControl(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if (state == Constants.PLAYER_ground) {
			if (input.isKeyPressed(Input.KEY_SPACE))
			{
				spdY -= Constants.PLAYER_JUMP_SPD;
			}	
		}
		
		input.clearKeyPressedRecord();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	// andere Methoden
	
//	public boolean intersectsWith(AABB box)
//	{
//		return Collision.intersect(coll_box, box, spdX, spdY, 1.0f, 0.0f);
//	}
//	
//	//getter und setter
//	public AABB getBox() {
//		return coll_box;
//	}
	
}
