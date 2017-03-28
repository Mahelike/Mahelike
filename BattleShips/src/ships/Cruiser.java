package ships;

public class Cruiser extends Ships{

	int size = 4;
	int health = 4;
	String name = "Cruiser";
	
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
