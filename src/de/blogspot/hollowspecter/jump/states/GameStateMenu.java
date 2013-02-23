package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateMenu extends BasicGameState {
	
	private Image background;
	private Animation pants;
	private Music music;
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = new Image("res/img/menu/start.png");
		Image pant_img = new Image("res/img/menu/char.png");
		SpriteSheet pant_sprites = new SpriteSheet(pant_img, (pant_img.getWidth()-2)/3, pant_img.getHeight(), 1, 0);
		pants = new Animation();
		pants.setAutoUpdate(true);
		for (int frame=0;frame<3;frame++) {
			pants.addFrame(pant_sprites.getSprite(frame, 0), 150);
		}
		
		//music handling
		music = new Music("res/sfx/music/menu.wav");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{		
		Input input = container.getInput();
		
		if (!music.playing())
			music.loop();
		
		if (input.isKeyPressed(Input.KEY_SPACE))
		{
			state.enterState(GameStates.PlayingState);
			music.stop();
		}
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{	
		background.draw(0,0);
		pants.draw(85, 180);
	}

	public int getID() {
		return GameStates.MenuState;
	}
}
