package ships;

public class Uboat extends Ships{
	
	private int size = 2;
	private int health = 2;
	private String name = "Uboat";

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void gotHit() {
		this.health = this.health - 1;
	}
	
}
