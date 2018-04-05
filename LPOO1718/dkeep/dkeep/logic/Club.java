package dkeep.logic;
import java.io.Serializable;
import java.util.Random;

/**
 * @class Club
 * @extends Person
 * @brief Abstraction of the Club of the game
 */
public class Club extends Person implements Serializable {
	Random r;					/** @brief To allow for the movement of the club to be random*/

	/**
	 * @brief Constructor for the Ogre's Club
	 * @param coord
	 * @param club_symbol
	 * @param key_symbol
	 */
	public Club(Coordinates coord, char club_symbol, char key_symbol) {
		super (coord, club_symbol, key_symbol);
		this.r = new Random();
	}

	/**@brief Movement of Club
	 * @param key a, 1 to left; d, 1 to the right; w, 1 upwards; s, 1 downwards
	 * @param map Map where the character is stored
	 */
	@Override 
	public void move_person (char key, Map map) {
		String movements = "asdw";
		Coordinates initial_coord = new Coordinates (this.get_coordinates());
		
		this.get_coordinates().set_pos(0, 0);
		
		while (map.not_empty(get_coordinates()) || (initial_coord.equals(get_coordinates()))) {
			this.set_pos(initial_coord);
			super.move_person(movements.charAt(r.nextInt(4)), map);
		}
	}
}
