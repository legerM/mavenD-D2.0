package warriors.weapons;

import warriors.engine.Case;
import warriors.engine.GameEtat;
import warriors.hero.Character;

public class WeaponWarriorCase extends Case {
	

	WeaponWarriorCase(String name,int dmgs){
		super(name,dmgs);
	}
	
	public String toString() {
		return "vous trouvez un "+getName()+" qui vous donne  "+getPower()+" Dmgs";
	}

	@Override
	public void launchEvent(GameEtat etatGame) {
		String newLog = etatGame.getLastLog();
		newLog+= "\n"+toString();
		int newPower = getPower();
		((Character)etatGame.getHero()).setAttackLevel(newPower);
		((GameEtat) etatGame).setLastLog(newLog);

	
	}
	
}