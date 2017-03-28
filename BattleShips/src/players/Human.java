package players;

import board.Board;

public class Human {

	private int boardSize = 0;
	private int gameState = 0;
	private int totalShips;
	Board board;
	public boolean test = false;
	

	public Human(){
		
	}
	
	public void setBoard(Board board){
		this.board = board;
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
	
	public void setBoardSize(int boardSize){
		this.boardSize = boardSize;
	}
	
	public int getBoardSize(){
		return this.boardSize;
	}
	
	public void setGameState(int game){
		this.gameState = game;
	}
	
	public int getGameState(){
		return this.gameState;
	}
	
	public void setShips(int ships){
		this.totalShips = ships;
	}
	
	public int getShips(){
		return this.totalShips;
	}
}
