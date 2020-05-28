package BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import warriors.contracts.GameStatus;

import warriors.engine.GameEtat;


public class DAOGamestate extends DAO<GameEtat>{
	Scanner sc =new Scanner(System.in);

	public DAOGamestate(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(GameEtat obj) {
		
		try {
			//Création d’un objet Statement
//			Statement state = connect.createStatement();
			PreparedStatement state = connect.prepareStatement("INSERT INTO `GameState`(`PlayerName`, `GameID`, `GameStatus`, `CurrentCase`) VALUES ('"+obj.getPlayerName()+"','"+obj.getGameId()+"','"+obj.getGameStatus()+"','"+obj.getCurrentCase()+"')",Statement.RETURN_GENERATED_KEYS);
			//L’objet ResultSet contient le résultat de la requête SQL 
//			String string = "INSERT INTO `GameState`(`PlayerName`, `GameID`, `GameStatus`, `CurrentCase`) VALUES ('"+obj.getPlayerName()+"','"+obj.getGameId()+"','"+obj.getGameStatus()+"','"+obj.getCurrentCase()+"')";
			state.executeUpdate();
			ResultSet result = state.getGeneratedKeys();
			if(result.next()) {
				int ID= result.getInt(1);
				obj.setID(ID);
			}

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		return false;
	}

	@Override
	public boolean delete(GameEtat obj) {
		try {
			Statement state = connect.createStatement();
			System.out.println("veuillez entrer L'ID de la partie a supprimer");
			int idDelete=sc.nextInt();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "DELETE FROM `GameState` WHERE `GameID`="+idDelete;
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean update(GameEtat obj) {


		try {
			//Création d’un objet Statement
			Statement state = connect.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "UPDATE `GameState` SET `GameStatus`='"+obj.getGameStatus()+"',`CurrentCase`='"+obj.getCurrentCase()+"' WHERE `GameID` = '"+obj.getGameId()+"'";
			
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}	

	@Override

	public GameEtat findById(int id) {
		
		GameEtat loadGame = null;
		
		try {
			String string = "SELECT * FROM `GameState` WHERE ID ="+id;
			System.out.println(string);
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery(string);
		
//			Statement state = connect.createStatement();
//			ResultSet result = state.executeQuery();
			if(result.first()) {

				loadGame = new GameEtat();

				if(result.getString("GameStatus").equals("IN_PROGRESS")){			
					loadGame.setCurrentCase(result.getInt("CurrentCase"));
					loadGame.setPlayerName(result.getString("PlayerName"));
					loadGame.SetGameId(result.getString("GameID"));
					loadGame.setGameStatus(GameStatus.IN_PROGRESS);
					loadGame.setID(result.getInt("ID"));

				}
				else {
					create(loadGame);
				}
			}
			result.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return loadGame;

	}

	@Override
	public  List<GameEtat> findAll() {

		List<GameEtat> gameList = new ArrayList<>();
		GameEtat loadGame=null;
		try {

			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM `GameState` ");
//			if(result.getString("GameStatus").equals("IN_PROGRESS")){
			while(result.next()) {

				loadGame = new GameEtat();

					
					loadGame.setCurrentCase(result.getInt("CurrentCase"));
					loadGame.setPlayerName(result.getString("PlayerName"));
					loadGame.SetGameId(result.getString("GameID"));
					loadGame.setGameStatus(GameStatus.IN_PROGRESS);
					gameList.add(loadGame);

				}

//			}
			
//			else {
//				create(loadGame);
//			}
//			
			
			result.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return gameList;
	}



}
