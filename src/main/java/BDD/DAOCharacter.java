package BDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import warriors.contracts.Hero;

import warriors.hero.WarriorHero;
import warriors.hero.WizardHero;

public class DAOCharacter extends DAO<Hero>{
	Scanner sc =new Scanner(System.in);
	public DAOCharacter(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Hero obj) {

		System.out.println("Veuillez entrer un ID");

		int value1=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer une classe de Hero");
		String value2=sc.nextLine();
		System.out.println("veuillez entrer un nom ");
		String value3=sc.nextLine();

		String value4="Image";
		System.out.println("veuillez enter des points de vie ");
		int value5=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer des points de forces");
		int value6=sc.nextInt();
		sc.nextLine();
		System.out.println("Veuillez entrer une arme");
		String value7=sc.nextLine();
		System.out.println("veuillez entrer une protection");
		String value8=sc.nextLine();
		try {	
			Statement state = connect.createStatement();
			String string = "INSERT INTO `Hero`(`ID`, `Type`, `Nom`, `Image`, `HealthPoint`, `PowerPoint`, `Weapons`, `Protections`) VALUES ('"+value1+"','"+value2+"','"+value3+"','"+value4+"','"+value5+"','"+value6+"','"+value7+"','"+value8+"')";
			state.executeUpdate(string);
		
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}




	@Override
	public boolean delete(Hero obj) {
		try {
			Statement state = connect.createStatement();
			System.out.println("veuillez entrer L'ID du Hero a supprimer");
			int idDelete=sc.nextInt();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "DELETE FROM `Hero` WHERE `ID`="+idDelete;
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Hero obj) {

//		System.out.println("Veuillez entrer un ID");
//
//		int value1=sc.nextInt();
//		sc.nextLine();
//		System.out.println("veuillez entrer une classe de Hero");
//		String value2=sc.nextLine();
//		System.out.println("veuillez entrer un nom ");
//		String value3=sc.nextLine();
//
//		String value4="Image";
//		System.out.println("veuillez enter des points de vie ");
//		int value5=sc.nextInt();
//		sc.nextLine();
//		System.out.println("veuillez entrer des points de forces");
//		int value6=sc.nextInt();
//		sc.nextLine();
//		System.out.println("Veuillez entrer une arme");
//		String value7=sc.nextLine();
//		System.out.println("veuillez entrer une protextion");
//		String value8=sc.nextLine();
		try {
			//Création d’un objet Statement
			Statement state = connect.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "UPDATE `Hero` SET `Nom`='"+obj.getName()+"',`HealthPoint`='"+obj.getLife()+"',`PowerPoint`='"+obj.getAttackLevel()+"' ' WHERE Nom = "+obj.getName();
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Hero findById(int id) {
		List<Hero> heroList = new ArrayList<>();
		warriors.hero.Character player=null;
		System.out.println("veuillez entrer L'ID du Personnage ");
		int scan= sc.nextInt();
		try {

			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT `Type`, `Nom`, `Image`, `HealthPoint`, `PowerPoint`, `Weapons`, `Protections` FROM `Hero` WHERE "+scan);
			if(result.first()) {
				if(result.getString("type").equals("Guerrier")) {
					player = new WarriorHero();}
				else if(result.getString("type").equals("Mage")) {
					player = new WizardHero();
				}
				player.setLife(result.getInt("HealthPoint"));
				player.setName(result.getString("Nom"));
				player.setAttackLevel(result.getInt("PowerPoint"));
				//					result.getString("HealthPoint"));
				//				}
				heroList.add(player);
			}

			result.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}


	public List<Hero> findAll() {
		List<Hero> heroList = new ArrayList<>();
		warriors.hero.Character player=null;
		try {

			Statement state = connect.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM `Hero` ");

			while(result.next()) {
				if(result.getString("Type").equals("Guerrier")) {
					player = new WarriorHero();
				}
				else if(result.getString("Type").equals("Mage")) {
					player = new WizardHero();
				}
				//				
				player.setLife(result.getInt("HealthPoint"));
				player.setName(result.getString("Nom"));
				player.setAttackLevel(result.getInt("PowerPoint"));

				heroList.add(player);

			}
			result.close();
			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return heroList;

	}



}
