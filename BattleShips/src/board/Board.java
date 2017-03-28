package board;

import processing.core.PApplet;
import ships.Ships;

public class Board {

	private int cellSize;
	private int boardSize;
	PApplet parent;
	Cells [][] cells;
	private int xPosition = -1;
	private int yPosition = -1;
	private int originalX;
	private int originalY;
	private int shipCounter =0;
	boolean shipFlag = false;
	public int simpleXVector;
	public int simpleYVector;
	public Board(){

	}

	public void setBoard(int width, int boardSize, int originalX, int originalY){
		this.cellSize = (width / 2) / boardSize;
		this.boardSize = boardSize;
		this.originalX = originalX;
		this.originalY = originalY;
	}
	
	public int getCellSize(){
		return this.cellSize;
	}
	
	// check our X and Y coords and reduce them down to integers that match the array keys
	// for simplicity
	public void setSimpleVectors(){
		simpleXVector = ((parent.mouseX - (parent.mouseX % this.cellSize)) / this.cellSize) - originalX;
		simpleYVector = ((parent.mouseY - (parent.mouseY % this.cellSize)) / this.cellSize) - originalY;
	}
	
	public int getSimpleX(){
		return this.simpleXVector;
	}
	
	public int getSimpleY(){
		return this.simpleYVector;
	}
	
	public void setParent(PApplet p){
		this.parent = p;
	}
	
	public boolean isShipPlaced(){
		return this.shipFlag;
	}
	
	public void setAxis(int x, int y){
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public int getBoardSize(){
		return this.boardSize;
	}
	
	public int getShipCounter(){
		return this.shipCounter;
	}
	
	public void setShipCounter(int value){
		this.shipCounter = value;
	}
	
	public void createBoard(){
		this.cells = new Cells[this.boardSize][this.boardSize];
		for(int i =0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j++){
				this.cells[i][j] = new Cells(this.cellSize);
				this.cells[i][j].setParent(this.parent);
				this.cells[i][j].setPosition(this.originalX + i,this.originalY +j);
			}
		}
	}
	
	public Cells [][] getCells(){
		return this.cells;
	}
	
	public void cellHover(int x, int y, int size, int direction){
		if(direction == 0){
			for(int i = x; i < x + size; i++){
				this.cells[i][y].updateCell(true);
			}
		}else{
			for(int i = y; i < y + size; i++){
				this.cells[x][i].updateCell(true);
			}
		}
	}
	
	public void cellEmpty(int x, int y, int size, int direction){
		for(int i=0; i < this.boardSize; i++){
			for(int j = 0; j < this.boardSize; j ++){
				if(direction == 0){
					if(i < x || i > x + size || j < y || j > y ){
						this.cells[i][j].updateCell(false);
					}
				}else{
					if(j < y || j > y + size || i < x || i > x ){
						this.cells[i][j].updateCell(false);
					}
				}
			}
			
		}
	}
	
	public void drawBoard(int x, int y, boolean player){
		for(int i=0; i < this.boardSize; i++){
			for(int j =0; j < this.boardSize; j++){
				this.cells[i][j].setPosition(x + i,y + j);
				this.originalX = x;
				this.originalY = y;
				if(player){
					this.cells[i][j].paintCell();
				}else{
					this.cells[i][j].draw();
				}
			}
		}
	}
	
	public void updateBoard(){
		for(int i=0; i < this.boardSize; i++){
			for(int j =0; j < this.boardSize; j++){
				this.cells[i][j].paintCell();
				if(i == this.xPosition && j == this.yPosition){
					this.cells[i][j].updateCell(true);
				}
			}
		}
	}
	
	public boolean fire(int x, int y){
		if(this.cells[x][y].beenFired){
			return false;
		}else{
			this.cells[x][y].fire();
			return true;
		}
	}
	
	public void addShip(int x, int y,Ships ship, int direction){
		if(direction == 0){
			//check if there is already a ship placed
			for(int k = x; k < x + ship.getSize(); k ++){
				if(this.cells[k][y].hasAShip()){
					shipFlag = true;
				}
			}	
			if(!shipFlag){
				for(int i = x; i < x + ship.getSize(); i ++){
					this.cells[i][y].setShip(ship);		
				}	
				this.shipCounter++;
			}else{
				shipFlag = false;
			}
		}else{
			for(int k = y; k < y + ship.getSize(); k ++){
				if(this.cells[x][k].hasAShip()){
					shipFlag = true;
				}		
			}
			if(!shipFlag){
				for(int i = y; i < y + ship.getSize(); i++){
					this.cells[x][i].setShip(ship);
				}
				this.shipCounter++;
			}else{
				shipFlag = false;
			}
		}
	}
	
	
	public void addComputerShip(Ships ship){
		boolean check = false;
		int direction = (int)parent.random(0,2);
		int size = ship.getSize();
		if(direction == 0){
				int x = (int)parent.random(0,boardSize - size);
				int y = (int)parent.random(0,boardSize);
				//check if there is already a ship placed
				for(int k = x; k < x + ship.getSize(); k ++){
					if(this.cells[k][y].hasAShip()){
						check = true;
					}
				}
				if(!check){
					for(int i = x; i < x + ship.getSize(); i ++){
						this.cells[i][y].setShip(ship);		
					}	
					this.shipCounter++;
				}else{
					addComputerShip(ship);
				}
			}else{
				int x = (int)parent.random(0,boardSize);
				int y = (int)parent.random(0,boardSize - size);
				for(int k = y; k < y + ship.getSize(); k ++){
					if(this.cells[x][k].hasAShip()){
						check = true;
					}		
				}
				if(!check){
					for(int i = y; i < y + ship.getSize(); i++){
						this.cells[x][i].setShip(ship);
					}
					this.shipCounter++;
				}else{
					addComputerShip(ship);
				}
			}
		}
	
	// generic method to check if the mouse is on the grid to prevent null pointers
		// takes direction boolean for both horizontal and vertical checks
		public boolean isInGrid(int x, int y, int shipSize, int boardSize, int direction ){
			boolean flag = false;
			if(direction == 0){
				if(((x + shipSize) <= boardSize && x >= 0) && (y < boardSize && y >= 0)){
					flag = true;
				}else{
					flag = false;
				}
			}else{
				if(((y + shipSize) <= boardSize && y >= 0) && (x < boardSize && x >= 0)){
					flag = true;
				}else{
					flag = false;
				}
			}
			return flag;
		}
	}



