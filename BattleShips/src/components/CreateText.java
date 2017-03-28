package components;

import processing.core.PApplet;

public class CreateText {
	
		PApplet parent;

		public CreateText(){
			
		}
		
		public void showText(String text, int fontSize,int x, int y, int width, int height, int color, PApplet p){
			this.parent = p;
			String title = text;
			parent.fill(color);
			parent.textSize(fontSize);
			parent.text(title,x, y, width, height);
		}
		
}
