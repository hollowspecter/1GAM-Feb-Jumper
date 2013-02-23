package de.blogspot.hollowspecter.jump;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.states.GameStateGameOver;
import de.blogspot.hollowspecter.jump.states.GameStateMenu;
import de.blogspot.hollowspecter.jump.states.GameStatePlaying;
import de.blogspot.hollowspecter.jump.states.GameStateWin;

public class Jump extends StateBasedGame {

	public Jump() {
		super("Jump V0.0");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new GameStateMenu());
		addState(new GameStatePlaying());
		addState(new GameStateWin());
		addState(new GameStateGameOver());
//		addState(new GameStatePaused());	
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Jump(), 640, 480, false);
			app.setVSync(true); //stops tearing, FPS at ~60
			app.setAlwaysRender(true);
			app.setShowFPS(false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
