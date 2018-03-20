package dkeep.logic;
import java.util.Random;

public class Club extends Person {
	Random r;					/** @brief To allow for the movement of the club to be random*/
	
	/**
	 * @brief Class constructor
	 * @param x_pos Initial x coordinate of the club on the map
	 * @param y_pos Initial y coordinate of the club on the map
	 * @param symbol Symbol that represents the club on the map
	 */
	Club (int x_pos, int y_pos, char symbol) {
		super (x_pos, y_pos, symbol);
		this.r = new Random ();
	}
	
	public Club(Coordinates coord, char club_symbol) {
		super (coord, club_symbol);
		this.r = new Random();
	}

	@Override 
	public void move_person (char key, Map map) {
		String movements = "asdw";
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		this.get_coordinates().set_pos(0, 0);
		
		while (map.get_letter(get_coordinates()) != ' ' || (initial_coord.equals(get_coordinates()))) {
			this.set_pos(initial_coord);
			super.move_person(movements.charAt(r.nextInt(4)), map);
			
			if (map.get_letter(initial_coord) == 'k') {
				this.set_symbol('$');
				break;
			}
		}
		
		if (this.get_symbol() == '$') {
			map.set_letter(initial_coord, 'k');
		}
		
		
	}

}
