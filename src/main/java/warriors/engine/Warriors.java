package warriors.engine;

import java.util.ArrayList; 
//import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import warriors.contracts.GameState;
//import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;
import warriors.hero.WarriorHero;
import warriors.hero.WizardHero;

public class Warriors implements WarriorsAPI {
	List<Map> map ;
	List<Hero> warriors ;
	//	private GameStatus status = GameStatus.IN_PROGRESS;
	protected java.util.Map <String,GameEtat>gameList;

	Random random = new Random();
	int gameCount=0;

	public Warriors(){

		warriors = new ArrayList<Hero>();
		map = new ArrayList<Map>();
		Hero warrior = new WarriorHero();
		warriors.add(warrior);
		Hero emperor = new WizardHero();
		warriors.add(emperor);
		Map mapchoice1 = new MapChoice("Alderaan",64);
		map.add(mapchoice1);
		Map mapchoice2= new MapChoice("Dagoba",64);
		map.add(mapchoice2);
		gameList = new HashMap<String, GameEtat>();

	}



	public List<? extends Hero> getHeroes(){

		return warriors;

	}


	public List<? extends Map> getMaps() {
		// TODO Auto-generated method stub
		return map ;
	}


	public GameState createGame(String playerName, Hero hero, Map map) {

		String gameId= "game - " +gameCount;
		gameCount ++ ;
		GameEtat game = new GameEtat(playerName,gameId,hero, (MapChoice) map);
		gameList.put(game.getGameId(), game);

		return game;
	}


	public GameState nextTurn(String gameID) {	

		int dice = rollDice();
		GameEtat gameS = gameList.get(gameID);
		gameS.moveForward(dice,gameS);
		
		return gameS;

	}


	private int rollDice() {

		int dice = 1 +random.nextInt(6-1);
		System.out.println("vous lancez un dé 6 et faites : " +dice);
		return dice;

	}


}

