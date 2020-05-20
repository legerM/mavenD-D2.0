package warriors.hero;

import warriors.contracts.Hero;

public abstract class Character implements Hero {
	String Name;
	int hP;
	int pP;
	
	Character(String Name ,int pP,int hP){
		
		this.Name=Name;
		this.pP=pP;
		this.hP=hP;
		
	}

	public String getName() {
		
		// TODO Auto-generated method stub
		return this.Name;
		
	}

	public String getImage() {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	public int getLife() {
		
		// TODO Auto-generated method stub
		return this.hP;
		
	}

	public int getAttackLevel() {
		
		// TODO Auto-generated method stub
		return this.pP;
		
	}

	public  void setLife(int life) {
		this.hP=life;
	}
	public void setAttackLevel(int PP) {
		this.pP=PP;
	}
}
