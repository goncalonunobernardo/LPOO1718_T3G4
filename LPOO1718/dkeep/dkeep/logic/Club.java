package dkeep.logic;
import java.io.Serializable;
import java.util.Random;

@SuppressWarnings("serial")
public class Club extends Person implements Serializable {
	Random r;					/** @brief To allow for the movement of the club to be random*/
	
	
	public Club(Coordinates coord, char club_symbol, char key_symbol) {
		super (coord, club_symbol, key_symbol);
		this.r = new Random();
	}

	@Override 
	public void move_person (char key, Map map) {
		String movements = "asdw";
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		while (map.not_empty(get_coordinates()) || (initial_coord.equals(get_coordinates()))) {
			this.set_pos(initial_coord);
			super.move_person(movements.charAt(r.nextInt(4)), map);
		}
	}
}
