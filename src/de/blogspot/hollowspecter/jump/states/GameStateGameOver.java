package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.other.Score;

public class GameStateGameOver extends BasicGameState {
	
	private Image background;	
	private Music music;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		background = new Image("res/img/menu/gameover.png");
		music = new Music("res/sfx/music/gameover.wav");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{		
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			state.enterState(GameStates.PlayingState);
			music.stop();
		}
		
		if (!music.playing())
			music.play();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{	
		background.draw(0,0);
		g.setColor(Color.black);
		g.drawString(""+Score.score, 480, 285);
		g.setColor(Color.white);
	}

	public int getID() {
		return GameStates.GameOver;
	}
}
