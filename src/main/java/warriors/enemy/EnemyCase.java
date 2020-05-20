package warriors.enemy;

import warriors.contracts.GameStatus;
import warriors.engine.Case;
import warriors.engine.GameEtat;
import warriors.hero.Character;

public class EnemyCase extends Case{
	GameEtat game;

	int hP;


	EnemyCase(String name, int hP, int pP){

		super(name , pP);
		this.hP=hP;


	}


	public int getHP() {
		return this.hP;
	}


	public String toString() {
		return "Vous etes face à un "+this.getName()+" qui a "+this.getHP()+" HP "+" et "+this.getPower()+" PP ";
	}


	public void launchEvent(GameEtat EtatGame) {
		int enemyAttack = getPower();
		int newLife=(EtatGame.getHero().getLife() - enemyAttack);
		String newLog = EtatGame.getLastLog();
		int enemyLife= getHP();
		
	
		newLog+= "\n"+toString();
		while (enemyLife > 0 && newLife >0) {
			newLog+="\n"+ "vous infligez à "+getName()+" "+EtatGame.getHero().getAttackLevel()+" Dmgs";
			enemyLife -= EtatGame.getHero().getAttackLevel();
			
			if(enemyLife <=0) {
					newLog +="\n" +getName() + " à été abattu ";
					break;
				}
			newLog+="\n"+getName()+" à "+enemyLife+"HP";
			newLog += "\n"+getName()+"Inflige "+enemyAttack+" Dmgs" ;
			((Character) EtatGame.getHero()).setLife(newLife);
		
			newLog+="\n"+EtatGame.getHero().getName()+" il vous reste "+newLife+" HP";
						
			

		}
	
		if(newLife <=0) {
			newLog += "Vous avez péri ";
			
			EtatGame.setGameStatus(GameStatus.GAME_OVER);
		}
		
		((GameEtat) EtatGame).setLastLog(newLog);
//		return newLog;
	}


}
