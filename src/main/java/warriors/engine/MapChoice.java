package warriors.engine;

import java.util.ArrayList;
import java.util.List;

import warriors.contracts.Map;
import warriors.enemy.DragonCase;
import warriors.enemy.GoblinCase;
import warriors.enemy.SorcererCase;
import warriors.potions.GreatPotionCase;
import warriors.potions.PotionMinorCase;
import warriors.potions.PotionStandarCase;
import warriors.weapons.BowCase;
import warriors.weapons.ClubCase;
import warriors.weapons.Fireball;
import warriors.weapons.LightningCase;
import warriors.weapons.SwordCase;

public class MapChoice implements Map{
	String name;
	public int numberofcase;
	public List<Case> mapCase;

	public MapChoice(String name,int numberofcase){

		this.name=name;
		this.numberofcase=numberofcase;
		this.mapCase =new ArrayList<Case>();
		setMap();
		
	}

	public String getName() {

		return name;

	}


	public int getNumberOfCase() {

		return numberofcase;

	}
	
	public Case getCaseAtPosition(int position) {
		return mapCase.get(position);
	}

	public  void setMap() {

		for(int i = 0;i <= this.numberofcase ; i++) {

			if (i == 45 || i == 52|| i == 56|| i == 62) {

				this.mapCase.add(new DragonCase());
				
			}

			else if (i == 10||i == 20||i==25||i ==32||i==35||i==36||i==37||i== 40||i==44||i==47) {

				this.mapCase.add(new SorcererCase());

			}
			else if (i ==3||i ==6 ||i==9||i==12||i==15||i==18||i==21||i==24||i == 27||i==30) {

				this.mapCase.add(new GoblinCase());

			}
			else if (i == 2||i==11||i==14||i==19||i==26) {

				this.mapCase.add(new BowCase());

			}
			
			else if (i ==5||i== 22||i==38) {

				this.mapCase.add(new ClubCase());

			}
			
			else if (i == 42 ||i == 53) {

				this.mapCase.add(new SwordCase());

			}
			
			else if(i == 1||i==4||i ==8|| i ==17||i == 23) {

				this.mapCase.add(new LightningCase());

			}
			
			else if (i ==48 ||i ==49) {

				this.mapCase.add(new Fireball());

			}
			
			else if (i ==7||i==13||i ==28||i==29||i==33) {

				this.mapCase.add(new PotionMinorCase());

			}

			else if (i==31||i==39||i==43) {

				this.mapCase.add(new PotionStandarCase());

			}
			
			else if (i==41) {

				this.mapCase.add(new GreatPotionCase());
			}
			else {
				this.mapCase.add(new EmptyCase());
			}
			
		}
	}
		public Case event(int currentCase) {
			
			
				Case currentEvent = mapCase.get(currentCase);
				
			
			System.out.println(currentEvent);
			return currentEvent;

	}

}
