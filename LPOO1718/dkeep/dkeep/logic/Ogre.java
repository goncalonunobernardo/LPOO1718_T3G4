package dkeep.logic;
import java.util.Random;


public class Ogre extends Person {
	private Random r;
	private Club club;
	private int stunned;
	
	public Ogre (Coordinates coord, Coordinates club_coord, char symbol, char club_symbol) {
		super (coord, symbol, "ogre_down");
		r = new Random();
		this.club = new Club (club_coord, club_symbol);
	}

	
	public Club  getClub() {
		return club;
	}
	
	
	@Override
	public void move_person (char key, Map map) {
		String movements = "asdw";
		
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		if (stunned == 0) {
			
			if (this.get_symbol() == '8')
				this.set_symbol('O');
			
			while (map.get_letter(get_coordinates()) != ' ' || (initial_coord.equals(get_coordinates()))) {
				this.set_pos(initial_coord);
				super.move_person(movements.charAt(r.nextInt(4)), map);
			
				if (map.get_letter(this.get_coordinates()) == 'k') {
					this.set_symbol('$');
					break;
				}
				else if (this.get_symbol() == '$') {
					this.set_symbol('O');
					map.set_letter(initial_coord, 'k');
					break;
				}
			}
		}
		else
			stunned--;
			
		club.set_pos(this.get_coordinates());
		club.move_person(' ', map);
	}
	
	@Override
	public boolean check_near (Hero person) {
		
		if (super.check_near(person) && (person.get_symbol() == 'A' || person.get_symbol() == 'K')) {
			this.set_symbol('8');
			this.stunned = 2;
			return club.check_near(person);
		}
		else {
			return super.check_near(person) || club.check_near(person);
		}
		
	}
}
