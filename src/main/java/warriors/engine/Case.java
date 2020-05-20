package warriors.engine;



public abstract class Case {
	
	String Name;
	int power;
	
	public Case(String name, int power){
		this.Name=name;
		this.power=power;
	}

	
	public Case(String name) {
		this.Name=name;
	}
	
	public String  getName() {
		return this.Name;
	}
	
	public int getPower() {
		return this.power;
	}
	
	
	
	public abstract void launchEvent(GameEtat etatGame);
		
	
	

	
}


