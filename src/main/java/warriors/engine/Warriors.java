package warriors.engine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
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
//		Hero warrior = new WarriorHero();
//		warriors.add(warrior);
//		Hero emperor = new WizardHero();
//		warriors.add(emperor);
		Map mapchoice1 = new MapChoice("Alderaan",64);
		map.add(mapchoice1);
		Map mapchoice2= new MapChoice("Dagoba",64);
		map.add(mapchoice2);
		gameList = new HashMap<String, GameEtat>();

		
	}



	public List<? extends Hero> getHeroes(){
	try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "mickaell";
			String passwd = "cheerfulguys84";
			Connection conn = DriverManager.getConnection(url, user, passwd);
			//Création d’un objet Statement
			Statement state = conn.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT * FROM Hero");
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			System.out.println("\n**********************************");
			warriors.add((Hero) result);
	//		On affiche le nom des colonnes
//			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
//				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
//			System.out.println("\n**********************************");
//			while(result.next()){
//				
//				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
//					System.out.print("\t" + result.getObject(i).toString() + "\t |");
//				System.out.println("\n---------------------------------");
//			}
			
			result.close();
			state.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
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

