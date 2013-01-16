package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.maps.Testmap;
import de.blogspot.hollowspecter.jump.objects.AABB;
import de.blogspot.hollowspecter.jump.objects.Player;
import de.blogspot.hollowspecter.jump.other.paths;

public class GameStatePlaying extends BasicGameState {
	
	public Player player1;
	public Testmap testmap;
	
	public AABB testbox;
	
	public GameStatePlaying() {		
	
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state) throws SlickException
	{	
		player1 = new Player(50, 200, paths.IMG_PLAYER_ANIMATION);		
		player1.init(container);
		testmap = new Testmap();
		testmap.init(container);
		
		testbox = new AABB(300,400,300,350);
	}

	@Override
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
    	collision(container, delta);
    	player1.update(container, delta);
	}
	
    @Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{    	
    	g.translate(-player1.getPosX()+100, 0);
    	testmap.render(container, g);
    	player1.render(container, g);
    	testbox.render(container, g);
    	
    	//Debug HUD
    	HUD(container, g);
	}
	

	@Override
	public int getID() {
		return GameStates.PlayingState;
	}
	
	// Debug HUD
	public void HUD(GameContainer container, Graphics g) throws SlickException
	{
    	g.translate(player1.getPosX(), 0);
		g.setColor(Color.black);
		g.drawString("posX: "+player1.getPosX(), -90, 0);
		g.drawString("posY: "+player1.getPosY(), -90, 15);
		g.drawString("spdX: "+player1.getSpdX(), -90, 30);
		g.drawString("spdY: "+player1.getSpdY(), -90, 45);
		g.setColor(Color.white);
	}
	
	//Collision UPDATE mit Player1 und den Boxes aus der collision map!
	public void collision(GameContainer container, int delta) throws SlickException
	{
		//test box collision
		if (player1.intersectsWith(testbox))
			player1.setSpdY(0);
		
    	for (AABB box : testmap.getList()) {
    		if (player1.intersectsWith(box))
    			player1.setSpdY(0);
    	}
	}
}
