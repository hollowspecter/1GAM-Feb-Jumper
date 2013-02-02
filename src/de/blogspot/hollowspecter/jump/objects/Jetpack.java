package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import de.blogspot.hollowspecter.jump.other.Constants;

public class Jetpack {
	
	/*
	 * Has flames
	 * has a tank
	 * some kinda sound?
	 * some kind of update thing to update location
	 * states: flames on/off or being used on/off
	 */
	
	private float posX;
	private float posY;
	private Image img;
	private Animation flames;
	private Sound flames_sound;
	
	private Fueltank tank;
	
	//states
	private boolean enable_flames = false;
	
	public Jetpack(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void init(GameContainer container) throws SlickException
	{
		flames_sound = new Sound("res/sfx/flames.wav");
		img = new Image("res/img/jetpack.png");
		
		Image img2 = new Image("res/img/flames.png");
		SpriteSheet flames_sprite = new SpriteSheet(img2, img2.getWidth()/3-2, img2.getHeight(),1,0);
		flames = new Animation();
		flames.setAutoUpdate(true);
		for (int frame=0;frame<3;frame++) {
			flames.addFrame(flames_sprite.getSprite(frame, 0), 100);
		}
		
		//tank
		tank = new Fueltank(100,100);
	}
	
	public void update(GameContainer container, int delta) throws SlickException
	{
		float delta_ = delta/1000f;
		
		if (enable_flames && tank.checkFuel())
		{
			tank.useFuel(Constants.FUEL_FACTOR * delta_);
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		img.draw(posX, posY);
		if (enable_flames)
			burn(container, g);
			
	}
	
	public void burn(GameContainer container, Graphics g) throws SlickException
	{
		g.drawAnimation(flames, posX+2, posY+img.getHeight());
		if (!flames_sound.playing())
			flames_sound.play();
	}
	
	public void updateLocation(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public boolean isEnable_flames() {
		return enable_flames;
	}

	public void setEnable_flames(boolean enable_flames) {
		this.enable_flames = enable_flames;
	}
	
	public void disable_flames() {
	enable_flames = false;
	flames_sound.stop();
	}

	public Fueltank getTank() {
		return tank;
	}

	public void setTank(Fueltank tank) {
		this.tank = tank;
	}
}
