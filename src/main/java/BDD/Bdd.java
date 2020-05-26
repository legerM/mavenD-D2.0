package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class Bdd {
	public static Scanner sc =new Scanner(System.in);

	static void getHero(){
		System.out.println("veuillez entrer L'ID du Personnage ");
		int scan= sc.nextInt();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "mickaell";
			String passwd = "cheerfulguys84";
			Connection conn = DriverManager.getConnection(url, user, passwd);
			//Création d’un objet Statement
			Statement state = conn.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL
			ResultSet result = state.executeQuery("SELECT `Type`, `Nom`, `Image`, `HealthPoint`, `PowerPoint`, `Weapons`, `Protections` FROM `Hero` WHERE "+scan);
			//On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			System.out.println("\n**********************************");
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
			System.out.println("\n**********************************");
			while(result.next()){
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + result.getObject(i).toString() + "\t |");
				System.out.println("\n---------------------------------");
			}
			
			result.close();
			state.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	static void getHeroes(){
		
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
			//On affiche le nom des colonnes
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
			System.out.println("\n**********************************");
			while(result.next()){
				
				for(int i = 1; i <= resultMeta.getColumnCount(); i++)
					System.out.print("\t" + result.getObject(i).toString() + "\t |");
				System.out.println("\n---------------------------------");
			}
			
			result.close();
			state.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

	static void createHero() {
		System.out.println("Veuillez entrer un ID");

		int ID=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer une classe de Hero");
		String type=sc.nextLine();
		System.out.println("veuillez entrer un nom ");
		String name=sc.nextLine();

		String image="Image";
		System.out.println("veuillez enter des points de vie ");
		int hp=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer des points de forces");
		int pp=sc.nextInt();
		sc.nextLine();
		System.out.println("Veuillez entrer une arme");
		String weapon=sc.nextLine();
		System.out.println("veuillez entrer une protextion");
		String protection=sc.nextLine();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "mickaell";
			String passwd = "cheerfulguys84";
			Connection conn = DriverManager.getConnection(url, user, passwd);
			//Création d’un objet Statement
			Statement state = conn.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "INSERT INTO `Hero`(`ID`, `Type`, `Nom`, `Image`, `HealthPoint`, `PowerPoint`, `Weapons`, `Protections`) VALUES ('"+ID+"','"+type+"','"+name+"','"+image+"','"+hp+"','"+pp+"','"+weapon+"','"+protection+"')";
			getHeroes();
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static void UpdateHero() {
		System.out.println("Veuillez entrer un ID");

		int ID=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer une classe de Hero");
		String type=sc.nextLine();
		System.out.println("veuillez entrer un nom ");
		String name=sc.nextLine();

		String image="Image";
		System.out.println("veuillez enter des points de vie ");
		int hp=sc.nextInt();
		sc.nextLine();
		System.out.println("veuillez entrer des points de forces");
		int pp=sc.nextInt();
		sc.nextLine();
		System.out.println("Veuillez entrer une arme");
		String weapon=sc.nextLine();
		System.out.println("veuillez entrer une protextion");
		String protection=sc.nextLine();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "mickaell";
			String passwd = "cheerfulguys84";
			Connection conn = DriverManager.getConnection(url, user, passwd);
			//Création d’un objet Statement
			Statement state = conn.createStatement();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "UPDATE `Hero` SET `ID`='"+ID+"',`Type`='"+type+"',`Nom`='"+name+"',`Image`='"+image+"',`HealthPoint`='"+hp+"',`PowerPoint`='"+pp+"',`Weapons`='"+weapon+"',`Protections`='"+protection+"' WHERE ID = "+ID;
			getHeroes();
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	
	static void DeleteHero() {
	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user = "mickaell";
			String passwd = "cheerfulguys84";
			Connection conn = DriverManager.getConnection(url, user, passwd);
			//Création d’un objet Statement
			Statement state = conn.createStatement();
			System.out.println("veuillez entrer L'ID du Hero a supprimer");
			int idDelete=sc.nextInt();
			//L’objet ResultSet contient le résultat de la requête SQL 
			String string = "DELETE FROM `Hero` WHERE `ID`="+idDelete;
			getHeroes();
			state.executeUpdate(string);

			state.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
