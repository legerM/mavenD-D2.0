package warriors.engine;

public class EmptyCase extends Case {
	
	
	public EmptyCase() {
		super("salle vide");
		// TODO Auto-generated constructor stub
	}

	public String toString() {
		return "Vous etes dans une Salle Vide ";
	}

	@Override
	public void launchEvent(GameEtat etatGame) {
		 toString();
	}
	
}
