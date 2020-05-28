package BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SdzConnection {
	//CTRL + SHIFT + O pour générer les imports

	//URL de connexion
	private String url = "jdbc:mysql://localhost:3306/Hero?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	//Nom du user
	private String user = "mickaell";
	//Mot de passe de l'utilisateur
	private String passwd = "cheerfulguys84";
	//Objet Connection
	private static Connection connect;

	//Constructeur privé
	private SdzConnection(){
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	public static Connection getInstance(){
		if(connect == null){
			new SdzConnection();


		}
		return connect; 
		
	}
	
}

