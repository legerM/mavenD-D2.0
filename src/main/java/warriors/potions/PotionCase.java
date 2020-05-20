package warriors.potions;

import warriors.engine.Case;
import warriors.engine.GameEtat;
import warriors.hero.Character;

public class PotionCase extends Case{

	PotionCase(String name , int bonus){
		super(name, bonus);
		
	}


	public String toString() {
		return "Vous trouvez une "+this.getName()+" qui vous donne "+this.getPower()+" Hp";
	}


	@Override
	public void launchEvent(GameEtat etatGame) {
		String newLog = etatGame.getLastLog();
		
		int bonus = getPower();
		int newPower= bonus + etatGame.getHero().getLife();
		newLog+= "\n"+toString()+" vous avez maintenant "+newPower+" HP ";
		((Character)etatGame.getHero()).setLife(+newPower);
		((GameEtat) etatGame).setLastLog(newLog);
//		return newLog;
	
		
	}

	
}
