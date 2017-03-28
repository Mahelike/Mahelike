package board;

import processing.core.PApplet;
import ships.Ships;

public class Cells {
	

	private boolean hasBeenClicked = false;
	private boolean hasAShip = false;
	boolean beenFired = false;
	private int xPosition;
	private int yPosition;
	private PApplet parent;
	boolean hit = false;
	private int cellSize;
	
	
	Ships ship;
	
	public Cells(int cellSize){
		this.cellSize = cellSize;
	}
	
	public int getCellSize(){
		return this.cellSize;
	}
	
	public void setPosition(int x, int y){
		this.xPosition = x;
		this.yPosition = y;
	}
	
	public boolean hasAShip(){
		return this.hasAShip;
	}
	
	public boolean returnClicked(){
		return this.hasBeenClicked;
	}
	
	public void setParent(PApplet p){
		this.parent = p;
	}
	
	public void draw(){
		if(hasAShip && beenFired){
			this.parent.fill(255,0,0);
		}else if(!hasAShip && beenFired){
			this.parent.fill(51,153,255);
		}else{
			this.parent.fill(255);
		}
		this.parent.rect(this.xPosition * this.cellSize, this.yPosition * this.cellSize, this.cellSize, this.cellSize);
	}
	
	
	public void paintCell(){
		this.parent.stroke(0);
		if(!this.hasBeenClicked && !hasAShip && !beenFired){
			//white
			this.parent.fill(255);
		}else if(hasAShip && beenFired){
			//red 
			this.parent.fill(255,0,0);
			// has been hit
			this.isHit();
		}else if(hasAShip && !beenFired){
			//grey
			this.parent.fill(100);
		}else if(!hasAShip && beenFired){
			this.parent.fill(51,153,255);
		}else{
			this.parent.fill(100);
		}
		this.parent.rect(this.xPosition * this.cellSize, this.yPosition * this.cellSize, this.cellSize, this.cellSize);
	}
	
	
	public void updateCell(boolean set){
		this.hasBeenClicked = set;
		this.paintCell();
	}

	
	public void setShip(Ships ship){
		this.hasAShip = true;
		this.ship = ship;
	}
	
	public void fire(){
		this.beenFired = true;
	}
	
	public void isHit(){
		this.ship.gotHit();
	}
}
