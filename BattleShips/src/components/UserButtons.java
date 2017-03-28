package components;

import processing.core.PApplet;

public class UserButtons extends PApplet{

	PApplet parent;
	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	private int color;
	private int fontSize;
	private String text;
	private int alpha = 0;
	private boolean isActive = false;
	Sound click;
	
	
	public UserButtons(){
	
	}
	
	public void createButton(String text, int fontSize,int x, int y, int width, int height, int color, PApplet p){
		this.xPosition = x;
		this.yPosition = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.text = text;
		this.parent = p;
		this.fontSize = fontSize;
	}
	
	public void drawButton(){
		this.parent.noStroke();
		this.parent.fill(this.color,0,0);
		this.parent.rect(this.xPosition,this.yPosition, this.width, this.height, 5);
		this.parent.fill(255);
		this.parent.textSize(this.fontSize);
		this.parent.text(text, this.xPosition + this.width/4, (this.yPosition + this.height) - fontSize /2);
	}
	
	public void drawButtonTransparent(){
		this.parent.stroke(255);
		if(this.isActive){
			this.parent.fill(255,0,0, 255);
		}else{
			this.parent.fill(255,0,0, this.alpha);
		}
		this.parent.rect(this.xPosition,this.yPosition, this.width, this.height, 5);
		this.parent.fill(255);
		this.parent.textSize(this.fontSize);
		this.parent.text(text, this.xPosition + this.width/6, ((this.yPosition + this.height) - (this.height / 2)) + this.fontSize /4);
	}
	
	public boolean mouseOnObject(int mouse_x, int mouse_y, int object_x, int object_y, int object_size, int object_height){
		boolean flag = false;
		if(mouse_x >= object_x && mouse_x <= object_x + object_size && mouse_y >= object_y && mouse_y <= object_y + object_height){
			flag = true;
		}
		return flag;
	}
	
	public void buttonPressed(){
		click = new Sound("click.wav", false, 0);
	}
	
	
	public int returnX(){
		return this.xPosition;
	}
	
	public int returnY(){
		return this.yPosition;
	}
	
	public int returnSize(){
		return this.width;
	}
	
	public int returnHeight(){
		return this.height;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	public void setAlpha(int alpha){
		this.alpha = alpha;
	}
	
	public void isActive(){
		this.isActive = true;
		buttonPressed();
	}
	
	public void isNotActive(){
		this.isActive = false;
	}
	
}
