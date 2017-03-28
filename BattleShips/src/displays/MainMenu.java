package displays;

import components.BackgroundAnimation;
import components.UserButtons;
import players.Human;
import components.CreateText;
import processing.core.PApplet;

public class MainMenu{

	private PApplet parent;
	public int gameState =  0;
	CreateText text = new CreateText();
	UserButtons newGameButton = new UserButtons();
	UserButtons loadGameButton = new UserButtons();
	BackgroundAnimation background;
	Human player;
	
	public void setParent(PApplet p, Human player){
		this.parent = p;
		this.player = player;
		background = new BackgroundAnimation(parent);
		background.seaBackground();	
	}
	
	public void drawMenu(){
		background.background(false);
		header();
		newGameButton();
		loadGameButton();
		
		//check if mouse is over the new game button
		if(this.newGameButton.mouseOnObject(parent.mouseX,parent.mouseY,newGameButton.returnX(), newGameButton.returnY(), newGameButton.returnSize(), newGameButton.returnHeight())){
			newGameButton.setColor(255);
			newGameButton.drawButton();
		}
		//check if mouse is over the load game button
		if(this.loadGameButton.mouseOnObject(parent.mouseX,parent.mouseY,loadGameButton.returnX(), loadGameButton.returnY(), loadGameButton.returnSize(), loadGameButton.returnHeight())){
			loadGameButton.setColor(255);
			loadGameButton.drawButton();
		}
	}
	
	public void header(){
		text.showText("BattleShips Reloaded",56,130,10,600,80,255,parent);
	}
	
	public void newGameButton(){
		newGameButton.createButton("New Game",20,240,100,150,30,200,parent);
		newGameButton.drawButton();
	}
	
	public void loadGameButton(){
		loadGameButton.createButton("Load Game",20,410,100,150,30,200,parent);
		loadGameButton.drawButton();
	}

	public void mousePressed(){
		if(this.newGameButton.mouseOnObject(parent.mouseX,parent.mouseY,newGameButton.returnX(), newGameButton.returnY(), newGameButton.returnSize(), newGameButton.returnHeight())){
			// button pressed so play click sound
			// and change gamestate
			newGameButton.buttonPressed();
			player.setGameState(1);
		}
		if(this.loadGameButton.mouseOnObject(parent.mouseX,parent.mouseY,loadGameButton.returnX(), loadGameButton.returnY(), loadGameButton.returnSize(), loadGameButton.returnHeight())){
			// button pressed so play click sound
			// and change gamestate
			loadGameButton.buttonPressed();
			player.setGameState(3);
		}
	}
}
