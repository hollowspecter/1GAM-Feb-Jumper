package de.blogspot.hollowspecter.jump.objects;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import de.blogspot.hollowspecter.jump.other.Constants;
import de.blogspot.hollowspecter.jump.other.paths;

@SuppressWarnings("unused")
public class Player extends GameObj{
	
	private Animation player;
	private Shape coll_box;
	private int state;
	private Shape feet_box;
	
	//Sounds
	private Sound jump_sound;
	
	//jetpaack
	protected Jetpack jetpack;
	
	//jump cache
	private int jump_cache = 0;

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
				
		jump_sound = new Sound("res/sfx/jump.wav");
				
		//apply gravity please
		apply_gravity = true;
		
		//Collision
		
		//box
		coll_box = new Rectangle(posX, posY, paths.IMG_PLAYER_WIDTH, paths.IMG_PLAYER_HEIGHT);
		feet_box = new Rectangle(posX, posY+paths.IMG_PLAYER_HEIGHT-7, paths.IMG_PLAYER_WIDTH, 5);
		
		//jetpack
		jetpack = new Jetpack(posX-10, posY);
		jetpack.init(container);
	}
	

	public void update (GameContainer container, int delta) throws SlickException
	{
		jumpControl(container, delta);	
		super.update(container, delta);
		updateColl();
		
		//jetpack!
		jetpack.updateLocation(posX-10, posY);
		jetpack.update(container, delta);
		
		normalizeSpdY();
	}
	
	public void render (GameContainer container, Graphics g) throws SlickException
	{
		g.drawAnimation(player, posX, posY);
		
		//jetpack!!
		jetpack.render(container, g);
		
		/*** render collisions ***
		g.setColor(Color.blue);
		g.draw(feet_box);
		g.setColor(Color.magenta);
		g.draw(coll_box);
		g.setColor(Color.white);
		***/
	}
	
	public void jumpControl(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		if (state == Constants.PLAYER_ground)
		{
			if (input.isKeyPressed(Input.KEY_SPACE))
				{
					startJump();
					jump_sound.play();
					jump_cache = 0;
				}	
		}

		input.clearKeyPressedRecord();
		
		//jetpack function
		if (jetpack.getTank().checkFuel())
		{
			if (input.isKeyDown(Input.KEY_SPACE)) {
				if (jump_cache > 35)
				{
					jetpack.setEnable_flames(true);
					incrSpdY(15);
				}
				else
					jump_cache += (int) (posX - prevPosX);
			}
			else
				jetpack.disable_flames();
		} else
			jetpack.disable_flames();
	}
	
	public void death()
	{
		resetLocation();
	}
	
	public void normalizeSpdY()
	{
		if (spdY < -Constants.PLAYER_MAX_SPDY)
			spdY = -Constants.PLAYER_MAX_SPDY;
		if (spdY > Constants.PLAYER_MAX_SPDY)
			spdY = Constants.PLAYER_MAX_SPDY;
	}
	
	public boolean checkCollisionWith(Shape other)
	{
	
		if (this.coll_box == other)
				return false;
			
		return (coll_box.contains(other) ||
				coll_box.intersects(other) ||
				other.contains(coll_box));
	}
	
	public boolean checkCollisionWith(Shape player, Shape other)
	{
	
		if (player == other)
				return false;
			
		return (player.contains(other) ||
				player.intersects(other) ||
				other.contains(player));
	}
	
	public void updateColl() {
		coll_box.setX(posX);
		coll_box.setY(posY);

		feet_box.setX(posX);
		feet_box.setY(posY+paths.IMG_PLAYER_HEIGHT-7);
	}
	

	
	//jump methods
	public void startJump() {
		spdY -= Constants.PLAYER_JUMP_SPD;
	}

	public void incrSpdY(float spd) {
		spdY -= spd;
		if (spdY > Constants.PLAYER_JUMP_SPD)
			spdY = Constants.PLAYER_JUMP_SPD;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Shape getFeet_box() {
		return feet_box;
	}

	public void setFeet_box(Shape feet_box) {
		this.feet_box = feet_box;
	}

	public Jetpack getJetpack() {
		return jetpack;
	}

	public void setJetpack(Jetpack jetpack) {
		this.jetpack = jetpack;
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
