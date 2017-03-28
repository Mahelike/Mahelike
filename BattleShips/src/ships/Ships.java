package ships;

public abstract class Ships {
	
		int health;
		int size;
		String name;	


		public abstract int getHealth();
		public abstract int getSize();
		public abstract String getName();
		public abstract void gotHit();
		
	
}
