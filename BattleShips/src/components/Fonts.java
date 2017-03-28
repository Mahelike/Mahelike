package components;

import processing.core.PApplet;
import processing.core.PFont;

public class Fonts extends PApplet{

	PFont font;
	PApplet parent;

	public Fonts(PApplet p){
		this.parent = p;
		font = parent.createFont(System.getProperty("user.dir") +"\\fonts\\INFECTED.ttf", 56);
		parent.textFont(font);
	}
	
}
