package components;

import processing.core.PApplet;

public class BackgroundAnimation extends PApplet{
	
	PApplet parent;
	int cols = 1400/ 20;
	int rows = 1200 /20;
	int scl = 20;
	float [][] sea;
	float moveBackground = 0;
	
	public BackgroundAnimation(PApplet p){
		this.parent = p;
	}
	
	public void background(boolean twoD){
		int mapx = 1;
		int mapy = 2;
		parent.pushMatrix();
		parent.background(0,159,225);
		parent.fill(0,105,148);
		parent.stroke(0,51,72);
		if(!twoD){
			mapx = 0;
			mapy = 1;
			parent.translate(parent.height/4 , parent.width/2);
			parent.rotateX(PI/3);
			parent.translate(-parent.height/2,-parent.width/2);
		}else{
			parent.translate(-parent.height/4, -60);
		}
		moveBackground -= 0.05;
		float yoff = moveBackground;
		for(int y = 0; y < rows; y++){
			float xoff = 0;
			for(int x = 0; x < cols ; x++){
				sea[x][y] = map(noise(xoff,yoff), mapx,mapy, -40, 40);
				xoff += 0.1;
			}
			yoff += 0.1;
		}
		for(int y =0; y < rows -1; y++){
			parent.beginShape(TRIANGLE_STRIP);
			for(int x =0; x < cols; x++){
				parent.vertex(x*scl,y*scl, sea[x][y]);
				parent.vertex(x*scl,(y+1)*scl, sea[x][y+1]);
			}
			parent.endShape();
		}
		parent.popMatrix();
	}
	
	public void seaBackground(){
		sea = new float[cols][rows];
	}

}
