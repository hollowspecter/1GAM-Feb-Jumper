package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.maps.Testmap;
import de.blogspot.hollowspecter.jump.objects.Player;
import de.blogspot.hollowspecter.jump.other.Constants;
import de.blogspot.hollowspecter.jump.other.paths;

public class GameStatePlaying extends BasicGameState {
	
	protected Player player1;
	protected Testmap testmap;
	
	//HUD
	private String hud_string;
	
		
	public GameStatePlaying() {		
	
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame state) throws SlickException
	{	
		
		player1 = new Player(50, 200, paths.IMG_PLAYER_ANIMATION);		
		player1.init(container);
		testmap = new Testmap();
		testmap.init(container);
	}

	@Override
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
    	player1.update(container, delta);
    	collision(container, delta);
    	if (player1.getPosY() > 500)
    		reset();
	}
	
    @Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{    	
    	if ((player1.getPosX()+540) < testmap.getMapwidth())
    		g.translate(-player1.getPosX()+100, 0);
    	else
    		g.translate(-1*(testmap.getMapwidth()-640),0);
    	testmap.render(container, g);
    	player1.render(container, g);
    	
    	//Debug HUD
    	HUD(container, g);
    	fuelBar(container, g);
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
		g.drawString(hud_string, -90, 60);
		g.setColor(Color.white);
	}
	
	public void collision(GameContainer container, int delta) throws SlickException
	{
		hud_string = "Collision: FALSE";
		player1.setState(Constants.PLAYER_air);
		boolean dmg_coll = false;
		
    	for (Shape box : testmap.getList()) {
    		if (player1.checkCollisionWith(box))
    		{
    			hud_string = "Collision: TRUE";
    			dmg_coll = true;

    			//only if feet hit the ground!
    			for (Shape box2 : testmap.getBoxAbove()) {
    				if (player1.checkCollisionWith(player1.getFeet_box(), box2)) {
    					player1.setState(Constants.PLAYER_ground);
    	    			player1.setPosY(box.getY()-32);
    	    			player1.setSpdY(0);
    	    			dmg_coll = false;
    				}
    			}
    		}
    	}
    	
    	if (dmg_coll)
    		reset();
	}
	
	public void fuelBar(GameContainer container, Graphics g) throws SlickException
	{
		g.setColor(Color.red);
		g.fillRect(400, 20, player1.getJetpack().getTank().getFuel(), 30);
		g.setColor(Color.white);
	}
	
	public void reset() {
		player1.resetLocation();
		player1.getJetpack().getTank().resetTank();
	}
}
