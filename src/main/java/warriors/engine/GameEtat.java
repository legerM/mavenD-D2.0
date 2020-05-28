package warriors.engine;

import warriors.contracts.GameState;
import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;

public class GameEtat implements GameState {

	String playername;
	String gameId;
	GameStatus Gamestat;
	Hero hero;
	MapChoice map;
	String lastlog;
	public int currentCase;
	int gamecount = 0;
	int ID;

	public GameEtat(String playername, String gameId, Hero hero, MapChoice map ,int ID){

		this.playername=playername;
		this.gameId=gameId;
		this.hero=hero;
		this.map=map;
		this.lastlog=gameId;
		this.currentCase=0;
		this.Gamestat=GameStatus.IN_PROGRESS;
		this.ID=ID;
	}

	public GameEtat() {


	}

	public GameEtat(String playername, String gameId, Hero hero, MapChoice map) {
		this.playername=playername;
		this.gameId=gameId;
		this.hero=hero;
		this.map=map;
		this.lastlog=gameId;
		this.currentCase=0;
		this.Gamestat=GameStatus.IN_PROGRESS;
		
	}
	
public void setID(int ID) {
	this.ID=ID;
	
}
public int getID() {
	return ID;
	
}
	public String getPlayerName() {

		// TODO Auto-generated method stub
		return playername;
	}

	public void setPlayerName(String playername) {
		this.playername=playername;
	}
	public String getGameId() {

		// TODO Auto-generated method stub
		return gameId;

	}

	public void SetGameId(String gameId) {
		this.gameId=gameId;
	}
	
	
	public GameStatus getGameStatus() {

		// TODO Auto-generated method stub
		return Gamestat;
	}


	public Hero getHero() {
		// TODO Auto-generated method stub
		return hero;
	}


	public Map getMap() {
		// TODO Auto-generated method stub
		return map;
	}


	public String getLastLog() {

		return this.lastlog;
	}

	public int getCurrentCase() {

		return currentCase ;

	}
	public void setGameStatus(GameStatus Gamestat) {
		this.Gamestat=Gamestat;
	}

	public void moveForward(int dice,GameEtat gameS) {

		currentCase = currentCase + dice;

		if (currentCase > this.map.getNumberOfCase()) {

			Gamestat = GameStatus.FINISHED;
			setGameStatus(Gamestat);
			setLastLog("Vous etes sorti du Donjon !");

		}

		else {

			Case caseToPlay = this.map.getCaseAtPosition(currentCase);


			lastlog = caseToPlay.toString();
			setLastLog("vous etes a la case : " + currentCase);
			setCurrentCase(currentCase);
			caseToPlay.launchEvent(gameS);
			//			System.out.println(gnu);

		}
	}

	public void setLastLog(String lastlog) {
		this.lastlog=lastlog;
	}
	public void setCurrentCase(int currentCase){
		this.currentCase=currentCase; 
	}


}


