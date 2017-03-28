package displays;

import board.Board;
import components.BackgroundAnimation;
import components.Sound;
import components.UserButtons;
import players.Computer;
import players.Human;
import processing.core.PApplet;
import ships.AirCraftCarrier;
import ships.Cruiser;
import ships.Destroyer;
import ships.Ships;
import ships.Submarine;
import ships.Uboat;

public class PlaceShips extends PApplet {
	
	private BackgroundAnimation background;
	private Board playerBoard = new Board();
	private Board computerBoard = new Board();
	private int originalX;
	private int originalY;
	private Ships getShip;
	private int direction = 0;

	private boolean setShipFlag = false;
	private boolean boardSetup = false;
	private int shipCounter = 0;
	private int shipTotal = 5;
	
	PApplet parent;
	Human player;
	Computer computer;
	Sound water;
	
	private UserButtons replaceShips = new UserButtons();
	private UserButtons playGame = new UserButtons();
	
	
	public PlaceShips(){
		
	}
	
	public void setParent(PApplet p, Human player, Computer computer){
		this.parent = p;
		this.player = player;
		this.computer = computer;
		background = new BackgroundAnimation(parent);
		background.seaBackground();	
	}
	
	public void setupPlacement(){
		// set starting point of grid display
		originalX = player.getBoardSize()/2;
		originalY = player.getBoardSize()/2;
		
		// create board for both the player and computer
		playerBoard.setParent(parent);
		computerBoard.setParent(parent);
		playerBoard.setBoard(parent.width,player.getBoardSize(),originalX,originalY);
		computerBoard.setBoard(parent.width,player.getBoardSize(),originalX,originalY);
		playerBoard.createBoard();
		computerBoard.createBoard();
		
		// board created so run place ships
		boardSetup = true;
		}
	
	public void updatePlacement(){
		// set simple vectors for ship placement on grid
		playerBoard.setSimpleVectors();
		
		// show the animated background
		background.background(true);
		
		setShip();
		// run the board methods
		playerBoard.updateBoard();
		
		//check position of mouse relative to screen
		checkMouse();
		
		// restart the board if restart button clicked
		restartPlacement();
		
		// when enough ships placed show play button
		if(shipCounter >= shipTotal){
			playGame();
		}
	}
	
	// switch statement to control ships being placed, each iterations changes the ship being placed
	public void setShip(){
		if(!setShipFlag){
			switch(this.shipCounter){
			case 0:
				this.getShip = new AirCraftCarrier();
				setShipFlag = true;
				break;
			case 1:
				this.getShip = new Cruiser();
				setShipFlag = true;
				break;
			case 2:
				this.getShip = new Destroyer();
				setShipFlag = true;
				break;
			case 3:
				this.getShip = new Submarine();
				setShipFlag = true;
				break;
			case 4:
				this.getShip = new Uboat();
				setShipFlag = true;
				break;
			}
		}
	}
	
	// check that the board has been created before running the update methods
	public boolean boardSetup(){
		return this.boardSetup;
	}
	
	
	// create restart board button
	public void restartPlacement(){
		replaceShips.createButton("Restart",20,410,100,150,30,200,parent);
		
		// create hover effect
		if(this.replaceShips.mouseOnObject(parent.mouseX,parent.mouseY,replaceShips.returnX(), replaceShips.returnY(), replaceShips.returnSize(), replaceShips.returnHeight())){
			replaceShips.setColor(255);
		}
		replaceShips.drawButton();
	}
	
	// create play game button
	public void playGame(){
		playGame.createButton("Play",20,410,600,150,30,200,parent);
		
		// create hover effect
		if(this.playGame.mouseOnObject(parent.mouseX,parent.mouseY,playGame.returnX(), playGame.returnY(), playGame.returnSize(), playGame.returnHeight())){
			playGame.setColor(255);
		}
		playGame.drawButton();
	}
	
	
	public void mousePressed(){
		// right mouse click function to change between horizontal and vertical ship placement
		// true = horizontal
		// false = vertical
		if (parent.mouseButton == RIGHT) {
			if(direction == 0){
				direction = 1;
			}else{
				direction = 0;
			}
		}else if(parent.mouseButton == LEFT){
			// check for out of bounds
			// if in bounds then place ship and play water sound
			if(playerBoard.isInGrid(playerBoard.getSimpleX(), playerBoard.getSimpleY(), getShip.getSize(), player.getBoardSize(), direction) && shipCounter < shipTotal){
				// check if the cells don't already contain a ship before updating
				playerBoard.addShip(playerBoard.getSimpleX(), playerBoard.getSimpleY(), getShip, direction);
				computerBoard.addComputerShip(getShip);
				water = new Sound("water.wav", false,0);
				this.setShipFlag = playerBoard.isShipPlaced();
			 }
			// refresh the board when restart button clicked
			if(replaceShips.mouseOnObject(parent.mouseX,parent.mouseY,replaceShips.returnX(), replaceShips.returnY(), replaceShips.returnSize(), replaceShips.returnHeight())){
				playerBoard.createBoard();
				playerBoard.setShipCounter(0);
				computerBoard.createBoard();
				computerBoard.setShipCounter(0);
				setShipFlag = false;
				this.shipCounter = 0;
			}
			
			//play game
			if(this.playGame.mouseOnObject(parent.mouseX,parent.mouseY,playGame.returnX(), playGame.returnY(), playGame.returnSize(), playGame.returnHeight())){
				player.setGameState(3);
				player.setBoard(playerBoard);
				computer.setBoard(computerBoard);
				player.setShips(shipCounter);
				computer.setShips(shipCounter);
			}
			
			// update shipCounter to show ship has been placed
			this.shipCounter = playerBoard.getShipCounter();
		 }
	}

	// check mouse position for hover effects when placing a ship
	public void checkMouse(){
		if(playerBoard.isInGrid(playerBoard.getSimpleX(), playerBoard.getSimpleY(), getShip.getSize(), player.getBoardSize(), direction) && shipCounter < shipTotal){
			playerBoard.cellHover(playerBoard.getSimpleX(), playerBoard.getSimpleY(), getShip.getSize(), direction);
			playerBoard.cellEmpty(playerBoard.getSimpleX(), playerBoard.getSimpleY(), getShip.getSize(), direction);
		}
	}

}