package displays;

import components.BackgroundAnimation;
import components.UserButtons;
import players.Computer;
import players.Human;
import components.CreateText;
import processing.core.PApplet;

public class CreateGame{

	PApplet parent;
	BackgroundAnimation background;
	UserButtons continueButton = new UserButtons();
	//8x8
	UserButtons gridButtonSizeEight = new UserButtons();
	//10x10
	UserButtons gridButtonSizeTen = new UserButtons();
	//12x12
	UserButtons gridButtonSizeTwelve = new UserButtons();
	//easy
	UserButtons easyButton = new UserButtons();
	//medium
	UserButtons mediumButton = new UserButtons();
	//hard
	UserButtons hardButton = new UserButtons();
	
	CreateText text = new CreateText();
	Human player;
	Computer computer;
	
	public void setParent(PApplet p, Human player, Computer computer){
		this.parent = p;
		this.player = player;
		this.computer = computer;
		background = new BackgroundAnimation(parent);
		background.seaBackground();	
	}
	
	public void setGame(){
		background.background(true);
		header();
		chooseGridSize();
		chooseDifficulty();
		continueButton();
	}
	
	//create continue button
	public void continueButton(){
		if(computer.getDifficulty() > 0 && player.getBoardSize() > 0){
			continueButton.createButton("Continue",20, 600 ,600 ,150 ,30 ,200,parent);
			// check if continue button is active
			if(this.continueButton.mouseOnObject(parent.mouseX,parent.mouseY,continueButton.returnX(), continueButton.returnY(), continueButton.returnSize(), continueButton.returnHeight())){
				continueButton.setColor(255);
			}
			continueButton.drawButton();
		}
	}

	public void header(){
		text.showText("Create your game", 56,170,10, 600, 80, 255, parent);
	}
	
	public void chooseGridSize(){
		text.showText("Choose your gridsize",30, 20, 100, 600, 80, 255, parent);
		
		// 8x8
		gridButtonSizeEight.createButton("8x8",30,120,155,150,150,0,parent);
		if(gridButtonSizeEight.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeEight.returnX(), gridButtonSizeEight.returnY(), gridButtonSizeEight.returnSize(), gridButtonSizeEight.returnHeight())){
			gridButtonSizeEight.setAlpha(150);
		}else{
			gridButtonSizeEight.setAlpha(0);
		}
		gridButtonSizeEight.drawButtonTransparent();
		
		//10x10
		gridButtonSizeTen.createButton("10x10",30,300,155,150,150,0,parent);
		if(gridButtonSizeTen.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeTen.returnX(), gridButtonSizeTen.returnY(), gridButtonSizeTen.returnSize(), gridButtonSizeTen.returnHeight())){
			gridButtonSizeTen.setAlpha(150);
		}else{
			gridButtonSizeTen.setAlpha(0);
		}
		gridButtonSizeTen.drawButtonTransparent();
		
		//12x12
		gridButtonSizeTwelve.createButton("12x12",30,480,155,150,150,0,parent);
		if(gridButtonSizeTwelve.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeTwelve.returnX(), gridButtonSizeTwelve.returnY(), gridButtonSizeTwelve.returnSize(), gridButtonSizeTwelve.returnHeight())){
			gridButtonSizeTwelve.setAlpha(150);
		}else{
			gridButtonSizeTwelve.setAlpha(0);
		}
		gridButtonSizeTwelve.drawButtonTransparent();
	}
	
	public void chooseDifficulty(){
		text.showText("Choose your difficulty",30, 20, 350, 600, 80, 255,parent);
		
		//easy button
		easyButton.createButton("Easy",30,120,400,150,150,0,parent);
		if(easyButton.mouseOnObject(parent.mouseX,parent.mouseY,easyButton.returnX(), easyButton.returnY(), easyButton.returnSize(), easyButton.returnHeight())){
			easyButton.setAlpha(150);
		}else{
			easyButton.setAlpha(0);
		}
		easyButton.drawButtonTransparent();
		
		//medium button
		mediumButton.createButton("Medium",30,300,400,150,150,0,parent);
		if(mediumButton.mouseOnObject(parent.mouseX,parent.mouseY,mediumButton.returnX(), mediumButton.returnY(), mediumButton.returnSize(), mediumButton.returnHeight())){
			mediumButton.setAlpha(150);
		}else{
			mediumButton.setAlpha(0);
		}
		mediumButton.drawButtonTransparent();
		
		
		// hardButton
		hardButton.createButton("Hard",30,480,400,150,150,0,parent);
		if(hardButton.mouseOnObject(parent.mouseX,parent.mouseY,hardButton.returnX(), hardButton.returnY(), hardButton.returnSize(), hardButton.returnHeight())){
			hardButton.setAlpha(150);
		}else{
			hardButton.setAlpha(0);
		}
		hardButton.drawButtonTransparent();
		
	}
	
	public void mousePressed(){
		if(gridButtonSizeEight.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeEight.returnX(), gridButtonSizeEight.returnY(), gridButtonSizeEight.returnSize(), gridButtonSizeEight.returnHeight())){
			player.setBoardSize(8);
			computer.setBoardSize(8);
			
			gridButtonSizeEight.isActive();
			
			gridButtonSizeTen.isNotActive();
			gridButtonSizeTwelve.isNotActive();
		}else if(gridButtonSizeTen.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeTen.returnX(), gridButtonSizeTen.returnY(), gridButtonSizeTen.returnSize(), gridButtonSizeTen.returnHeight())){
			player.setBoardSize(10);
			computer.setBoardSize(10);
			
			gridButtonSizeTen.isActive();
			
			gridButtonSizeEight.isNotActive();
			gridButtonSizeTwelve.isNotActive();
		}else if(gridButtonSizeTwelve.mouseOnObject(parent.mouseX,parent.mouseY,gridButtonSizeTwelve.returnX(), gridButtonSizeTwelve.returnY(), gridButtonSizeTwelve.returnSize(), gridButtonSizeTwelve.returnHeight())){
			player.setBoardSize(12);
			computer.setBoardSize(12);
			
			gridButtonSizeTwelve.isActive();
			
			gridButtonSizeEight.isNotActive();
			gridButtonSizeTen.isNotActive();
		}
		
		// difficulty settings
		if(easyButton.mouseOnObject(parent.mouseX,parent.mouseY,easyButton.returnX(), easyButton.returnY(), easyButton.returnSize(), easyButton.returnHeight())){
			computer.setDifficulty(1);
			easyButton.isActive();
			
			mediumButton.isNotActive();
			hardButton.isNotActive();
		}else if(mediumButton.mouseOnObject(parent.mouseX,parent.mouseY,mediumButton.returnX(), mediumButton.returnY(), mediumButton.returnSize(), mediumButton.returnHeight())){
			computer.setDifficulty(2);
			mediumButton.isActive();
			
			easyButton.isNotActive();
			hardButton.isNotActive();
		}else if(hardButton.mouseOnObject(parent.mouseX,parent.mouseY,hardButton.returnX(), hardButton.returnY(), hardButton.returnSize(), hardButton.returnHeight())){
			computer.setDifficulty(3);
			hardButton.isActive();
			
			easyButton.isNotActive();
			mediumButton.isNotActive();
		}
		
		if(this.continueButton.mouseOnObject(parent.mouseX,parent.mouseY,continueButton.returnX(), continueButton.returnY(), continueButton.returnSize(), continueButton.returnHeight())){
			continueButton.buttonPressed();
			player.setGameState(2);
		}

	}
}
