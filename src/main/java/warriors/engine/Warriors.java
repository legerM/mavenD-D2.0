package warriors.engine;


import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import BDD.DAOCharacter;
import BDD.DAOGamestate;
import BDD.SdzConnection;
import warriors.contracts.GameState;
//import warriors.contracts.GameStatus;
import warriors.contracts.Hero;
import warriors.contracts.Map;
import warriors.contracts.WarriorsAPI;

public class Warriors implements WarriorsAPI {
	List<Map> map ;
	List<Hero> warriors ;
	//	private GameStatus status = GameStatus.IN_PROGRESS;
	protected java.util.Map <String,GameEtat>gameList;
	
	Random random = new Random();
	int gameCount=1;

	public Warriors(){

		warriors = new ArrayList<Hero>();
		map = new ArrayList<Map>();
//		Hero warrior = new WarriorHero();
//		warriors.add(warrior);
//		Hero emperor = new WizardHero();
//		warriors.add(emperor);
		Map mapchoice1 = new MapChoice("Alderaan",64);
		map.add(mapchoice1);
		Map mapchoice2= new MapChoice("Dagoba",64);
		map.add(mapchoice2);
		gameList = new HashMap<String, GameEtat>();
		DAOGamestate dao = new DAOGamestate(SdzConnection.getInstance());
		ArrayList<GameEtat> daoList = new ArrayList<GameEtat>();
		daoList = (ArrayList<GameEtat>) dao.findAll();
		for (int i = 0; i < daoList.size(); i++) {
			gameList.put(daoList.get(i).getGameId(),daoList.get(i));
			
		}
		
	}
	public List<? extends GameState> getallgames(){
		DAOGamestate dao = new DAOGamestate(SdzConnection.getInstance());
		return dao.findAll();


	}
	
	public List<? extends Hero> getHeroes(){
		DAOCharacter dao = new DAOCharacter(SdzConnection.getInstance());
		return dao.findAll();


	}


	public List<? extends Map> getMaps() {
		// TODO Auto-generated method stub
		return map ;
	}


	public GameState createGame(String playerName, Hero hero, Map map) {
		
		DAOGamestate dao = new DAOGamestate(SdzConnection.getInstance());
		String gameId= "game : " +gameCount;
		gameCount ++ ;
		GameEtat game = new GameEtat(playerName,gameId,hero, (MapChoice) map);
		gameList.put(game.getGameId(), game);
		dao.create(game);
		return game;
		
	}


	public GameState nextTurn(String gameID) {	
		
		DAOGamestate dao = new DAOGamestate(SdzConnection.getInstance());
		
		int dice = rollDice();
		GameEtat gameS = gameList.get(gameID);
		
//		int ID = gameS.getID();
		gameS.moveForward(dice,gameS);
		dao.update(gameS);
		return gameS;

	}


	private int rollDice() {

		int dice = 1 +random.nextInt(6-1);
		System.out.println("vous lancez un dé 6 et faites : " +dice);
		return dice;

	}


}

