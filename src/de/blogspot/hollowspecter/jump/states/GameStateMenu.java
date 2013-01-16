package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameStateMenu extends BasicGameState {
	
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{

	}
	
	@Override
	public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{		
		Input input = container.getInput();
		
		if (input.isKeyPressed(Input.KEY_ENTER))
			state.enterState(GameStates.PlayingState);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{	
		g.drawString("Menü. Press Enter to start.", 50, 50);
	}

	public int getID() {
		return GameStates.MenuState;
	}
}
