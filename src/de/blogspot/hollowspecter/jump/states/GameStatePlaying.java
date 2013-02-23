package de.blogspot.hollowspecter.jump.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.blogspot.hollowspecter.jump.maps.Testmap;
import de.blogspot.hollowspecter.jump.objects.Booster;
import de.blogspot.hollowspecter.jump.objects.Player;
import de.blogspot.hollowspecter.jump.other.Constants;
import de.blogspot.hollowspecter.jump.other.Score;
import de.blogspot.hollowspecter.jump.other.paths;

public class GameStatePlaying extends BasicGameState {
	
	protected Player player1;
	protected Testmap testmap;
	protected Image fuel_hud;
	protected Sound fuel_sound;
	private Music music;
	
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
		fuel_sound = new Sound("res/sfx/fuel.wav");
		
    	for (Booster boost : testmap.getBoosts()) {
    		boost.init(container);
    	}
		
		fuel_hud = new Image("res/img/fuel.png");
		
		//music handling
		music = new Music("res/sfx/music/playing.wav");
	}

	@Override
    public void update(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
    	player1.update(container, delta);
    	collision(container, state, delta);
    	if (player1.getPosY() > 500)
    	{
    		if(player1.getPosX() > testmap.getMapwidth())
    		{
    			reset();
    			state.enterState(GameStates.Win);
    		}
    		else
    		{
    			reset();
        		state.enterState(GameStates.GameOver);
    		}
    	}
    	
    	if (!music.playing())
    		music.loop();
	}
	
    @Override
	public void render(GameContainer container, StateBasedGame state, Graphics g) throws SlickException
	{    	
    	translate2(container, g);
    	testmap.render(container, g);
    	
    	for (Booster boost : testmap.getBoosts()) {
    			boost.render(container, g);
    	}
    	
    	player1.render(container, g);
    	
    	//Debug HUD
//    	HUD(container, g);
    	realHUD(container, g);
	}
    
    //translate
    public void translate2(GameContainer container, Graphics g) throws SlickException
    {
    	if ((player1.getPosX()+540) < testmap.getMapwidth())
    		g.translate(-player1.getPosX()+100, 0);
    	else
    		g.translate(-1*(testmap.getMapwidth()-640),0);
    }
	

	@Override
	public int getID() {
		return GameStates.PlayingState;
	}
	
	//REAL HUD
	public void realHUD(GameContainer container, Graphics g) throws SlickException
	{
		if ((player1.getPosX()+540) < testmap.getMapwidth())
    		g.translate(player1.getPosX(), 0);
    	else
    		g.translate(testmap.getMapwidth()-540,0);
		fuelBar(container, g);
		g.setColor(Color.black);
		g.drawString("Score: "+Math.round(player1.getPosX()), -90, 15);
	}
	
	// Debug HUD
	public void HUD(GameContainer container, Graphics g) throws SlickException
	{
		g.setColor(Color.black);
		g.drawString("posX: "+player1.getPosX(), -90, 0);
		g.drawString("posY: "+player1.getPosY(), -90, 15);
		g.drawString("spdX: "+player1.getSpdX(), -90, 30);
		g.drawString("spdY: "+player1.getSpdY(), -90, 45);
		g.drawString(hud_string, -90, 60);
		g.setColor(Color.white);
	}
	
	public void collision(GameContainer container, StateBasedGame state, int delta) throws SlickException
	{
		hud_string = "Collision: FALSE";
		player1.setState(Constants.PLAYER_air);
		boolean dmg_coll = false;
		
    	for (Booster boost : testmap.getBoosts()) {
    		if (!boost.isCollected()) {
    			if (player1.checkCollisionWith(boost.getShape())) {
        			boost.collect();
        			player1.getJetpack().getTank().gasUp(10);
        			fuel_sound.play();
        		}
    		}
    	}
		
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
    	{
    		reset();
    		state.enterState(GameStates.GameOver);
    	}	
	}
	
	public void fuelBar(GameContainer container, Graphics g) throws SlickException
	{
		fuel_hud.draw(378, 20);
		g.setColor(Color.red);
		g.fillRect(400, 20, player1.getJetpack().getTank().getFuel()*2, 20);
		g.drawRect(399, 19, player1.getJetpack().getTank().getTank_capacity()*2, 21);
		g.setColor(Color.white);
	}
	
	public void reset() {
		Score.score = Math.round(player1.getPosX());
		music.stop();
		player1.resetLocation();
		player1.getJetpack().getTank().resetTank();
		for (Booster boost : testmap.getBoosts()) {
    		boost.setCollected(false);
    	}
	}
}
