package ships;

public class AirCraftCarrier extends Ships{

	int size = 5;
	int health = 5;
	String name = "AircraftCarrier";
	
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
