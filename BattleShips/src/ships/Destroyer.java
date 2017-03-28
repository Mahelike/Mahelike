package ships;

public class Destroyer extends Ships {

	int size = 3;
	int health = 3;
	String name = "Destroyer";
	
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
