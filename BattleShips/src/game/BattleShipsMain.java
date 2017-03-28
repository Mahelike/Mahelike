package game;

import processing.core.PApplet;

public class BattleShipsMain extends PApplet{

	Initialize initialize  = new Initialize();
	public static void main(String[] args) {
		PApplet.main(new String[] {"game.BattleShipsMain"});
	}

	public void settings(){
		initialize.windowSize(this);
	}
	
	public void setup(){
		initialize.setup();
	}
	
	public void draw(){
		initialize.play();
		
	}
	
	public void mousePressed(){		
		initialize.mousePressed();
	}
}
