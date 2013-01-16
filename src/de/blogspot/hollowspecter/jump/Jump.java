package de.blogspot.hollowspecter.jump;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.states.GameStateMenu;
import de.blogspot.hollowspecter.jump.states.GameStatePlaying;

public class Jump extends StateBasedGame {

	public Jump() {
		super("Jump V0.0");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new GameStateMenu());
		addState(new GameStatePlaying());
//		addState(new GameOver());
//		addState(new GameStatePaused());	
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Jump(), 640, 480, false);
			app.setVSync(true); //stops tearing, FPS at ~60
			app.setAlwaysRender(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
