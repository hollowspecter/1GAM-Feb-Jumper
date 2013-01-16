package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import de.blogspot.hollowspecter.jump.other.Constants;
import de.blogspot.hollowspecter.jump.other.paths;

public class Player extends GameObj{
	
	private Animation player;

	public Player(float posX, float posY, String imgpath, boolean apply_gravity) {
		super(posX, posY, Constants.PLAYER_SPEED, 0, imgpath, Constants.PLAYER_MASS);
	}
	
	public Player(float posX, float posY, String imgpath) {
		this(posX, posY, imgpath, false);
	}

	public void init (GameContainer container) throws SlickException
	{
		super.init(container);
		
		SpriteSheet sheet = new SpriteSheet(img, paths.IMG_PLAYER_WIDTH, paths.IMG_PLAYER_HEIGHT,3,0);
		player = new Animation();
		player.setAutoUpdate(true);
		for (int frame=0;frame<3;frame++) {
			player.addFrame(sheet.getSprite(frame, 0), 150);
		}
		
		//xmin xax ymin ymax initialisieren
		xMin = posX;
		xMax = posX + paths.IMG_PLAYER_WIDTH;
		yMin = posY;
		yMax = posY + paths.IMG_PLAYER_HEIGHT;
		
		//update box!
		box.update(xMin, xMax, yMin, yMax);
		
		//apply gravity please
		apply_gravity = true;
	}
	
	public void update (GameContainer container, int delta) throws SlickException
	{
		//Steuerung
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE))
		{
			spdY -= Constants.PLAYER_JUMP_SPD;
		}
		
		super.update(container, delta);
	}
	
	public void render (GameContainer container, Graphics g) throws SlickException
	{
		g.drawAnimation(player, posX, posY);
	}
}
