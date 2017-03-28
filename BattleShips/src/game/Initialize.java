package game;

import components.Fonts;
import components.Sound;
import displays.CreateGame;
import displays.MainMenu;
import displays.PlaceShips;
import displays.PlayGame;
import players.Computer;
import players.Human;
import processing.core.PApplet;

public class Initialize extends PApplet{

	private PApplet parent;
	private final int windowHeight = 800;
	private final int windowWidth = 800;
	
	// create all the displays
	MainMenu mainMenu = new MainMenu();
	PlaceShips placeShips = new PlaceShips();
	CreateGame createGame = new CreateGame();
	PlayGame playGame = new PlayGame();
	
	Sound music;
	
    public Human player = new Human();
    public Computer computer = new Computer();
	
	public Initialize(){
		
	}
	
	public void windowSize(PApplet p){
		this.parent = p;
		this.parent.size(windowHeight, windowWidth,P3D);
	}
	
	public void setup(){
		music = new Sound("gameMusic2.wav", true, 30); 
		new Fonts(parent);
		parent.cursor(CROSS);	
		parent.smooth();
		computer.setParent(parent);
		mainMenu.setParent(parent, this.player);
		createGame.setParent(parent, this.player, this.computer);
		placeShips.setParent(parent, this.player, this.computer);
		playGame.setParent(parent, this.player, this.computer);
	}
	
	public void play(){
		switch(player.getGameState()){
		case 0:
			// show main menu
			mainMenu.drawMenu();
			break;
		case 1:
			// create game
			createGame.setGame();
			break;
		case 2:
			//place ships on grid
			if(!placeShips.boardSetup()){
				placeShips.setupPlacement();
			}
			placeShips.updatePlacement();
			break;
			
		case 3:
			// play game
			playGame.start();
			break;
		default:
			// error so back to main menu
			player.setGameState(0);
			break;
		}
	}
	
	public void mousePressed(){
		switch(player.getGameState()){
		case 0:
			mainMenu.mousePressed();
			break;
		case 1:
			createGame.mousePressed();
			break;
		case 2:
			placeShips.mousePressed();
			break;
		case 3:
			playGame.mousePressed();
			break;
		default:
			player.setGameState(0);
			break;
		}
	}
}
