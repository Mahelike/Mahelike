package players;

import board.Board;
import processing.core.PApplet;

public class Computer{
	
	private int difficulty;
	private int boardSize;
	private int totalShips;
	PApplet parent;
	Board board = new Board();
	public boolean test = false;

	public Computer(){
		
	}
	
	public void setBoard(Board board){
		this.board = board;
	}
	
	public void setParent(PApplet p){
		this.parent = p;
	}
	
	public Board getBoard(){
		return this.board;
	}
	
	public void showBoard(){
		for(int i = 0; i < board.getBoardSize(); i++){
			for(int j = 0; j < board.getBoardSize(); j++){
				if(board.getCells()[j][i].hasAShip()){
					System.out.print("x  ");
				}else{
					System.out.print("-  ");
				}
			}
			System.out.println();
		}
		test = true;
	}
	
	public boolean makeATurn(Human player){
		//if(difficulty == 1){
			if(!player.getBoard().fire((int)parent.random(0,board.getBoardSize()), (int)parent.random(0,board.getBoardSize()))){
				return false;
			}else{
				return true;
			}
		//}
	}
	
	
	public void setDifficulty(int value){
		this.difficulty = value;
	}
	
	public int getDifficulty(){
		return this.difficulty;
	}
	
	public void setBoardSize(int value){
		this.boardSize = value;
	}
	
	public int getBoardSize(){
		return this.boardSize;
	}
	
	public void setShips(int value){
		this.totalShips = value;
	}
	
	public int getShips(){
		return this.totalShips;
	}

}
