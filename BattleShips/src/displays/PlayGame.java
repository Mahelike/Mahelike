package displays;

import components.BackgroundAnimation;
import players.Computer;
import players.Human;
import processing.core.PApplet;

public class PlayGame {
	
	PApplet parent;
	Human player;
	Computer computer;
	boolean turn = false;
	private BackgroundAnimation background;

	public PlayGame(){
		
	}
	
	public void setParent(PApplet p, Human player, Computer computer){
		this.parent = p;
		this.player = player;
		this.computer = computer;
		background = new BackgroundAnimation(parent);
		background.seaBackground();

	}
	
	
	public void start(){
		background.background(true);
		player.getBoard().drawBoard(0,0, true);
		computer.getBoard().drawBoard(computer.getBoardSize() + 1, 0, false);
		computer.getBoard().setSimpleVectors();
		turns();
	}
	
	public void turns(){
		if(!turn){
			// player
			//System.out.println("players turn");

		}else{
			// computer
			//System.out.println("computers turn");
			if(computer.makeATurn(player)){
				this.turn = false;
			}
			
		}
	}
	
	public void mousePressed(){
		computer.getBoard().fire(computer.getBoard().getSimpleX(),computer.getBoard().getSimpleY());
			this.turn = true;
	}

}
