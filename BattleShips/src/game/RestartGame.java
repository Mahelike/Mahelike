package game;

public class RestartGame {

	private boolean restart = false;
	
	public RestartGame(){
		
	}
	
	public void restart(){
		if(this.restart){
			this.restart = false;
		}else{
			this.restart = true;
		}
	}
	
	public boolean getRestart(){
		return this.restart;
	}
	
	
}
